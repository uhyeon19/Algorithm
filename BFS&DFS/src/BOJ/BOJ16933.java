package BOJ;

import java.io.*;
import java.util.*;

public class BOJ16933 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int arr[][], N, M, K, ans = 0;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean visited[][][][];

	static class Point {
		int x, y, skill, depth;
		boolean isDay;

		public Point(int x, int y, int depth, int skill, boolean isDay) {
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.skill = skill;
			this.isDay = isDay;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(bfs());
	}

	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M][K + 1][2];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int k = 0; k < M; k++) {
				arr[i][k] = str.charAt(k) - '0';
			}
		}
	}

	static int bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(0, 0, 1, K, true));
		visited[0][0][K][0] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (cur.x == N - 1 && cur.y == M - 1) return cur.depth;
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if (!isIn(nx, ny)) continue;
				
				if (arr[nx][ny] == 1 && cur.isDay && cur.skill > 0 && !visited[nx][ny][cur.skill - 1][1]) {
					visited[nx][ny][cur.skill - 1][1] = true;
					q.offer(new Point(nx, ny, cur.depth + 1, cur.skill - 1, false));
				}
				if (arr[nx][ny] == 0 && !visited[nx][ny][cur.skill][cur.isDay ? 1 : 0]) {
					visited[nx][ny][cur.skill][cur.isDay ? 1 : 0] = true;
					q.offer(new Point(nx, ny, cur.depth + 1, cur.skill, !cur.isDay));
				}
			}
			// 제자리
			if (!visited[cur.x][cur.y][cur.skill][cur.isDay ? 1 : 0]) {
				visited[cur.x][cur.y][cur.skill][cur.isDay ? 1 : 0] = true;
				q.offer(new Point(cur.x, cur.y, cur.depth + 1, cur.skill, !cur.isDay));
			}
		}
		return -1;
	}

	static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < M;
	}
}