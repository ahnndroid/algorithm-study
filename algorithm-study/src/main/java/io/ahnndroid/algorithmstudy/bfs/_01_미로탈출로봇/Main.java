package io.ahnndroid.algorithmstudy.bfs._01_미로탈출로봇;

import java.util.Scanner;

/**
 * 
[ 문제 설명 ]
미로탈출 로봇 대회를 개최하였다. 
대회에 사용되는 미로는 가로(WIDTH), 세로(HEIGHT) 100이하의 크기이며, 미로를 한 칸 이동하는 데는 1초가 걸린다.
로봇이 출발점에서 도착점까지 가장 빨리 이동할 경우 걸리는 시간을 구하는 프로그램을 작성하시오.
 
[ 입력 ]
첫 줄에 미로의 크기 X, Y(1≤X, Y≤100)가 주어진다.
둘째 줄에 출발점 x, y 좌표와 도착점 x, y 좌표가 공백으로 구분하여 주어진다.
셋째 줄부터 미로의 정보가 길은 0, 벽은 1로 공백이 없이 들어온다.
주의 사항으로, 좌표는 좌측상단이 가장 작은 위치이며 이 위치의 좌표는 (1,1)이다.
 
[ 출력 ]
첫 줄에 출발점에서 도착점까지 가장 빠른 시간을 출력한다.
 
[ 입력 예시 ]
8 7
1 2 7 5
11111111
00000111
10110011
10111001
10111101
10000001
11111111
 
[ 출력 예시 ]
9
	
 * @author ahnndroid
 *
 */
public class Main {
	
	// 미로의 행렬 사이즈를 입력 받는 전역 변수 (X는 세로, Y는 가로)
	static int X, Y;
	// 출발점 좌표를 입력 받는 전역 변수
	static int SX, SY;
	// 도착점 좌표를 입력 받는 전역 변수
	static int EX, EY;
	
	// 미로판 배열 전역 변수
	static char[][] miro = new char[Y+10][X+10];
	// 미로 방문 여부 저장을 위한 배열 전역 변수
	static int[][] visit = new int[Y+10][X+10];
	
	// 큐 원소에 관한 내부 클래스
	static class Element {
		int currY;		// 원소의 행 좌표 위치
		int currX;		// 원소의 열 좌표 위치
		int v;			// 원소 방문 여부 플래그
		
		Element(int y, int x, int v) {
			this.currY = y;
			this.currX = x;
			this.v = v;
		}
	}
	// 큐 전역 변수
	static Element[] Queue = new Element[(Y+10) * (X+10)];
	// 큐의 쓰기 & 읽기 위치를 저장하는 포인터 관련 전역 변수
	static int wp, rp;
	// 큐에서 꺼낸 원소 기준 상하좌우 탐색을 위한 오프셋 배열 전역 변수 선언 
	static int[] offsetY = { -1, 1, 0, 0 };
	static int[] offsetX = { 0, 0, -1, 1 };
	
	// 이동 시간 누적합(정답) 출력용 전역 변수
	static int sol = 0;
	
	/**
	 * 알고리즘 연산에 필요한 전역 변수 초기화 작업을 진행하는 유틸 메소드
	 */
	static void init() {
		Scanner sc = new Scanner(System.in);
		
		// 미로 행렬 사이즈 입력
		X = sc.nextInt();
		Y = sc.nextInt();
		
		// 출발점 좌표 입력
		SX = sc.nextInt();
		SY = sc.nextInt();
		
		// 도착점 좌표 입력
		EX = sc.nextInt();
		EY = sc.nextInt();
		
		// 미로판 구성을 위한 입력
		for (int i = 1; i <= Y; i++) {
			miro[i] = ("\0" + sc.next()).toCharArray();
		}
		
		sc.close();
	}
	
	
	/**
	 * 너비 우선 탐색(BFS) 수행 메소드
	 * 
	 * @return
	 */
	static int BFS() {
		
		// Step 1) BFS 시작
		// Step 1-1) 시작 위치를 큐에 삽입하고
		enQueue(SY, SX, 0);
		
		// Step 1-2) 해당 시작 위치를 방문했음을 마킹
		visit[SY][SX] = 1;
		
		// 큐의 쓰기 포인터 값이 읽기 포인터 값보다 큰 동안(즉, 큐로부터 읽을 데이터가 존재하는 동안) 루프 반복 진행
		while (wp > rp) {
			// Step 2) 큐에서 원소를 꺼내서
			Element out = deQueue();
			
			// 꺼낸 원소의 좌표를 기준으로 상하좌우 탐색 시작
			int currY, currX;
			for (int k = 0; k < offsetY.length; k++) {
				// Step 3) 꺼낸 원소 좌표를 기준으로 상, 하, 좌 or 우 방향일때의 좌표를 계산해서
				currY = out.currY + offsetY[k];
				currX = out.currX + offsetX[k];
				
				// Step 4) 탐색한 현재 좌표의 원소가...
				// Step 4-1) 미로판 범위를 벗어난 상태면 continue
				if (currY < 1 || currY > Y || currX < 1 || currX > X) continue;
				
				// Step 4-2) 벽이면 continue
				if (miro[currY][currX] == 1)	continue;
				
				// Step 4-3) 방문한 좌표 칸이면 continue
				if (visit[currY][currX] == 1)	continue;
				
				// Step 4-4) 목적지와 일치하면 결과 리턴
				if (currY == EY && currX == EX)	return out.v + 1;
				
				// Step 5) 목적지에 일단 도달하지 않은 좌표의 원소인 경우...
				// Step 5-1) 방문했다는 마킹을 하고
				visit[currY][currX] = 1;
				
				// Step 5-2) 해당 탐색된 원소를 다음 연산을 위해 큐에 삽입
				enQueue(currY, currX, out.v + 1);
			}
		}
		
		// 여기까지 도달했다는 것은 미로 탈출에 실패했다는 의미 (사실상 도달 불가)
		return 0;
	}
	
	/**
	 * 큐에 원소 삽입 시 이용하는 유틸 메소드
	 */
	static void enQueue(int y, int x, int v) {
		Queue[wp++] = new Element(y, x, v);
	}
	
	/**
	 * 큐에서 원소를 꺼낼 시 이용하는 유틸 메소드
	 * @return
	 */
	static Element deQueue() {
		return Queue[rp++];
	}

	public static void main(String[] args) {
		
		init();			// 입력 초기화 작업 진행
		
		sol = BFS();	// 가장 빠른 이동 시간 계산을 위한 너비 우선 탐색 진행
		
		System.out.println(sol);
		
	}

}
