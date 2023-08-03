package SR;

import java.util.*;
import java.io.*;

public class BOJ1012 {
	static int TC, M, N, K, ans;
	static int[][] arr;
	static boolean[][] visit;
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[M][N];
		visit = new boolean[M][N];
		ans = 0;
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x][y] = 1;
		}
	}
	
	public static boolean isIn(int nx, int ny) {
		if(nx < 0 || ny < 0 || nx >= M || ny >= N) {
			return false;
		}
		return true;
	}
	
	public static void DFS(int x, int y) {
		visit[x][y] = true;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!isIn(nx, ny)) {
				continue;
			}
			
			if(!visit[nx][ny] && arr[nx][ny] == 1) {
				DFS(nx, ny);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < TC; tc++) {
			init();
			for(int i = 0; i < M; i++) {
				for(int k = 0; k < N; k++) {
					if(!visit[i][k] && arr[i][k] == 1) {
						DFS(i, k);
						ans++;
					}
				}
			}
			sb.append(ans).append('\n');
		}
		System.out.print(sb.toString());
	}
}
