/*
 * https://blog.encrypted.gg/945
 * https://st-lab.tistory.com/118
 * 
 * ex) 4 X 4 배열 일 경우
 * 0 : 퀸을 놓지 않은 곳
 * 1 : 퀸을 놓은 곳
 * 
 * 0 1 0 0
 * 0 0 0 1
 * 1 0 0 0
 * 0 0 1 0
 * 
 * 우리는 퀸을 놓을 수 있는 위치가 한정되어 있다.
 * 퀸은 팔방으로 움직일 수 있기 때문에 우리가 퀸을 놓을 수 있는 위치는
 * 각 행에 1개, 각 열에 1개로 위와 같이 만들어진다.
 * 위와 같은 모양을 인덱스화 하여 살펴보게 된다면
 * {2, 0, 3, 1}로 만들 수 있다.
 * 이를 재귀호출 함수로 짜보자.
 */

package BOJ;

import java.io.*;

public class BOJ9663 {
	public static int[] arr;
	public static int N;
	public static int count = 0;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
 
		nQueen(0);
		System.out.println(count);
	}
 
	public static void nQueen(int depth) {
		// 모든 원소를 다 채운 상태면 count 증가 및 return 
		if (depth == N) {
			count++;
			return;
		}
 
		for (int i = 0; i < N; i++) {
			arr[depth] = i;
			// 놓을 수 있는 위치일 경우 재귀호출
			if (Possibility(depth)) {
				nQueen(depth + 1);
			}
		}
	}
 
	public static boolean Possibility(int col) {
		for (int i = 0; i < col; i++) {
			// 해당 열의 행과 i열의 행이 일치할경우 (같은 행에 존재할 경우) 
			if (arr[col] == arr[i]) {
				return false;
			} 
			
			/*
			 * 대각선상에 놓여있는 경우
			 * (열의 차와 행의 차가 같을 경우가 대각선에 놓여있는 경우)
			 */
			else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
				return false;
			}
		}
		return true;
	}
}