package SR;

import java.io.*;
import java.util.*;

public class BOJ2206 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, arr[][], ans = 0, tmp[][];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean visited[][][];

	static class Point {
		int x, y, skill, depth;

		public Point(int x, int y, int depth, int skill) {
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.skill = skill;
		}
	}

	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < M;
	}

	public static int bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(0, 0, 1, 1));
		visited[0][0][1] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(cur.x == N - 1 && cur.y == M - 1) return cur.depth;
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if(isIn(nx, ny) && !visited[nx][ny][cur.skill]) {
					if(arr[nx][ny] == 0) {
						visited[nx][ny][cur.skill] = true;
						q.offer(new Point(nx, ny, cur.depth + 1, cur.skill));
					} else {
						if(cur.skill == 1) {
							visited[nx][ny][cur.skill - 1] = true;
							q.offer(new Point(nx, ny, cur.depth + 1, cur.skill - 1));
						}
					}
				}
			}
		}
		return -1;
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M][2];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int k = 0; k < M; k++) {
				arr[i][k] = str.charAt(k) - '0';
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(bfs());
		System.out.println();
	}
}