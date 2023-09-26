package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2178 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, arr[][], ans = 1;
	static boolean visit[][];
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M)
			return false;
		return true;
	}

	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y));
		visit[x][y] = true;
		while (!q.isEmpty()) {
			int size = q.size();
			while (--size >= 0) {
				Point p = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];

					if (isIn(nx, ny) && !visit[nx][ny] && arr[nx][ny] == 1) {
						if (nx == N - 1 && ny == M - 1) return;
						q.offer(new Point(nx, ny));
						visit[nx][ny] = true;
					}
				}
			}
			ans++;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int k = 0; k < M; k++) {
				arr[i][k] = str.charAt(k) - '0';
			}
		}
		bfs(0, 0);
		System.out.println(ans + 1);
	}
}