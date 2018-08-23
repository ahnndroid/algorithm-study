package io.ahnndroid.algorithmstudy.dfs._04_해밀턴순환회로;

/**
 * 
[ 문제 설명 ]
문제를 잘 풀기로 소문난 도경이는 모든 올림피아드 대회에 참가하고 싶어 했다. 
대회에 참가하여 상이란 상은 다 타고 싶은 마음이었지만, 한 가지 걸리는 것이 있었다.
 
문제는 올림피아드 대회가 모두 해외에서 열리는 관계로 비행기 값이 굉장히 많이 들어간다는 것이다. 
결국에는 집으로 다시 돌아와야 하는데, 
모든 대회에 1번씩만 참가하고 집으로 돌아오는 경비를 가장 최소화하고 싶다. 
도경이는 이것을 해결하지 못하면, 대회에 참가하기가 어렵게 된다. 
대회는 참가하기만 하면 언제든지 알고리즘 문제를 풀 수 있기 때문에 
경기를 계산하는 것 이외의 사항은 고려하지 않아도 된다.


[ 입력 ]
첫 줄은 참가하는 대회의 수 N(1≤N≤12)을 입력 받는다. 
이 때, 출발지(집)는 1번으로 한다.
둘째 줄은 N*N 크기의 대회 개최지와 개최지를 이동하는 항공료(0≤항공료<100)가 나온다. 
각 항공료는 한 칸의 공백으로 구분된다. 
만약에 개최지에서 개최지로 이동할 수 있는 항공로가 없다면 항공료의 값을 0으로 표시한다.


[ 출력 ]
집에서 출발하여 전체 대회를 모두 참가하고 돌아올 때, 최소의 항공료를 출력한다.


[ 입력 예시 ]
5 
0 14 4 10 20 
14 0 7 8 7 
4 5 0 7 16 
11 7 9 0 2 
18 7 17 4 0 


[ 출력 예시 ]
30

 * 
 * @author ahnndroid
 *
 */
import java.util.Scanner;

public class Main {
	
	static int N;
	static int sol = 0x7fffffff;	// 최소 비용을 구해야 함에 따라 최대값을 사전 저장해두는 용도로 활용
	static int[][] a = new int[12 + 10][12 + 10];	// 지역별 항공료 관련 배열 전역 변수 선언
	static int[] visit = new int[12 + 10];		// 방문한 지역 표시를 위한 배열 전역 변수 선언
	
	/**
	 * 
	 * @param pos : 방문 도시의 순서 
	 * @param cost : pos 이전까지의 비용
	 * @param start : 출발 도시
	 */
	static void DFS(int pos, int cost, int start) {
		
		// 1. 종료 조건
		if (pos == N) {
			
			// 1-1) 모두 순회했다 하더라도 1번 도시로 갈 수 없으면 반환 후 종료
			if (a[start][1] == 0) return;
			
			// 1-2) cost(= pos 이전까지의 방문 비용) + 1번 도시로 가는 비용 < sol인 경우 반환 후 종료
			if (sol > cost + a[start][1]) {
				sol = cost + a[start][1];
			}
			
			return;
		}
		
		// 2. 가지 치기
		// 하기 루프 상의 i 인덱스는 방문할 도시를 가리킴.
		for (int i = 2; i <= N; i++) {
			// 2-1) 항공료가 0원이면 이동이 불가하므로 continue
			if (a[start][i] == 0) continue;
			
			// 2-2) 이미 방문한 곳이면 탐색할 필요가 없으므로 continue
			if (visit[i] == 1) continue;
			
			// 2-3) pos 이전까지의 누적 비용이 sol 비용보다 크다면 continue
			if (cost + a[start][i] >= sol) continue;
			
			// 가지 치기 조건을 모두 회피했다면
			// 방문 했음을 마킹하고
			visit[i] = 1;
			
			// 다음 도시로 방문해서 탐색 진행
			DFS(pos + 1, cost + a[start][i], i);
            
			// 방문 표시 제거
            visit[i] = 0;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		
		DFS(1, 0, 1);
		System.out.println(sol);
		
		sc.close();
	}

}
