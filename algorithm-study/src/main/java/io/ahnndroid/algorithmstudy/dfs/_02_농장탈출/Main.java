package io.ahnndroid.algorithmstudy.dfs._02_농장탈출;

/**
 * 
[ 문제 설명 ]
소들은 농부 존의 농장을 탈출하는 대담한 계획을 세웠다.
그들은 작은 고무 보트를 구했고 한 밤중에 농장 경계에 흐르는 강을 보트를 타고 건너려는 계획이다. 
그 계획은 완벽해 보였다. 작은 고무 보트가 소들의 무게를 견디지 못한다는 사실을 알기 전까지는…
 
N마리의 소(1≤N≤20)들의 무게는 각각 W_1, …, W_N이다. 
보트가 침몰하지 않을 만큼 가벼운 소들을 선별해야 한다. 불행하게도, 소들은 산수를 못하기로 유명하다. 
10진법을 사용하는 소들은 소들의 무게를 더하다가 자리올림이 발생하는 경우 
그 소는 보트를 사용하기에는 너무 무거운 소라고 판단한다. 
자리올림이 발생하지 않고 더할 수 있는 무게가 보트를 사용할 수 있는 가벼운 무게이다.
 
당신이 할 일은 소들을 도와서 보트를 탈 수 있는 소들의 최대 수를 구하는 것이다. 
즉, 소들의 무게들을 모두 더했을 때 자리올림이 발생하지 않게 하는 소들의 최대 수를 구하는 것이다.


[ 입력 ]
첫 줄에 소들의 수 N(1≤N≤20)이 주어진다.
두 번째 줄부터 N 줄에 걸쳐 각 소의 무게(W_i)가 입력된다. 
(정수, 1≤W_i≤100,000,000)


[ 출력 ]
무게를 모두 더했을 때 어떤 자리에서도 자리올림이 발생하지 않는 소들의 최대 수를 출력하라.


[ 입력 예시 ]
5
522
6
84
7311
19


[ 출력 예시 ]
3

 * 
 * @author ahnndroid
 *
 */
import java.util.Scanner;

public class Main {
	
	static int N;
	static int[] W = new int[20 + 10];
	static int sol = 0;
	
	/**
	 * 깊이 우선 탐색
	 * 
	 * @param pos : 배에 실은 소의 번호
	 * @param sum : pos 이전까지의 소들의 무게 합
	 * @param cnt : pos 이전까지의 소들의 수
	 */
	static void DFS(int pos, int sum, int cnt) {
		
		// 1. 종료 조건 : 배에 실은 소의 번호가 입력된 번호의 범위를 벗어나는 경우
		if (pos == N + 1) {
			// 최종 카운트를 정답을 담을 변수에 업데이트한 후 반환
			if (cnt > sol) {
				sol = cnt;
			}
			
			return;
		}
		
		// 4. 가지 치기 : 자리 넘침이 발생하지 않는 경우에만 선택 깊이 경로로 재귀 타고 내려가는 걸 허용
		if (check(sum, W[pos]) == 1) {
			// 2. 재귀 호출 Case 1) 선택하는 경우
			DFS(pos + 1, sum + W[pos], cnt + 1);
		}
		
		// 3. 재귀 호출 Case 2) 선택하지 않는 경우
		DFS(pos + 1, sum, cnt);
		
	}
	
	/**
	 * 자리 넘침 발생 여부 체크 유틸 메소드
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	static int check(int x, int y) {
		
		while (x > 0 && y > 0) {
			// 자리 넘침이 발생하는 경우 0 리턴
			if (x % 10 + y % 10 >= 10) return 0;
			x /= 10;
			y /= 10;
		}
		
		// 최종적으로 자리 넘침이 발생하지 않으면 1 리턴 
		return 1;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();	// 소들의 수 입력
		
		// 각 소들의 무게 입력
		for (int i = 1; i <= N; i++) {
			W[i] = sc.nextInt();
		}
		
		// 깊이 우선 탐색을 이용해 맨 처음부터 탐색해나감.
		DFS(1, 0, 0);
		System.out.println(sol);
		
		sc.close();
	}

}
