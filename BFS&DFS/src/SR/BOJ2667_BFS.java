package SR;

import java.util.*;
import java.io.*;

public class BOJ2667_BFS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, arr[][], cnt;
	static PriorityQueue<Integer> ans = new PriorityQueue<>();
	static boolean visit[][];
	static int []dx = {0, 0, 1, -1};
	static int []dy = {1, -1, 0, 0};
	static Queue<Point> q = new LinkedList<>();
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static boolean isIn(int nx, int ny) {
		if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
			return false;
		}
		return true;
	}
	
	public static void bfs(int x, int y) {
		int cnt = 1;
		visit[x][y] = true;
		q.offer(new Point(x, y));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				
				if(isIn(nx, ny) && !visit[nx][ny] && arr[nx][ny] == 1) {
					q.offer(new Point(nx, ny));
					visit[nx][ny] = true;
					cnt++;
				}
			}
		}
		ans.add(cnt);
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visit = new boolean[N][N];
		cnt = 0;
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int k = 0; k < str.length(); k++) {
				arr[i][k] = str.charAt(k) - '0';
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(!visit[i][k] && arr[i][k] == 1) {
					bfs(i, k);
					cnt++;
				}
			}
		}
		sb.append(cnt).append('\n');
		while(!ans.isEmpty()) {
			sb.append(ans.poll()).append('\n');
		}
		
		System.out.print(sb.toString());
	}
}