package BOJ;

import java.io.*;
import java.util.*;

public class BOJ4963_BFS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int w, h, ans, rand[][];
	static boolean [][]visit;
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dy = {0, 1, 0, -1, 1, -1, 1, -1};
	
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static boolean isIn(int nx, int ny) {
		if(nx < 0 || ny < 0 || nx >= w || ny >= h) {
			return false;
		}
		return true;
	}
	
	public static void BFS(int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(x, y));
		visit[x][y] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 8; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(isIn(nx, ny) && rand[nx][ny] == 1 && !visit[nx][ny]) {
					visit[nx][ny] = true;
					queue.offer(new Point(nx, ny));
				}
			}
		}
		ans++;
	}
	
	public static void main(String[] args) throws IOException {
		while(true) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			if(w == 0 && h == 0) break;
			rand = new int[w][h];
			visit = new boolean[w][h];
			ans = 0;
			for(int i = 0; i < w; i++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < h; k++) {
					rand[i][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < w; i++) {
				for(int k = 0; k < h; k++) {
					if(rand[i][k] == 1 && !visit[i][k]) {
						BFS(i, k);
					}
				}
			}
			
			sb.append(ans + "\n");
		}
		System.out.print(sb.toString());
	}
}
