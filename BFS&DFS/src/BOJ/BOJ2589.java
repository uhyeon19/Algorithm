package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2589 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M;
	static char originArr[][];
	static boolean visit[][];
	static PriorityQueue<Integer> ans = new PriorityQueue<>(Collections.reverseOrder());
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

	public static void BFS(int x, int y) {
		visit = new boolean[N][M];
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y));
		visit[x][y] = true;
		int cnt = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			while (--size >= 0) {
				Point p = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					if (isIn(nx, ny) && originArr[nx][ny] == 'L' && !visit[nx][ny]) {
						q.offer(new Point(nx, ny));
						visit[nx][ny] = true;
					}
				}
			}
			cnt++;
		}
		ans.offer(cnt - 1);
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		originArr = new char[N][M];

		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int k = 0; k < M; k++) {
				originArr[i][k] = tmp.charAt(k);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (originArr[i][j] == 'L') {
					BFS(i, j);
				}
			}
		}

		System.out.println(ans.poll());
	}
}