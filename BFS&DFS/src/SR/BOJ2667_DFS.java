package SR;

import java.util.*;
import java.io.*;

public class BOJ2667_DFS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, arr[][], cnt, count;
	static PriorityQueue<Integer> ans = new PriorityQueue<>();
	static boolean visit[][];
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
			return false;
		}
		return true;
	}

	public static void dfs(int x, int y) {
		visit[x][y] = true;

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (isIn(nx, ny) && !visit[nx][ny] && arr[nx][ny] == 1) {
				dfs(nx, ny);
				count++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visit = new boolean[N][N];
		cnt = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int k = 0; k < str.length(); k++) {
				arr[i][k] = str.charAt(k) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				if (!visit[i][k] && arr[i][k] == 1) {
					count = 1;
					dfs(i, k);
					ans.add(count);
					cnt++;
				}
			}
		}
		sb.append(cnt).append('\n');
		while (!ans.isEmpty()) {
			sb.append(ans.poll()).append('\n');
		}

		System.out.print(sb.toString());
	}
}