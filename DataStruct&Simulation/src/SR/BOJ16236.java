package SR;

import java.io.*;
import java.util.*;

public class BOJ16236 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, arr[][], curSize = 2, eatCnt = 0, ans = 0;
	static int[] dx = { -1, 0, 0, 1};
	static int[] dy = { 0, -1, 1, 0 };
	static boolean visited[][];
	static Point start;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= N)
			return false;
		return true;
	}

	public static boolean isAllZero() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(arr[i][k] != 0) return false;
			}
		}
		return true;
	}
	
	public static boolean canEat() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(arr[i][k] > 0 && arr[i][k] < curSize) return true;
			}
		}
		return false;
	}

	public static boolean bfs() {
		visited = new boolean[N][N];
		Queue<Point> q = new ArrayDeque<>();
		PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o1.x == o2.x ? o1.y - o2.y : o1.x - o2.x);
		q.offer(start);
		visited[start.x][start.y] = true;
		int cnt = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			while (--size >= 0) {
				Point p = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					if (isIn(nx, ny) && !visited[nx][ny]) {
						if (arr[nx][ny] == 0 || arr[nx][ny] == curSize) {
							q.offer(new Point(nx, ny));
							visited[nx][ny] = true;
						} else if (arr[nx][ny] < curSize) {
							pq.offer(new Point(nx, ny));
							q.offer(new Point(nx, ny));
							visited[nx][ny] = true;
						} else if (arr[nx][ny] > curSize) {
							visited[nx][ny] = true;
						}
					}
				}
			}
			if(!pq.isEmpty()) {
				Point p = pq.poll();
				eatCnt++;
				arr[p.x][p.y] = 0;
				if (eatCnt == curSize) {
					eatCnt = 0;
					curSize++;
				}
				ans += cnt;
				start = p;
				return true;
			}
			cnt++;
		}
		return false;
	}

	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
				if (arr[i][k] == 9) {
					start = new Point(i, k);
					arr[i][k] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();	
		while(true) {
			if(!bfs()) break;
			if(isAllZero()) break;
			if(!canEat()) break;
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}