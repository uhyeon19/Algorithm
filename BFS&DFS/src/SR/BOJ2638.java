package SR;

import java.io.*;
import java.util.*;

public class BOJ2638 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, arr[][], tmpArr[][];
	static boolean visit[][];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
			return false;
		}
		return true;
	}

	public static void bfs(int x, int y) {
		visit = new boolean[N][M];
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y));
		tmpArr = new int[N][M];
		visit[x][y] = true;
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if (isIn(nx, ny) && !visit[nx][ny]) {
					if (arr[nx][ny] == 0) {
						q.offer(new Point(nx, ny));
						visit[nx][ny] = true;
					} else if (arr[nx][ny] == 1) {
						tmpArr[nx][ny]++;
					}
				}
			}
		}
	}

	public static boolean isFin() {
		boolean result = true;
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				if (tmpArr[i][k] >= 2)
					arr[i][k] = 0;
				if (arr[i][k] == 1)
					result = false;
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		do {
			bfs(0, 0);
			ans++;
		} while (!isFin());
		System.out.println(ans);
	}
}