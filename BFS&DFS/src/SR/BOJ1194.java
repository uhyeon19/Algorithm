package SR;

import java.io.*;
import java.util.*;

public class BOJ1194 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static char map[][];
	static boolean visited[][][];
	static int N, M;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Point start;

	static class Point {
		int x, y, depth, skill;
		boolean alpha[] = new boolean[7];

		public Point(int x, int y, int depth, int skill, boolean alpha[]) {
			super();
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.skill = skill;
			this.alpha = alpha;
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
		visited = new boolean[N][M][50];
		boolean alpha[] = new boolean[7];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int k = 0; k < M; k++) {
				map[i][k] = str.charAt(k);
				if (map[i][k] == '0') {
					start = new Point(i, k, 0, 0, alpha);
					map[i][k] = '.';
				}
			}
		}
	}

	public static int bfs(Point start) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(start);
		visited[start.x][start.y][start.skill] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if(isIn(nx, ny) && map[nx][ny] != '#' && !visited[nx][ny][cur.skill]) {
					if(map[nx][ny] == '1') return cur.depth + 1;
					if(map[nx][ny] == '.') {
						visited[nx][ny][cur.skill] = true;
						q.offer(new Point(nx, ny, cur.depth + 1, cur.skill, cur.alpha));
					} else {
						for(int i = 1; i <= 6; i++) {
							if(map[nx][ny] == i + 'a' - 1) {
								visited[nx][ny][cur.skill + 1] = true;
								boolean tmp[] = cur.alpha.clone();
								tmp[i] = true;
								q.offer(new Point(nx, ny, cur.depth + 1, cur.skill + 1, tmp));
								break;
							} else if(map[nx][ny] == i + 'A' - 1) {
								if(cur.alpha[i]) {
									visited[nx][ny][cur.skill] = true;
									q.offer(new Point(nx, ny, cur.depth + 1, cur.skill, cur.alpha));
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
