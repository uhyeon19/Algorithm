package BOJ;

import java.io.*;
import java.util.*;

public class BOJ14923 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, arr[][], ans = 0, startX, startY, endX, endY;
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

	public static int bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(x, y, 0, 1));
		visited[0][0][1] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(cur.x == endX && cur.y == endY) return cur.depth;
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
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken()) - 1;
		startY = Integer.parseInt(st.nextToken()) - 1;
		st = new StringTokenizer(br.readLine());
		endX = Integer.parseInt(st.nextToken()) - 1;
		endY = Integer.parseInt(st.nextToken()) - 1;
		visited = new boolean[N][M][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(bfs(startX, startY));
		System.out.println();
	}
}