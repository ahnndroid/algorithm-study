package io.ahnndroid.algorithmstudy.dfs._08_양팔저울;

/**
 * 
[ 문제 설명 ]
양팔 저울과 몇 개의 추가 주어졌을 때, 
이를 이용하여 입력으로 주어진 구슬의 무게를 확인할 수 있는지를 결정하려고 한다.
 
무게가 각각 1g과 4g인 두 개의 추가 있을 경우, 
주어진 구슬과 1g 추 하나를 양팔 저울의 양쪽에 각각 올려놓아 수평을 이루면 구슬의 무게는 1g이다. 
또 다른 구슬이 4g인지를 확인하려면 1g 추 대신 4g 추를 올려놓으면 된다.
 
구슬이 3g인 경우 아래 <그림 1>과 같이 구슬과 추를 올려놓으면 양팔 저울이 수평을 이루게 된다. 
따라서 각각 1g과 4g인 추가 하나씩 있을 경우 주어진 구슬이 3g인지도 확인해 볼 수 있다.
 
<그림 2>와 같은 방법을 사용하면 구슬이 5g인지도 확인할 수 있다. 
구슬이 2g이면 주어진 추를 가지고는 확인할 수 없다.
 
추들의 무게와 확인할 구슬들의 무게가 입력되었을 때, 
주어진 추만을 사용하여 구슬의 무게를 확인 할 수 있는지를 결정하는 프로그램을 작성하시오.
 

[ 입력 ]
첫째 줄에는 추의 개수가 자연수로 주어진다. 추의 개수는 12 이하이다.
 
둘째 줄에는 추의 무게들이 자연수로 가벼운 것부터 차례로 주어진다.
같은 무게의 추가 여러 개 있을 수도 있다. 
추의 무게는 500g이하이며, 입력되는 무게들 사이에는 빈칸이 하나씩 있다.
 
세 번째 줄에는 무게를 확인하고자 하는 구슬들의 개수가 주어진다. 
확인할 구슬의 개수는 7이하이다. 

네 번째 줄에는 확인하고자 하는 구슬들의 무게 Gi( 0 <= Gi <=40000)가 정수로 주어지며,
입력되는 무게들 사이에는 빈 칸이 하나씩 있다.


[ 출력 ]
주어진 각 구슬의 무게에 대하여 확인이 가능하면 Y, 아니면 N을 차례로 출력한다.
한 개의 줄로 이루어지며, 각 구슬에 대한 답 사이에는 빈칸을 하나씩 둔다.


[ 입력 예시 ]
2
1 4
2
3 2


[ 출력 예시 ]
Y N 

 * 
 * @author ahnndroid
 *
 */
import java.util.Scanner;

public class Main {
	
	static int N, M;
	static int[] a;
	static int[] r;
	static int[] G;
	
	/**
	 * 깊이 우선 탐색 진행
	 * 
	 * @param pos : 탐색 순번 (여기서는 사용할 추의 순번)
	 * @param left : 왼쪽 팔에 올려놓을 구슬의 무게
	 * @param right : 오른쪽 팔에 올려놓을 구슬의 무게
	 * @return
	 */
	static int DFS(int pos, int left, int right) {
		// 가지 치기
		if (Math.abs(left - right) > r[pos]) return 0;
		
		// 1) 종료 조건
		// 1-1) 왼쪽 팔 무게와 오른쪽 팔 무게가 동일하면 답을 찾은 것이므로 1 리턴 후 순회 종료
		if (left == right) return 1;
		// 1-2) 순회 순번이 범위를 벗어나면 종료
		if (pos == N + 1) return 0;
		
		// 2) 깊이 우선 탐색 진행
		// 2-1) 왼쪽에 추를 놓는 케이스에 대한 재귀 탐색
		if (DFS(pos + 1, left + a[pos], right) == 1) return 1;
		// 2-2) 오른쪽에 추를 놓는 케이스에 대한 재귀 탐색
		if (DFS(pos + 1, left, right + a[pos]) == 1) return 1;
		// 2-3) 어느 쪽 팔에도 추를 올리지 않는 케이스에 대한 재귀 탐색
		if (DFS(pos + 1, left, right) == 1) return 1;
		
		return 0;
	}

	public static void main(String[] args) {
		a = new int[12 + 10];
		r = new int[12 + 10];
		G = new int[7 + 10];
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		for (int i = 1; i <= N; i++) {
			a[i] = sc.nextInt();
		}
		r[N] = a[N];
		for (int i = N - 1; i >= 1; i--) {
			r[i] = a[i] + r[i + 1]; 
		}
		
		M = sc.nextInt();
		for (int i = 1; i <= M; i++) {
			G[i] = sc.nextInt();
			
			if (DFS(1, G[i], 0) == 1) {
				System.out.print("Y ");
			} else {
				System.out.print("N ");
			}
		}
		
		sc.close();
	}

}
