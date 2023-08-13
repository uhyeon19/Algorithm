package SR;

import java.io.*;
import java.util.*;

public class BOJ17135 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, D, arr[][], tmpArr[][], ans = Integer.MIN_VALUE;
	static Point[] archer = new Point[3];
	static boolean isSelected[], kill[][], visit[][];
	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
			return false;
		}
		return true;
	}

	static void enemyForward() {
		int[][] resultArr = new int[N][M];
		for (int i = 0; i < N - 1; i++) {
			resultArr[i + 1] = tmpArr[i].clone();
		}
		tmpArr = resultArr;
	}

	static boolean isFin() {
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				if (tmpArr[i][k] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	static void attackBFS(Point archer) {
		Queue<Point> q = new LinkedList<>();
		int count = 1;
		visit = new boolean[N][M];
		q.offer(archer);
		boolean check = false;
		while (!q.isEmpty()) {
			int size = q.size();
			while (--size >= 0) {
				Point p = q.poll();
				for (int d = 0; d < 3; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					if (isIn(nx, ny) && !visit[nx][ny] && count <= D) {
						if (tmpArr[nx][ny] == 0) {
							q.offer(new Point(nx, ny));
							visit[nx][ny] = true;
						} else if (tmpArr[nx][ny] == 1) {
							kill[nx][ny] = true;
							check = true;
							break;
						}
					}
				}
				if (check)
					break;
			}
			if (check)
				break;
			count++;
		}
	}

	static void whereArcher_Comb(int cnt, int index) {
		if (cnt == 3) {
			tmpArr = new int[N][M];
			for (int i = 0; i < N; i++) {
				tmpArr[i] = arr[i].clone();
			}

			int count = 0;
			do {
				kill = new boolean[N][M];
				for (int i = 0; i < 3; i++) {
					attackBFS(archer[i]);
				}

				for (int i = 0; i < N; i++) {
					for (int k = 0; k < M; k++) {
						if (kill[i][k]) {
							count++;
							tmpArr[i][k] = 0;
						}
					}
				}
				enemyForward();
			} while (!isFin());
			ans = Integer.max(count, ans);
			return;
		}
		for (int i = index; i < M; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				archer[cnt] = new Point(N, i);
				whereArcher_Comb(cnt + 1, index + 1);
				isSelected[i] = false;
			}
		}
	}

	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		isSelected = new boolean[M];
	}

	public static void main(String[] args) throws IOException {
		init();
		whereArcher_Comb(0, 0);
		System.out.println(ans);
	}
}
