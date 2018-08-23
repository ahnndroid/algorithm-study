package io.ahnndroid.algorithmstudy.recursion._01_1부터n까지합구하기;

/**
 * 재귀를 이용하여 누적합 구하기
 * 
 * @author ahnndroid
 *
 */
import java.util.Scanner;

public class Main {
	
	static int N;		// 입력값 저장을 위한 전역 변수
	static int sol;		// 결과를 담을 전역 변수
	
	/**
	 * 누적합 구하는 메소드
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	private static int add(int num1, int num2) {
		
		if (num1 == N / 2) {
			return num1 + num2;
		}
		
		return num1 + num2 + add(num1 + 1, num2 - 1);
	}

	public static void main(String[] args) {
		// 스캐너를 통해 콘솔 입력
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		
		sol = add(1, N);
		
		System.out.println(sol);
	}

}
