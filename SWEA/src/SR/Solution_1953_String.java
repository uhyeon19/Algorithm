package SR;

import java.io.*;
import java.util.*;

public class Solution_1953_String {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int arr[][], T, N, M, curR, curC, L, ans;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, 1, -1, 0 };
	static String[] type = new String[8];
	static boolean visited[][];

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M)
			return false;
		return true;
	}

	public static void addVisit() {
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				if (visited[i][k]) ans++;
			}
		}
	}
	
	public static void solve() {
		int count = 1;
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(curR, curC));
		visited[curR][curC] = true;
		while(!q.isEmpty() && L != 1) {
			int size = q.size();
			while(--size >= 0) {				
				Point p = q.poll();
				String curType = type[arr[p.x][p.y]];
				for(int i = 0; i < curType.length(); i++) {
					int d = curType.charAt(i) - '0';
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					if(isIn(nx, ny) && arr[nx][ny] != 0 && !visited[nx][ny]) {
						String nextType = type[arr[nx][ny]];
						if(nextType.contains(String.valueOf(3 - d))) {
							q.offer(new Point(nx, ny));
							visited[nx][ny] = true;
						}
					}
				}
			}
			count++;
			if(count == L) break;
		}
		addVisit();
	}

	public static void initType() {
		type[1] = "0123";
		type[2] = "03";
		type[3] = "12";
		type[4] = "01";
		type[5] = "13";
		type[6] = "23";
		type[7] = "02";
	}
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		curR = Integer.parseInt(st.nextToken());
		curC = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		ans = 0;
		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		initType();
		for (int t = 1; t <= T; t++) {
			init();
			solve();
			sb.append("#" + t + " " + ans + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}