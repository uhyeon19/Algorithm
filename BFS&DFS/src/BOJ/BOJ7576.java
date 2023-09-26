package BOJ;

import java.io.*;
import java.util.*;

public class BOJ7576 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int M, N, arr[][], maxDay = Integer.MIN_VALUE;
	static int []dx = {-1, 0, 1, 0};
	static int []dy = {0, 1, 0, -1};
	static Queue<Point> q = new LinkedList<>();
	
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static boolean isZero() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < M; k++) {
				if(arr[i][k] == 0) return true;
			}
		}
		return false;
	}
	
	public static boolean isIn(int nx, int ny) {
		if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
			return false;
		}
		return true;
	}
	
	public static void BFS() {
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(isIn(nx, ny) && arr[nx][ny] == 0) {
					arr[nx][ny] += arr[p.x][p.y] + 1;
					q.offer(new Point(nx, ny));
				}
			}
		}
		
		if(isZero()) {
			System.out.println("-1");
			return;
		} else {
			for(int i = 0; i < N; i++) {
				for(int k = 0; k < M; k++) {
					maxDay = Integer.max(maxDay, arr[i][k]);
				}
			}
			System.out.println(maxDay - 1);
			return;
		}
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < M; k++) {
				if(arr[i][k] == 1) {
					q.offer(new Point(i, k));
				}
			}
		}
		BFS();
	}
}
