package SR.SWEA;

import java.io.*;
import java.util.*;

public class Solution_1868 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[] dx = { -1, 0, 1, 0, -1, 1, 1, -1 };
	static int[] dy = { 0, 1, 0, -1, 1, 1, -1, -1 };
	static int arr[][], T, N, ans;
	static boolean visit[][];

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
			return false;
		}
		return true;
	}

	public static void BFS(int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(x, y));
		visit[x][y] = true;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			for (int i = 0; i < 8; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (isIn(nx, ny) && arr[nx][ny] >= 0 && !visit[nx][ny]) {
					visit[nx][ny] = true;
					if(arr[nx][ny] == 0) queue.offer(new Point(nx, ny));
				}
			}
		}
		ans++;
	}

	public static void solve() {
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				if (arr[i][k] == 0 && !visit[i][k]) {
					BFS(i, k);
				}
			}
		}
	}

	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		ans = 0;
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int k = 0; k < N; k++) {
				if (str.charAt(k) == '*') {
					arr[i][k] = -9;
					visit[i][k] = true;
					for (int d = 0; d < 8; d++) {
						int nx = i + dx[d];
						int ny = k + dy[d];
						if (isIn(nx, ny)) {
							arr[nx][ny]++;
						}
					}
				}
			}
		}
	}

	public static void another() {
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				if (!visit[i][k])
					ans++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solve();
			another();
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.print(sb.toString());
	}
}
