package BOJ;

import java.util.*;
import java.io.*;

public class BOJ3184 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, wolfAll = 0, sheepAll = 0;
	static char arr[][];
	static boolean visit[][];
	static int []dx = {1, -1, 0, 0};
	static int []dy = {0, 0, 1, -1};
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static boolean isIn(int nx, int ny) {
		if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
			return false;
		}
		return true;
	}
	
	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y));
		visit[x][y] = true;
		int wolf = 0;
		int sheep = 0;
		if(arr[x][y] == 'v') wolf++;
		else sheep++;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if(isIn(nx, ny) && !visit[nx][ny]) {
					if(arr[nx][ny] == '.') {
						q.offer(new Point(nx, ny));
						visit[nx][ny] = true;						
					} else if(arr[nx][ny] == 'v') {
						q.offer(new Point(nx, ny));
						visit[nx][ny] = true;	
						wolf++;
					} else if(arr[nx][ny] == 'o') {
						q.offer(new Point(nx, ny));
						visit[nx][ny] = true;	
						sheep++;
					}
				}
			}
		}
		if(wolf < sheep) wolf = 0;
		else sheep = 0;
		wolfAll += wolf;
		sheepAll += sheep;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		visit = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int k = 0; k < C; k++) {
				arr[i][k] = str.charAt(k);
			}
		}
		
		for(int i = 0; i < R; i++) {
			for(int k = 0; k < C; k++) {
				if(!visit[i][k] && (arr[i][k] == 'v' || arr[i][k] == 'o')) {
					bfs(i, k);
				}
			}
		}
		System.out.println(sheepAll + " " + wolfAll);
	}
}
