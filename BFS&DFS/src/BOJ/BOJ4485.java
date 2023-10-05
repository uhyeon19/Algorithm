package BOJ;

import java.io.*;
import java.util.*;

public class BOJ4485 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, arr[][], dp[][];
	static int []dx = { -1, 0, 1, 0 };
	static int []dy = { 0, 1, 0, -1 };
	static boolean visited[][];
	static PriorityQueue<Road> pq;
	
	static class Road implements Comparable<Road> {
		int x, y, cost;

		public Road(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Road o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws IOException {
		run();
	}
	
	static void run() throws IOException {
		int cnt = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			init();
			sb.append("Problem " + cnt++ + ": " + bfs() + "\n");
		}
		System.out.println(sb.toString());
	}
	
	static void init() throws IOException {
		arr = new int[N][N];
		dp = new int[N][N];
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static int bfs() {
		pq = new PriorityQueue<>();
		pq.offer(new Road(0, 0, arr[0][0]));
		dp[0][0] = arr[0][0];
		visited[0][0] = true;
		
		while(!pq.isEmpty()) {
			Road cur = pq.poll();
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if(!isIn(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				dp[nx][ny] = cur.cost + arr[nx][ny];
				visited[nx][ny] = true;
				pq.offer(new Road(nx, ny, dp[nx][ny]));
			}
		}
		return dp[N - 1][N - 1];
	}
	
	static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}
}