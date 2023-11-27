package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5648 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int T, N, X, Y, D, E, ans;
	static final int SIZE = 4000;
	static int[][] arr = new int[SIZE + 1][SIZE + 1];
	static Queue<Point> q = new ArrayDeque<>();
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static class Point {
		int x, y, d, e;

		public Point(int x, int y, int d, int e) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.e = e;
		}
	}

	public static void main(String[] args) throws IOException {
		run();
	}

	static void run() throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			bfs();
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb.toString());
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken()) * 2 + SIZE / 2;
			Y = Integer.parseInt(st.nextToken()) * 2 + SIZE / 2;
			D = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			arr[X][Y] = E;
			q.offer(new Point(X, Y, D, E));
		}
		ans = 0;
	}

	static void bfs() {
		int nx, ny;
		Point p;
		while (!q.isEmpty()) {
			p = q.poll();
			// 현재 칸 = 현재 나의 에너지라면 다른 친구가 오지 않았다는 것! => 나만 있음
			// 현재 칸 != 나의 에너지 => 다른 원자가 왔다는 뜻
			if (arr[p.x][p.y] != p.e) {
				ans += arr[p.x][p.y];	// 다른 원자가 있는 경우니까 ans에 담고 큐에서 뺀 상태로 continue;
				arr[p.x][p.y] = 0;	// ans에 담았으니 0으로 만들기
				// 나랑 겹쳤던 다른 원자들은 큐에 담지 않았었으니 이미 큐에는 없을 것임!, 여기서는 ans 채우고 현재 큐 빼기만 함
				continue;
			}
			
			// 다른 친구가 오지 않았기 때문에 다음 로직
			// 다음 칸으로 이동
			nx = p.x + dx[p.d];
			ny = p.y + dy[p.d];

			if (isIn(nx, ny)) {	// 범위 내 존재
				if (arr[nx][ny] == 0) {	// 이전에 온 원자가 없는 경우
					arr[nx][ny] = p.e;	// 나의 에너지 담기
					q.offer(new Point(nx, ny, p.d, p.e));	// 가능한 경우니까 큐에 담기
				} else {	// 이전에 온 원자가 있는 경우
					arr[nx][ny] += p.e;	// 큐에 담지 않고 그냥 추가만 하기
				}
			}
			arr[p.x][p.y] = 0;	// 움직이고 나면 그 이전 칸은 0이 된다.
		}
	}

	static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx <= SIZE && ny <= SIZE;
	}
}