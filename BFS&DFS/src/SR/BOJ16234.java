package SR;

import java.io.*;
import java.util.*;

public class BOJ16234 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int arr[][], copyArr[][], N, L, R;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean visited[][];

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}

	public static boolean isFin() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				for(int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = k + dy[d];
					if(isIn(nx, ny)) {
						if(isGapOkay(arr[i][k], arr[nx][ny])) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public static boolean isGapOkay(int cur, int next) {
		int gap = Math.abs(cur - next);
		return gap >= L && gap <= R;
	}

	public static void bfs(Point p) {
		Queue<Point> q = new ArrayDeque<>();
		List<Point> list = new ArrayList<>();
		q.offer(p);
		list.add(p);
		visited[p.x][p.y] = true;
		int sum = arr[p.x][p.y];
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if(isIn(nx, ny) && !visited[nx][ny] && isGapOkay(arr[cur.x][cur.y], arr[nx][ny])) {
					q.offer(new Point(nx, ny));
					visited[nx][ny] = true;
					list.add(new Point(nx, ny));
					sum += arr[nx][ny];
				}
			}
		}
		move(sum, list);
	}

	public static void move(int sum, List<Point> list) {
		int result = sum / list.size();
		for (int i = 0; i < list.size(); i++) {
			int x = list.get(i).x;
			int y = list.get(i).y;
			copyArr[x][y] = result;
		}
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}

	public static int run() {
		int ans = 0;
		while(true) {
			if(isFin()) break;
			copyArr = new int[N][N];
			visited = new boolean[N][N];
			boolean flag = false;
			for (int i = 0; i < N; i++) {
				for (int k = 0; k < N; k++) {
					if (!visited[i][k]) {
						bfs(new Point(i, k));
					}
				}
			}
			for(int i = 0; i < N; i++) {
				arr[i] = copyArr[i].clone();
			}
			ans++;
		}
		return ans;
	}

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(run());
	}
}
