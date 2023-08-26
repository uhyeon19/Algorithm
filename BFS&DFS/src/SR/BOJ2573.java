package SR;

import java.io.*;
import java.util.*;

public class BOJ2573 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, M, arr[][], copyArr[][], iceCnt, ans;
	static List<Point> iceList = new ArrayList<>();
	static boolean visited[][];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < M;
	}
	
	public static void iceCnt() {
		iceCnt = 0;
		visited = new boolean[N][M];
		for(int i = 0; i < iceList.size(); i++) {
			int x = iceList.get(i).x;
			int y = iceList.get(i).y;
			if(arr[x][y] != 0 && !visited[x][y]) {
				bfs(x, y);
				iceCnt++;
			}
		}
	}

	public static void bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if(isIn(nx, ny) && !visited[nx][ny] && arr[nx][ny] != 0) {
					visited[nx][ny] = true;
					q.offer(new Point(nx, ny));
				}
			}
		}
	}
	
	public static void meltIce() {
		copyArr = new int[N][M];
		for(int i = 0; i < N; i++) {
			copyArr[i] = arr[i].clone();
		}
		
		for(int i = iceList.size() - 1; i >= 0; i--) {
			Point ice = iceList.get(i);
			for(int d = 0; d < 4; d++) {
				int nx = ice.x + dx[d];
				int ny = ice.y + dy[d];
				if(isIn(nx, ny) && arr[nx][ny] == 0) {
					if(--copyArr[ice.x][ice.y] == 0) {
						iceList.remove(i);
						break;
					}
				}
			}
		}
		arr = copyArr;
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
				if (arr[i][k] != 0)
					iceList.add(new Point(i, k));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		while(true) {
			iceCnt();
			if(iceCnt >= 2) break;
			if(iceCnt == 0) break;
			meltIce();
			ans++;
		}
		bw.write(iceCnt == 0 ? 0 + "\n" : ans + "\n");
		bw.flush();
		bw.close();
	}
}
