package SR;

import java.io.*;
import java.util.*;

public class BOJ10157 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, arr[][], K;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static boolean[][] visit;

	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		arr = new int[R][C];
		visit = new boolean[R][C];

		if (K > R * C) {
			System.out.println(0);
			return;
		} else if (K == 1) {
			System.out.println(1 + " " + 1);
			return;
		} else {
			int cnt = 1;
			int x = 0, y = 0;
			arr[x][y] = cnt;
			visit[x][y] = true;
			int curD = 0;
			while (cnt <= R * C) {
				int nx = x + dx[curD];
				int ny = y + dy[curD];
				if (isIn(nx, ny) && !visit[nx][ny]) {
					arr[nx][ny] = ++cnt;
					visit[nx][ny] = true;
					x = nx;
					y = ny;
					if (cnt == K) {
						System.out.println((nx + 1) + " " + (ny + 1));
						return;
					}
				} else {
					curD = (curD + 1) % 4;
				}
			}
		}
	}
}
