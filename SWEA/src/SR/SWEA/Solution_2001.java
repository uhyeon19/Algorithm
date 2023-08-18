package SR.SWEA;

import java.io.*;
import java.util.*;

public class Solution_2001 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, N, M, ans, arr[][];

	public static void init() throws IOException {	// 입력 값 받아서 세팅하기!
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];
		ans = Integer.MIN_VALUE;	// 정답 값은 가장 큰 값을 입력할 것이니 초기값은 가장 작게 줘서 비교 시 무조건 바뀌게!

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = 1;
			while (st.hasMoreTokens()) {
				arr[i + 1][cnt++] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void makeAddArr() {	// 2차원 배열 누적합 구하기
		for(int i = 1; i <= N; i++) {
			for(int k = 1; k <= N; k++) {
				arr[i][k] = arr[i][k - 1] + arr[i - 1][k] - arr[i - 1][k - 1] + arr[i][k];
				// 누적합 배열
			}
		}
	}
	
	public static void solve() {
		int x1 = 1, y1 = 1;	// 처음 시작 구간 (1, 1) ~ (M, M)
		int x2 = M, y2 = M;
		for(int i = 0; i <= N - M; i++) {	// N - M 만큼 이동할 수 있음
			for(int j = 0; j <= N - M; j++) {
				int sum = arr[x2][y2] - arr[x1 - 1][y2] - arr[x2][y1 - 1] + arr[x1 - 1][y1 - 1];
				// 구간 누적합 구하기
				ans = Integer.max(ans, sum);	// 구간 누적합과 ans를 비교하여 더 큰 값을 담아 ans를 만든다
				y1++;	// 다음 구간
				y2++;	// 다음 구간
			}
			x1++;	// y를 키워 N에 맞닿았으니 x를 한 칸 키워 아래쪽을 확인한다.
			x2++;
			y1 = 1;	// y1는 다시 1로 초기화
			y2 = M;	// y2는 다시 M으로 초기화
			// ex)
			// 1. (1, 1) ~ (2, 2)
			// 2. (1, 2) ~ (2, 3)
			// 		...
			// 3. (2, 1) ~ (3, 2)
			// 4. (2, 2) ~ (3, 3)
			//		...
		}
		sb.append(ans).append('\n');
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			sb.append("#").append(i + 1 + " ");
			init();
			makeAddArr();
			solve();
		}
		System.out.print(sb.toString());
	}
}
