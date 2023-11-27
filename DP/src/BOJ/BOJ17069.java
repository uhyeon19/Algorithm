package BOJ;

import java.io.*;
import java.util.*;

public class BOJ17069 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int arr[][], N;
	static long[][][] dp;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(dp());
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new long[N][N][3]; // 0 = 가로, 1 = 세로, 2 = 대각선
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static long dp() {
		// dp에 담을 값(메모이제이션 할 값) = 파이프 (x, y)를 끝 점으로 해서 존재할 수 있는 경우의 수
		dp[0][1][0] = 1; // 초기 상태 지정
		for (int i = 0; i < N; i++) {
			for (int k = 2; k < N; k++) {
				// 1. 가로방향으로 파이프를 놓을 경우
				// 	왼쪽칸에서 가로, 대각일 때만 가능하다.
				if (k - 1 >= 0 && arr[i][k] == 0) {
					dp[i][k][0] = dp[i][k - 1][0] + dp[i][k - 1][2];
				} 
				// 2. 세로방향으로 파이프를 놓을 경우
				// 	위쪽칸에서 세로, 대각일 때만 가능하다.
				if (i - 1 >= 0 && arr[i][k] == 0) {
					dp[i][k][1] = dp[i - 1][k][1] + dp[i - 1][k][2];
				}
				// 3. 대각선 방향으로 파이프를 놓을 경우
				// 	왼쪽위의 칸에서 가로, 세로, 대각일 때 모두 가능하다.
				if (i - 1 >= 0 && k - 1 >= 0 && arr[i][k] == 0 && arr[i - 1][k] == 0 && arr[i][k - 1] == 0) {
					dp[i][k][2] = dp[i - 1][k - 1][0] + dp[i - 1][k - 1][1] + dp[i - 1][k - 1][2];
				}
			}
		}

		return dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
	}
}