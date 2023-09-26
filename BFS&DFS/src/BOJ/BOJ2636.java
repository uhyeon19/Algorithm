package BOJ;

import java.util.*;
import java.io.*;

public class BOJ2636 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, cnt, arr[][];
	static boolean visit[][];
	static int []dx = {-1, 0, 1, 0};
	static int []dy = {0, 1, 0, -1};
	static List<Integer> ans = new ArrayList<>();

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isFin() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < M; k++) {
				if(arr[i][k] == 1) return false;
			}
		}
		return true;
	}
	
	public static boolean isIn(int nx, int ny) {
		if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
			return false;
		}
		return true;
	}
	
	public static void BFS() {
		visit = new boolean[N][M];
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		visit[0][0] = true;
		int answer = 0;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if(isIn(nx, ny)) {
					if(arr[nx][ny] == 0 && !visit[nx][ny]) {
						q.offer(new Point(nx, ny));
						visit[nx][ny] = true;						
					} else if(arr[nx][ny] == 1 && !visit[nx][ny]) {
						answer++;
						arr[nx][ny] = 0;
						visit[nx][ny] = true;
					}
				}
			}
		}
		ans.add(answer);
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		cnt = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(!isFin()) {
			BFS();
			cnt++;
		}
		
		sb.append(cnt).append('\n').append(ans.get(ans.size() - 1));
		System.out.println(sb.toString());
	}
}