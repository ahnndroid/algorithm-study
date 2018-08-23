package io.ahnndroid.algorithmstudy.dfs._05_책꽂이;

import java.util.Scanner;

/**
 * 
[ 문제 설명 ]
농부 John은 최근 소 도서관을 위한 책꽂이를 구매했지만, 
책이 빠른 속도로 채워져서 지금은 가장 윗부분에만 공간이 남아있다.
농부 John은 N마리의 소를 가지고 있다.(1 <= N <= 20) 각각의 소의 키는 H_i이다.(1 <= H_i<= 1,000,000)
책꽂이는 B 높이를 가지고 있다. (1 <= B <= S, S는 모든 소의 키의 합계임).

책꽂이의 제일 윗부분에 닿기 위해서, 하나 혹은 여러 마리의 소가 서로의 머리 위에 올라설 수 있다. 
그래서 그들의 전체 높이는 개개인 소의 키의 합계가 된다. 
소들이 책꽂이의 제일 윗부분에 닿기 위해서는 이 전체높이가 책꽂이 높이에 비해 낮아서는 안 된다.
소들이 서로의 머리 위에 올라서게 되면, 높으면 높을수록 위험해지기 때문에, 
당신이 할 일은 책꽂이의 제일 윗부분에 닿을 수 있게 하는 소들의 키의 합의 최소값을 찾는 것이다. 
당신의 프로그램은 당신이 찾게 된 소들의 키의 합이 책꽂이로부터 얼마나 초과하는지를 출력하면 된다.


[ 입력 ]
첫 번째 줄에는 테스트케이스의 갯수 T가 입력된다.
두 번째 줄부터 T개의 테스트케이스 세트가 주어진다.
테스트케이스의 첫번째 줄은 소의 마리수 N과 B가 주어진다. 
(1 <= N <= 20,1 <= B <= S, S는 모든 소의 키의 합계)

테스트케이스의 두번째 줄부터 N줄에 각 소의 키 H_i가 주어진다. (1 <= H_i<= 1,000,000)


[ 출력 ]
적당한 소들의 키의 합과 책꽂이 높이의 차이를 출력한다.


[ 입력 예시 ]
1
5 16
3
1
3
5
6


[ 출력 ]
1

 * 
 * @author ahnndroid
 *
 */
public class Main {
	
	static int T;	// 테스트 케이스 개수를 입력 받는 전역 변수 선언
	static int N;	// 소의 마리수를 입력 받는 전역 변수 선언
	static int B;	// 책꽂이 높이를 입력 받는 전역 변수 선언
	static int[] H = new int[20 + 10];	// 소의 키를 담을 배열 전역 변수 선언
	static int sol = 0x7fffffff;		// 정답을 담을 전역 변수 선언
	
	/**
	 * 
	 * @param pos : 소의 순번
	 * @param sum : 소의 키 누적합
	 */
	static void DFS(int pos, int sum) {
		// 1. 종료 조건
		// 1-1) 소의 키 누적합 sum >= sol이면 반환 후 종료
		if (sum >= sol) return;
		
		// 1-2) 소의 키 누적합이 책꽂이 높이보다 크거나 같으면, sol에 소의 키 누적합을 업데이트하고 반환 후 종료
		if (pos == N) {
			if (sum >= B) sol = sum;
			return;
		}		
		
		// 종료 조건을 회피했다면
		// 소의 순번을 1 증가시키고 현재까지의 누적합을 담아서 깊이 우선 탐색 진행
		DFS(pos + 1, sum);
		
		// 소의 순번을 1 증가시키고 현재 순번의 소의 키 값을 누적합에 더한 값을 담아 깊이 우선 탐색 진행
		DFS(pos + 1, H[pos] + sum);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			// 주의: 변수에 대한 초기화 필요
			N = sc.nextInt();
			B = sc.nextInt();
			
			for (int i = 1; i <= N; i++) {
				H[i] = sc.nextInt();
			}
			
			DFS(1, 1);
			System.out.println(sol - B);
		}
		
		sc.close();
	}

}
