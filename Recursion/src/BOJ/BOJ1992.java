package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1992 {
	// 입력 값을 위한 bufferedreader
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 출력하기 위한 bufferedwriter
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	// 출력값을 저장하기 위한 stringbuilder
	static StringBuilder sb = new StringBuilder();
	// 배열의 크기, 배열
	static int N, arr[][];

	// 재귀호울
	/**
	 * 분할 정복을 위해 사용하는 메소드
	 * @param r	(시작 Row 값)
	 * @param c	(시작 Col 값)
	 * @param size	(얼마나 살펴볼 건지에 대한 size)
	 */
	public static void solve(int r, int c, int size) {
		if (check(r, c, size)) {	// r~r+size까지, c~c+size 배열 탐색 ==> 전부 0이거나 1이면 true
			sb.append(arr[r][c]);	// 조건이 true라는 것은 압축이 가능하다는 것. 압축된 값을 출력값으로 저장한다.
			return;
		}

		// 위에서 걸리지 않았다는 것은 압축이 불가했다는 뜻.
		// 4등분 내줘야한다.
		// 4등분을 하면 각 변을 살펴볼 사이즈가 반으로 준다.
		// 반으로 나눈 사이즈 nextSize
		int nextSize = size / 2;
		
		// ( 를 처음에 붙임으로서 압축시작 알림
		sb.append("(");
		
		// 왼쪽 위(2사분면)
		// 2사분면은 r, c가 size/2길이만큼 탐색된다.
		solve(r, c, nextSize);

		// 오른쪽 위(1사분면)
		// 1사분면은 r, c + size/2가 size/2길이만큼 탐색된다.
		solve(r, c + nextSize, nextSize);

		// 왼쪽 밑(3사분면)
		// 3사분면은 r + size/2, c가 size/2길이만큼 탐색된다.
		solve(r + nextSize, c, nextSize);

		// 오른쪽 밑(4사분면)
		// 4사분면은 r + size/2, c + size/2가 size/2길이만큼 탐색된다.
		solve(r + nextSize, c + nextSize, nextSize);
		
		// 4등분 한 것 돌고 나면 압축 한 번 완료로 ) 넣기
		sb.append(")");
	}

	/**
	 * 압축을 할 수 있는지 없는지 체킹하는 메소드
	 * @param r	(시작 Row 값)
	 * @param c	(시작 Col 값)
	 * @param size	(얼마나 살펴볼 건지에 대한 size)
	 * @return
	 */
	public static boolean check(int r, int c, int size) {
		int basic = arr[r][c];	// 살펴볼 구역의 첫번째 값
		for(int i = r; i < r + size; i++) {	// 살펴볼 구역: r부터 r + size까지
			for(int k = c; k < c + size; k++) {	// 살펴볼 구역: c부터 c + size까지
				if(arr[i][k] != basic) {	// 처음 가져왔던 값과 다르다면
					return false;	// 0으로 다 차있거나, 1로 다 차있지 않은 상황
				}
			}
		}
		return true;	// 모두 0으로 가득찼거나, 1로 가득차있다면 true
	}
	
	/**
	 * main 메소드
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int k = 0; k < N; k++) {
				arr[i][k] = str.charAt(k) - '0';
			}
		}
		// 기본 init() -> 초깃값 세팅
		
		// 재귀 함수 시작
		solve(0, 0, N);	// 0, 0부터 시작하고, N의 길이만큼 탐색(처음은 전체 탐색이니까!)
		
		// 재귀함수를 잘 거치고 왔다면, sb가 맞게 차있을 것이다.
		// 이를 출력해준다.
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}