package SR;

import java.io.*;
import java.util.*;

public class BOJ7569 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int M, N, H, arr[][][], maxDay = Integer.MIN_VALUE;
	static int[] dx = { -1, 0, 1, 0, 0, 0 };
	static int[] dy = { 0, 1, 0, -1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, 1, -1 };
	static Queue<Point> q = new LinkedList<>();

	static class Point {
		int x, y, z;

		Point(int z, int y, int x) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	public static boolean isZero() {
		for (int i = 0; i < H; i++) {
			for (int k = 0; k < N; k++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][k][j] == 0)
						return true;
				}
			}
		}
		return false;
	}

	public static boolean isIn(int nx, int ny, int nz) {
		if (nx < 0 || ny < 0 || nz < 0 || nx >= M || ny >= N || nz >= H) {
			return false;
		}
		return true;
	}

	public static void BFS() {
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 6; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				int nz = p.z + dz[i];

				if (isIn(nx, ny, nz) && arr[nz][ny][nx] == 0) {
					arr[nz][ny][nx] += arr[p.z][p.y][p.x] + 1;
					q.offer(new Point(nz, ny, nx));
				}
			}
		}

		if (isZero()) {
			System.out.println("-1");
			return;
		} else {
			for (int i = 0; i < H; i++) {
				for (int k = 0; k < N; k++) {
					for (int j = 0; j < M; j++) {
						maxDay = Integer.max(maxDay, arr[i][k][j]);
					}
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
		H = Integer.parseInt(st.nextToken());

		arr = new int[H][N][M];
		for (int i = 0; i < H; i++) {
			for (int k = 0; k < N; k++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[i][k][j] = Integer.parseInt(st.nextToken());
				}
			}
		}

		for (int i = 0; i < H; i++) {
			for (int k = 0; k < N; k++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][k][j] == 1) {
						q.offer(new Point(i, k, j));
					}
				}
			}
		}
		BFS();
	}
}