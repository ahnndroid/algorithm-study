package io.ahnndroid.algorithmstudy.bfs._02_장기;

import java.util.Scanner;

/**
 * 
[ 문제 설명 ]
N×M장기판에 졸 한 개와 말 한 개가 놓여 있다.
말의 이동 방향이 다음과 같다고 할 때, 말이 최소의 이동횟수로 졸을 잡으려고 한다.
말이 졸을 잡기 위한 최소 이동 횟수를 구하는 프로그램을 작성해보자.

[ 입력 ]
첫 줄은 장기판 행의 수(N)와 열의 수(M)를 받는다.(1≤N,M≤100)
둘째 줄은 말이 있는 위치의 행(R), 열(C)의 수와 졸이 있는 위치의 행(S), 열(K)의 수를 입력 받는다. 
단, 장기판의 제일 왼쪽 위의 위치가 (1,1)이다.
각 행과 열은 R(1≤R≤N), C(1≤C≤M), S(1≤S≤N), K(1≤K≤M)이다.

[ 출력 ]
말이 졸을 잡기 위한 최소 이동 횟수를 출력한다.

[ 입력 예시 ]
9 9
3 5 2 8

[ 출력 예시 ]
2

 * @author ahnndroid
 *
 */
public class Main {
	
	// 장기판 행(N)과 열(M)을 입력 받을 전역 변수 선언
	static int N, M;
	
	// 말이 위치한 행(R)과 열(C)을 입력 받을 전역 변수 선언
	static int R, C;
	
	// 졸이 위치한 행(S)과 열(K)을 입력 받을 전역 변수 선언
	static int S, K;
	
	// 장기판 배열 전역 변수 선언
	static int[][] jangi;
	
	// 큐 객체 전역 변수 선언
	static Element[] Queue;
	
	// 큐의 추출 위치 포인터(dp: dequeuePointer) 관련 전역 변수 선언
	static int dp;
	
	// 큐의 삽입 위치 포인터(ep: enqueuePointer) 관련 전역 변수 선언
	static int ep;
	
	// 최소 이동 횟수 관련 결과값 출력을 위한 전역 변수 선언
	static int sol;
	
	
	/**
	 * 초기화 작업 진행 관련 유틸 메소드
	 */
	static void init() {
		// 전역 변수 초기값 설정
		initStaticVariables();
		
		// 사용자 입력 초기화
		initInputVariables();
	}
	
	/**
	 * 전역 변수 초기값 설정
	 */
	static void initStaticVariables() {
		jangi = new int[100 + 10][100 + 10];
		
		Queue = new Element[(100 + 10) * (100 + 10)];
		
		dp = 0;
		ep = 0;
		
		sol = 0;
	}
	
	/**
	 * 사용자 입력 관련 전역 변수 초기화
	 */
	static void initInputVariables() {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		R = sc.nextInt();
		C = sc.nextInt();
		
		S = sc.nextInt();
		K = sc.nextInt();
		
		sc.close();
	}
	
	
	/**
	 * 최소 이동 횟수를 찾기 위한 너비 우선 탐색 진행
	 * 
	 * @return
	 */
	static int BFS() {
		
		// BFS 탐색 시 이용될 행렬 오프셋 배열 선언
		int[] rowOffset = { -2, -1,  1,  2,  2,  1, -1, -2 };
		int[] colOffset = { -1, -2, -2, -1,  1,  2,  2,  1 };
		
		// BFS 탐색 대상 원소의 행렬 좌표 저장 변수
		int currRow, currCol;
		
		// Step 1) 시작 원소의 큐 삽입 & 해당 원소의 좌표를 방문했음을 마킹
		enQueue(R, C, 0);
		jangi[R][C] = 1;
		
		// Step 2) 큐에 원소가 존재하는 동안 루프를 반복하면서 (= enqueuePointer가 dequeuePointer보다 큰 동안)
		while (ep > dp) {
			
			// Step 3) 큐에서 원소를 하나 빼내서
			Element out = deQueue();
			
			// Step 4) 빼낸 원소 좌표를 기준으로 상하좌우대각선 탐색 과정 진행
			for (int i = 0; i < 8; i++) {
				
				// Step 4-1) 탐색 대상 원소의 좌표 추출
				currRow = out.row + rowOffset[i];
				currCol = out.col + colOffset[i];
				
				// Step 4-2) 탐색 대상 원소가 범위를 벗어난 경우 continue
				if ( currRow < 1 || currRow > N || currCol < 1 || currCol > M )	continue;
				
				// Step 4-3) 탐색 대상 원소가 방문했던 곳이면 continue
				if ( jangi[currCol][currRow] == 1 )	continue;
				
				// Step 4-4) 탐색 대상 원소가 졸의 위치와 동일하면 이동 회수를 1 증가 후 return
				if ( currRow == S && currCol == K )	return out.v + 1;
				
				// Step 5) 아직 졸을 만나지 못했다면
				// Step 5-1) 다음 탐색을 위해 현재 탐색 대상 원소를 큐에 삽입하고
				enQueue(currRow, currCol, out.v + 1);
				
				// Step 5-2) 해당 탐색 대상 원소의 좌표는 방문했었음을 마킹
				jangi[currCol][currRow] = 1;
			}
		}
		
		return 0;
	}
	
	/**
	 * 큐 원소 삽입 유틸 메소드
	 * 
	 * @param row
	 * @param col
	 * @param v
	 */
	static void enQueue(int row, int col, int v) {
		Queue[ep++] = new Element(row, col, v);
	}
	
	/**
	 * 큐 원소 빼내기 유틸 메소드
	 * @return
	 */
	static Element deQueue() {
		return Queue[dp++];
	}
	
	/**
	 * 결과 출력
	 */
	static void print() {
		System.out.println(sol);
	}
	

	public static void main(String[] args) {
		
		// 초기화 작업 진행
		init();
		
		// 장기 말이 졸을 찾는데 이동한 최소 이동 횟수를 찾기 위한 너비 우선 탐색 진행
		sol = BFS();
		
		// 결과 출력
		print();
	}
	
	
	/**
	 * 큐 원소 관련 내부 클래스
	 * 
	 * @author ahnndroid
	 *
	 */
	static class Element {
		int row;	// 큐 원소의 행 위치 
		int col;	// 큐 원소의 열 위치
		int v;		// 큐 원소의 이동 횟수
		
		Element(int row, int col, int v) {
			this.row = row;
			this.col = col;
			this.v = v;
		}
	}

}
