package BOJ;

import java.io.*;
import java.util.*;

public class BOJ7562 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int T, N;
	static int []dx = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int []dy = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static Point start, end;
	
	static class Point {
		int x, y, depth;

		public Point(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
	
	static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}
	
	static int BFS() {
		if(start.x == end.x && start.y == end.y) return 0;
		Queue<Point> q = new ArrayDeque<>();
		boolean visited[][] = new boolean[N][N];
		q.offer(start);
		visited[start.x][start.y] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int d = 0; d < 8; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if(!isIn(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(nx == end.x && ny == end.y) {
					return p.depth + 1;
				}
				q.offer(new Point(nx, ny, p.depth + 1));
				visited[nx][ny] = true;
			}
		}
		return -1;
	}
	
	static void init() throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			start = new Point(startX, startY, 0);
			st = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			end = new Point(endX, endY, 0);
			sb.append(BFS()).append("\n");
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(sb.toString());
	}
}
