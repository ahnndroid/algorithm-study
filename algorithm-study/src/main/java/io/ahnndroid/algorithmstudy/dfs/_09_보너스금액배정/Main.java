package io.ahnndroid.algorithmstudy.dfs._09_보너스금액배정;

/**
 * 
[ 문제 설명 ]
중소기업인 K 회사에서 직원들에게 보너스를 지급하려고 한다.
그런데 직원들의 자존심이 강해서 상급자들이 직급이 낮은 사람보다는 한 푼이라도 더 받기를 원한다.
단, 자기랑 직접적 관련이 없는 사람의 보너스 금액에는 관심 없다.
중소기업 특성상 정확한 직급이 존재하지 않고 누가 누구 상급자고 하급자인지만 정해져 있는 상황에서 사장은 골치가 아프다.
편의상 이름은 숫자로 대체한다.
1은 언제나 사장이다.

5명이 있고 보너스 금액은 51, 30, 35, 30, 31 일 경우 
1번부터 51, 35, 31, 30, 30으로 배정하면 된다.
중소기업 사장을 도와서 모두가 만족할 수 있는 보너스 금액을 배정하자.


[ 입력 ]
첫 줄에 N과 M이 입력된다. 
N은 직원 수 (3≤N≤10), M은 상하관계의 개수(2≤M≤100) 이다.

다음 줄부터 M줄에 걸쳐 상하관계가 입력된다. 
각 줄에는 상급자 하급자 순으로 입력되며 공백으로 구분된다. 
(상하관계 오류가 발생하는 입력은 없음)

마지막 줄에는 보너스 금액이 N개만큼 공백으로 구분되어 입력된다. 
보너스 금액은 1이상 1,000,000 이하 이다.


[ 출력 ]
1번부터 N번까지 순서대로 공백으로 구분하여 보너스 금액을 출력한다. 
(답이 여러 개일 경우 그 중 한 가지만 출력하면 됨)


[ 입력 예시 ]
5 6
1 2
2 3
1 4
2 4
1 5
3 5
51 30 35 30 31


[ 출력 예시 ]
51 35 31 30 30 

 * 
 * @author ahnndroid
 *
 */
import java.util.Scanner;

public class Main{
	
	static int N;			// 직원 수
	static int M;			// 상하관계 개수
	static int[][] a;	// 직원 간 상하관계를 나타내는 배열
	static int[] B;			// 입력 받은 보너스 금액 배열
	static int[] visit;		// 입력 받은 보너스 금액 사용 여부를 담을 배열
	static int[] V;			// 직원들에게 최종적으로 배정되는 보너스 금액을 담을 배열
	
	static void init() {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
        M = sc.nextInt();
         
        a = new int[10 + 10][10 + 10];
        B = new int[10 + 10];
        visit = new int[10 + 10];
        V = new int[10 + 10];
         
        for (int i = 1; i <= M; i++) {
            int t1 = sc.nextInt();
            int t2 = sc.nextInt();
            
            // 부하 직원임을 나타내는 마킹 작업
            a[t1][t2] = 1;
        }
      
        for (int i = 1; i <= N; i++) {
        	B[i] = sc.nextInt();
        }
        
        sc.close();
	}
    
    /**
     * 
     * @param pos : 직원 번호
     * @return
     */
    static int DFS(int pos) {
    	// 1) 종료 조건
    	// 	  => 탐색 깊이에 다다르면 보너스 금액 배정이 성공했다고 보고 결과 리턴
        if (pos == N + 1) return 1;
        
        // 입력된 보너스 금액 배열 사이즈만큼 루프를 반복하면서 적정 보너스 배정을 위한 깊이 우선 탐색 진행
        for (int i = 1; i <= N; i++) {
        	// 가지 치기 1: 이미 지급한 보너스로 판명되면 continue
            if (visit[i] == 1) continue;
            
            // 가지 치기 2: 상하관계에 의거하여 보너스 지급이 불가하다 판명되면 continue
            if (check(pos, B[i]) == 0) continue;
              
            /** pos 순번의 직원에게 i번째 보너스 금액 배정을 위한 전역 변수 설정 **/
            // pos 순번의 직원에게 i번째 금액을 배정
            V[pos] = B[i];
            // i번째의 보너스 금액 사용 모드로 표시
            visit[i] = 1;
            
            // 보너스 배정을 위한 깊이 우선 탐색 재귀 진행
            if (DFS(pos + 1) == 1) return 1;
      
            /** 보너스 지급 탐색에 실패한 경우 전역 변수 복구 **/
            // pos 순번의 직원에게 배정한 금액 제거
            V[pos] = 0;
            // i번째 금액 사용 안함으로 표시
            visit[i] = 0;
        }
        
        return 0;
    }
    
    /**
     * 보너스 배정 가능 여부 체크를 위한 유틸 메소드
     * 
     * @param pos : 현재 순번의 직원 번호
     * @param bonus : pos 순번의 직원에게 배정 시도하려는 보너스 금액
     * @return
     */
    static int check(int pos, int bonus) {
    	// i는 직원 번호
        for (int i = 1; i <= N; i++) {
        	// i 번째 직원에게 보너스 지급 배정이 되지 않은 상태이므로 continue
            if (V[i] == 0) continue;
            
            // pos 순번 직원의 하급자인 i번째 직원이 pos 순번 직원보다 더 많은 돈을 받은 경우엔 보너스 배정 불가
            if ((a[pos][i] == 1) && (V[i] >= bonus)) return 0;
            // pos 순번 직원이 상급자인 i번째 직원보다 많은 돈을 받는 경우엔 보너스 배정 불가
            if ((a[i][pos] == 1) && (V[i] <= bonus)) return 0;
        }
        
        return 1;
    }
    
    /**
     * 결과 출력 유틸 메소드
     */
    static void print() {
    	for (int i = 1; i <= N; i++) {
    		System.out.print(V[i] + " ");
    	}
    }
    
    public static void main(String[] args) {
    	
		init();		// 전역 변수 입력 및 초기화
        
        DFS(1);     // 사장 다음부터 직원 배정
        
        print();	// 최종 보너스 배정 결과 출력
	}
}