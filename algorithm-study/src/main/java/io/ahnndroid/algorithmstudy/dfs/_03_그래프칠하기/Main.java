package io.ahnndroid.algorithmstudy.dfs._03_그래프칠하기;

/**
 * 
[ 문제 설명 ]
N개의 노드로 구성된 그래프의 정보가 주어지고, 숫자 M이 주어진다. 
이 때, 서로 인접한 노드들 간에는 같은 색을 칠하지 않으면서 M개의 색으로 N개의 노드를 전부 칠할 수 있는지 판단하라.
가능한 경우에는 첫 번째 노드부터 색상 번호(1 ~ M에서 선택)를 출력하고, 불가능한 경우 -1을 출력한다.
 
노드1부터 순서대로 색을 칠해야 하며 색상 번호도 낮은 번호부터 붙여 나가야 한다.
주어진 색상을 모두 사용할 필요는 없으며 가능하면 낮은 색상 번호를 사용하여 완성하라.
즉, 6개의 색이 주어졌어도 5개의 색으로 모두 칠할 수 있으면 5개색만 사용하여야 한다.

그래프 정보는 triangular matrix로 주어지고, 
연결되어 있는 경우 1, 연결되어 있지 않은 경우 0으로 주어진다.


[ 입력 ]
첫 줄에 노드 수 N(1≤ N ≤12)와 색상 번호 M(1≤ M ≤12)가 주어진다.


[ 출력 ]
노드1부터의 칠해진 색상 번호 리스트를 출력한다.
불가능한 경우 -1을 출력한다.


[ 입력 예시 ]
4 3
0 
1 0 
1 1 0 
0 1 1 0 


[ 출력 예시 ]
1 2 3 1 
 
 * @author ahnndroid
 *
 */
import java.util.Scanner;

public class Main {
	
	static int N;	// 노드 개수 입력 받을 전역 변수
	static int M;	// 색상 번호 입력 받을 전역 변수
	static int[][] a = new int[12 + 10][12 + 10];
	static int[] C = new int[12 + 10];
	
	/**
	 * 
	 * @param pos : 노드의 번호
	 * @return
	 */
	static int DFS(int pos) {
		
		// 1. 종료 조건
		//	  -> 현재 위치가 노드 깊이의 범위를 벗어나는 시점엔 색상 번호 리스트를 다 찾았다고 보고 성공 리턴
		if (pos == N + 1) return 1;
		
		for (int i = 1; i <= M; i++) {			
			// 2. 가지 치기
			//	  -> 현재 위치 노드에 i번째 색상을 칠할 수 있는지 여부 체크
			if (check(pos, i) == 0) continue;
			
			// 색상 배열에 칠할 색상을 설정
			C[pos] = i;
			
			// 성공 시까지 재귀 호출 진행
			if (DFS(pos + 1) == 1) return 1;
			
			// 색상 번호 전역 변수 복구
			C[pos] = 0;
		}
		
		// 여기에 도달하게 되면 실패라고 봐야 함.
		return 0;
	}
	
	/**
	 * 색상을 칠할 수 있는지 여부를 체크하는 유틸 메소드
	 * 
	 * @param pos : 현재 위치 노드
	 * @param color : 칠하고자 하는 색상값
	 * @return
	 */
	static int check (int pos, int color) {
		// i는 이미 색상이 정해진 노드 번호라 할 수 있음.
		for (int i = 1; i < pos; i++) {
			// 이 때, pos와 i가 인접하여 연결되어 있는 가운데 같은 색상이면 칠할 수 없으므로 0을 리턴
			if (a[pos][i] == 1 && C[i] == color) return 0;
		}
		
		return 1;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				a[i][j] = sc.nextInt();
				a[j][i] = a[i][j];
			}
		}
		
		// 깊이 우선 탐색을 통해 탐색이 깊이 끝까지 탐색이 끝났다면
		// 색상이 다 칠해진 배열이 생성되었다는 의미이므로 색상 출력 후 종료
		if (DFS(1) == 1) {
			for (int i = 1; i <= N; i++) {
				System.out.print(C[i] + " ");
			}
		} else {
			System.out.println("-1");
		}
		
		sc.close();
	}

}
