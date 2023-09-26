package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2468 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, arr[][], copyArr[][], ans = 1, max = Integer.MIN_VALUE;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean visited[][];

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}

	public static void rain(int height) {
		copyArr = new int[N][N];
		for (int i = 0; i < N; i++) {
			copyArr[i] = arr[i].clone();
			for (int k = 0; k < N; k++) {
				copyArr[i][k] = copyArr[i][k] - height < 0 ? 0 : copyArr[i][k] - height;
			}
		}
	}

	public static void bfs(Point p) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(p);
		visited[p.x][p.y] = true;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if(isIn(nx, ny) && !visited[nx][ny] && copyArr[nx][ny] != 0) {
					q.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}

	public static void run() {
		while (max > 0) {
			rain(--max);
			visited = new boolean[N][N];
			int res = 0;
			for (int i = 0; i < N; i++) {
				for (int k = 0; k < N; k++) {
					if (copyArr[i][k] != 0 && !visited[i][k]) {
						bfs(new Point(i, k));
						res++;
					}
				}
			}
			ans = Integer.max(ans, res);
		}
	}

	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
				max = Integer.max(max, arr[i][k]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		run();
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}
