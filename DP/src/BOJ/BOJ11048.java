package BOJ;

import java.io.*;
import java.util.*;

public class BOJ11048 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int arr[][], dp[][], N, M;
	static int[] dx = {1, 1, 0};	// 밑, 대각선 밑, 오른쪽
	static int[] dy = {0, 1, 1};
	
	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < M;
	}
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		dp = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static int dp() {
		dp[0][0] = arr[0][0];
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < M; k++) {
				for(int d = 0; d < 3; d++) {
					int nx = i + dx[d];
					int ny = k + dy[d];
					if(isIn(nx, ny)) dp[nx][ny] = Integer.max(dp[nx][ny], dp[i][k] + arr[nx][ny]);
				}
			}
		}
		return dp[N - 1][M - 1];
	}
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(dp());
	}
}