package io.ahnndroid.algorithmstudy.dfs._01_더하기;

/**
 * 
[ 문제 설명 ]
덧셈을 못하는 철수를 공부시키기 위해 자연수들을 주고, 
그 중에 몇 개의 수를 골라서 그 합이 K가 될 수 있는지 알아보라고 시켰다. 
철수 어머니가 자연수들을 무작위로 선택해서 본인도 가능한지 아닌지 모르고 있다. 
어머니가 채점을 할 수 있게 주어진 문제의 답을 찾아주자.


[ 입력 ]
첫 번째 줄에 테스트 케이스 개수 T(1≤T≤10)가 주어진다.
두 번째 줄부터 아래 내용이 T개 만큼 주어진다.
첫 줄에 자연수 개수 N(5 <= N <= 20)과 K(1 <= K <= 2,000,000)가 공백으로 구분되어 입력된다.
다음 줄에 N개의 자연수 di(1 <= di <= 100,000)가 공백으로 구분되어 입력된다.


[ 출력 ]
T줄에 걸쳐 각 테스트 케이스 별로 주어진 자연수 중 몇 개의 합이 K가 되면 “YES”를 아니면 “NO”를 출력한다.


[ 입력 예시 ]
2
5 19
1 2 4 7 10
5 25
1 2 4 7 10


[ 출력 예시 ]
YES
NO

 * @author ahnndroid
 *
 */
import java.util.Scanner;

public class Main {
	
	static int T, N, K;
	static int[] a = new int[20 + 10];
	
	static int DFS(int pos, int sum) {
		
		// 종료 조건
		if (sum == K) return 1;
		if (pos == N + 1) return 0;
		
		// 가지 치기 : 누적합이 허용 범위 안에 있을 경우에만 재귀를 태우겠다는 의미
		if (sum + a[pos] <= K) {
			// 재귀 호출 Case 1) 선택하는 경우
			if (DFS(pos + 1, sum + a[pos]) == 1) return 1;
		}
		
		// 재귀 호출 Case 2) 선택하지 않는 경우
		if (DFS(pos + 1, sum) == 1) return 1;
		
		return 0;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			K = sc.nextInt();
			
			for (int i = 1; i <= N; i++) {
				a[i] = sc.nextInt();
			}
			
			if (DFS(1, 0) == 1) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		
		sc.close();
	}

}
