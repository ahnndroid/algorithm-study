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
<<<<<<< HEAD:algorithm-study/src/main/java/io/ahnndroid/algorithmstudy/bfs/_01_미로탈출로봇/Main.java
public class Main {
	
	static int X, Y;	// 미로 행렬 사이즈 입력
	static int SX, SY;	// 출발점 좌표 입력
	static int EX, EY;	// 도착점 좌표 입력
	
=======
public class 미로탈출로봇 {
	
	private static int WIDTH, HEIGHT;				// Scanner를 통해 입력받게 될 미로의 행렬 사이즈 관련 전역 변수 선언
	private static int START_WIDTH, START_HEIGHT;	// Scanner를 통해 입력 받게 될 출발점 가로&세로 좌표 관련 전역 변수 선언
	private static int FIN_X, FIN_Y;				// Scanner를 통해 입력 받게 될 도착점 가로&세로 좌표 관련 전역 변수 선언
	private static int[][] miro = new int[100 + 10][100 + 10];		// 미로를 구성하게 될 배열 관련 전역 변수 선언
	
	private static Element[] queue = new Element[100 * 100 + 10];	// BFS 연산에 이용될 큐 관련 전역 변수 선언
	private static int enqueuePointer = 0, dequeuePointer = 0;		// 최근 삽입 위치 포인터와 최근 추출 위치 포인터 관련 전역 변수 선언
	
	private static int[] offsetWIDTH = { -1, 1, 0, 0 };		// 상하좌우 탐색 시 가로 오프셋 관련 배열 전역 변수 선언
	private static int[] offsetHEIGHT = { 0, 0, -1, 1 };	// 상하좌우 탐색 시 세로 오프셋 관련 배열 전역 변수 선언
	
	// 상하좌우 탐색에 사용할 루프 인덱스 및 탐색 현재 위치 좌표 기록용 전역 변수 선언
	private static int k = 0;
	private static int currWidth, currHeight;
	
	private static int result = 0;		// 미로 탈출 최종 시간 기록(즉, 너비 우선 탐색 최종 결과 저장)을 위한 전역 변수 선언
	
	/**
	 * 너비 우선 탐색 수행 내부 메소드
	 * 
	 * @return
	 */
	private static int BFS() {
		
		Element enqueueElem = new Element();	// 큐에 삽입 대상 원소 객체 선언
		Element dequeueElem = new Element();	// 큐로부터 추출한 원소 저장용 객체 선언
		
		/*
		 * STEP 1) 출발점 가로&세로 좌표 및 이동 시간을 0으로 초기화한 원소를 큐에 삽입하고, 방문한 미로의 출발점을 1로 표시
		 */
		enqueueElem.setWidth(START_WIDTH);
		enqueueElem.setHeight(START_HEIGHT);
		enqueueElem.setV(0);
		queue[enqueuePointer++] = enqueueElem;
		miro[START_WIDTH][START_HEIGHT] = 1;
		
		/*
		 * STEP 2) 삽입 위치 포인터가 추출 위치 포인터보다 큰 동안
		 * 		   즉, 큐에 데이터가 존재하는 동안 루프를 통해 반복
		 */
		while (enqueuePointer > dequeuePointer) {
		
			/*
			 * STEP 3) 큐에서 원소를 추출하여
			 */
			dequeueElem = queue[dequeuePointer++];
		
			/*
			 * STEP 4) 큐에서 꺼내 온 원소의 좌표를 기준으로 상하좌우 네 방향을 탐색하면서
			 */			
			for (k = 0; k < 4; k++) {
				currWidth = dequeueElem.getWidth() + offsetWIDTH[k];
				currHeight = dequeueElem.getHeight() + offsetHEIGHT[k];
				
				/*
				 * STEP 5) 탐색 대상 좌표가 미로의 범위 좌표를 벗어나는 경우 연산 스킵(즉, continue)
				 */
				if (currWidth < 1 || currWidth > WIDTH || currHeight < 1 || currHeight > HEIGHT) {
					continue;
				}
		
				/*
				 * STEP 6) 탐색 대상 좌표가 벽인 경우(즉, 1로 설정되어 있는 경우) 연산 스킵(즉, continue)
				 */
				if (miro[currHeight][currWidth] == 1) {
					continue;
				}
		
				/*
				 * STEP 7) 탐색 대상 좌표가 목적지인 경우, 추출된 큐 원소에 축적 기록되어 있는 이동 시간에 1을 증가한 후 결과 데이터로 반환 
				 */
				if (currWidth == FIN_Y && currHeight == FIN_X) {
					return dequeueElem.getV() + 1;
				}
		
				/*
				 * STEP 8) 탐색이 추가 진행되어야 하는 것으로 판단되었다면, 현 탐색 대상 좌표를 방문했었음을 나타내는 마킹을 하고
				 */
				miro[currHeight][currWidth] = 1;
		
				/*
				 * STEP 9) 현 탐색 대상 좌표 및 해당 좌표의 이동 시간을 1 증가시킨 후, 큐에 삽입하고 해당 삽입 위치 포인터를 1 증가시킴.
				 */
				enqueueElem.setWidth(currWidth);
				enqueueElem.setHeight(currHeight);
				enqueueElem.setV(dequeueElem.getV() + 1);
				queue[enqueuePointer++] = enqueueElem;
			}
		}
		
		return 0;
	}
>>>>>>> 714c92620b4418ec96a8297d6ddcc1834aeb1d7a:algorithm-study/src/main/java/io/ahnndroid/algorithmstudy/_01_미로탈출로봇/미로탈출로봇.java

	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
<<<<<<< HEAD:algorithm-study/src/main/java/io/ahnndroid/algorithmstudy/bfs/_01_미로탈출로봇/Main.java
=======
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		// 미로의 행렬 사이즈 콘솔 입력
		WIDTH = sc.nextInt();
		HEIGHT = sc.nextInt();
		sc.nextLine();
		
		// 출발점 가로&세로 좌표 콘솔 입력
		START_WIDTH = sc.nextInt();
		START_HEIGHT = sc.nextInt();
		
		// 도착점 가로가 될 Y &세로가 될 X 좌표 콘솔 입력
		FIN_X = sc.nextInt();
		FIN_Y = sc.nextInt();
		sc.nextLine();
		
		// 미로 배열 초기화 콘솔 입력
		for (int i = 1; i <= WIDTH; i++) {
			String record = sc.next();
			
			String[] fields = record.split("");
			for (int j = 1; j <= HEIGHT; j++) {
				miro[i][j] = Integer.valueOf(fields[j-1]).intValue();
			}
			
			sc.nextLine();
		}
		
		// 너비 우선 탐색을 이용한 미로 탐색 결과 도출
		result = BFS();
		System.out.println(result);
>>>>>>> 714c92620b4418ec96a8297d6ddcc1834aeb1d7a:algorithm-study/src/main/java/io/ahnndroid/algorithmstudy/_01_미로탈출로봇/미로탈출로봇.java
	}
}

/**
 * 큐에 삽입 또는 추출되는 원소 관련 클래스
 * 
 * @author ahnndroid
 *
 */
class Element {
	
	private int width;		// 해당 원소의 큐 삽입 시점의 가로 위치점
	private int height;		// 해당 원소의 큐 삽입 시점의 높이 위치점
	private int v;			// 해당 원소의 큐 삽입 시점의 이동 시간
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getV() {
		return v;
	}
	public void setV(int v) {
		this.v = v;
	}
	
}
