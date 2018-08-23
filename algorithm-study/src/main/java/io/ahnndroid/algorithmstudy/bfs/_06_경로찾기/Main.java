package io.ahnndroid.algorithmstudy.bfs._06_경로찾기;

/**
 * 
[ 문제 설명 ]
길이가 같은 두 개의 이진수 코드 A와 B가 있다고 하자. 
이 두 코드 사이의 해밍 거리는 A와 B의 각 비트를 왼쪽부터 오른쪽으로 차례대로 비교할 때 서로 다른 값을 가진 비트의 수이다. 
예를 들어, A=010010, B=011011 이라고 하면, 세 번째 비트와 여섯 번째 비트만 서로 다르므로 
이 두 코드 사이의 해밍 거리는 2이다.

우리는 총 N개의 이진 코드를 가지고 있고, 각 코드의 길이는 K로 같다.
예를 들어, 다음과 같이 길이가 3인 5개의 이진 코드들이 있다.
A=000, B=111, C=010, D=110, E=001
두 코드 사이의 해밍 거리를 H(A,B)로 표현한다. 
그러면, H(A,B)=3, H(A,C)=1, H(A,D)=2, H(A,E)=1 이다.

우리는 이진 코드들에 대해 해밍 경로를 찾고자 한다. 
해밍 경로는 모든 인접한 두 코드사이의 해밍 거리가 1인 경로이다. 
위의 예에서 (A,C,D)는 코드 A와 C의 해밍 거리가 1이고, 코드 C와 D의 해밍 거리가 1이므로 해밍 경로이다. 
(A,E,B)는 코드 A와 E의 해밍 거리는 1이지만, 코드 E와 B의 해밍 거리가 2이므로 해밍 경로가 아니다.
이 문제는 주어진 두 코드 사이에 가장 짧은 해밍 경로를 구하는 프로그램을 작성하는 것이다.


[ 입력 ]
첫째 줄에는 두 개의 정수 N과 K가 빈칸을 사이에 두고 주어진다(3≤N≤1,000, 2≤K≤30).
둘째 줄부터 N개의 줄에는 각 줄마다 길이가 K인 이진 코드가 하나씩 주어진다. 하나의 코드는 빈칸이 없이 주어진다.
코드들은 주어진 순서대로 1부터 N까지의 번호로 구분된다. 코드가 같은 경우는 없다.
그 다음 줄에는 해밍 경로를 찾고자 하는 서로 다른 두 개의 코드 A와 B가 각각의 코드번호로 주어진다.


[ 출력 ]
입력으로 주어진 두 코드 사이에 해밍 경로가 존재하면 그 경로 중 
가장 짧은 경로를 코드 A부터 코드 B까지 경로상의 코드 번호로 출력한다. 
코드 번호를 출력할 경우에는 코드 번호 사이에 하나의 빈칸을 사이에 두고 출력한다.
만약 답이 여러 개 있으면 그 중에 하나만 출력하고, 경로가 존재하지 않으면 -1을 출력한다.


[ 입력 예시 ]
5 3
000
111
010
110
001
1 2


[ 출력 예시 ]
1 3 4 2

 * @author ahnndroid
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
