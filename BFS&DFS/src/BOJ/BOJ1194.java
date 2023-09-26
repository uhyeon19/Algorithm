package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1194 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static char map[][];
	static int N, M;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Point start;

	static class Point {
		int x, y, depth;
		boolean alpha[] = new boolean[7];
		boolean visit[][] = new boolean[N][M];

		public Point(int x, int y, int depth, boolean alpha[], boolean visit[][]) {
			super();
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.alpha = alpha;
			this.visit = visit;
		}
	}

	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < M;
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		boolean alpha[] = new boolean[7];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int k = 0; k < M; k++) {
				map[i][k] = str.charAt(k);
				if (map[i][k] == '0') {
					start = new Point(i, k, 0, alpha, new boolean[N][M]);
					map[i][k] = '.';
				}
			}
		}
	}

	public static int bfs(Point start) {
		Queue<Point> q = new ArrayDeque<>();
		start.visit[start.x][start.y] = true;
		start.alpha[0] = true;
		q.offer(start);

		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if (isIn(nx, ny) && !cur.visit[nx][ny] && map[nx][ny] != '#') {
					if (map[nx][ny] == '1')
						return cur.depth + 1;
					if (map[nx][ny] == '.') {
						cur.visit[nx][ny] = true;
						q.offer(new Point(nx, ny, cur.depth + 1, cur.alpha, cur.visit));
					} else {
						for (int i = 1; i <= 6; i++) {
							if (map[nx][ny] == i + 'a' - 1) {
								if (!cur.alpha[i]) {
									boolean tmpVisit[][] = new boolean[N][M];
									tmpVisit[nx][ny] = true;
									boolean tmpAlpha[] = cur.alpha.clone();
									tmpAlpha[i] = true;
									q.offer(new Point(nx, ny, cur.depth + 1, tmpAlpha, tmpVisit));
								} else {
									cur.visit[nx][ny] = true;
									q.offer(new Point(nx, ny, cur.depth + 1, cur.alpha, cur.visit));	
								}
								break;
							} else if (map[nx][ny] == i + 'A' - 1) {
								if (cur.alpha[i]) {
									cur.visit[nx][ny] = true;
									q.offer(new Point(nx, ny, cur.depth + 1, cur.alpha, cur.visit));
								}
								break;
							}
						}
					}
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(bfs(start));
	}
}
