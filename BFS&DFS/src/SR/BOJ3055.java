package SR;

import java.io.*;
import java.util.*;

public class BOJ3055 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C;
	static char arr[][], copyArr[][];
	static boolean visited[][];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Point start, end;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < R && ny < C;
	}

	public static void waterMove() {
		copyArr = new char[R][C];
		for(int i = 0; i < R; i++) {
			copyArr[i] = arr[i].clone();
		}
		
		for(int i = 0; i < R; i++) {
			for(int k = 0; k < C; k++) {
				if(arr[i][k] == '*') {
					for(int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = k + dy[d];
						if(isIn(nx, ny) && arr[nx][ny] == '.') {
							copyArr[nx][ny] = '*';
						}
					}
				}
			}
			arr[i] = copyArr[i].clone();
		}
	}
	
	public static String bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(start);
		visited[start.x][start.y] = true;
		int ans = 0;
		while(!q.isEmpty()) {
			waterMove();
			int size = q.size();
			while(--size >= 0) {
				Point cur = q.poll();
				for(int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					if(nx == end.x && ny == end.y) return String.valueOf(ans + 1);
					if(isIn(nx, ny) && copyArr[nx][ny] == '.' && !visited[nx][ny]) {
						q.offer(new Point(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
			ans++;
		}
		return "KAKTUS";
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int k = 0; k < C; k++) {
				arr[i][k] = str.charAt(k);
				if(arr[i][k] == 'S') {
					start = new Point(i, k);
				} else if(arr[i][k] == 'D') {
					end = new Point(i, k);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(bfs());
	}
}