package SR;

import java.util.*;
import java.io.*;

public class BOJ2567 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, arr[][];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static boolean isIn(int nx, int ny) {
		if (nx < 1 || ny < 1 || nx >= 101 || ny >= 101) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[101][101];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int k = x; k < x + 10; k++) {
				for (int j = y; j < y + 10; j++) {
					arr[k][j] = 1;
				}
			}
		}

		int cnt = 0;
		for (int i = 1; i <= 100; i++) {
			for (int k = 1; k <= 100; k++) {
				if (arr[i][k] == 1) {
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = k + dy[d];

						if (isIn(nx, ny) && arr[nx][ny] == 0)
							cnt++;
						else if (!isIn(nx, ny))	// 경계여도 cnt++
							cnt++;
					}
				}
			}
		}
		System.out.println(cnt);
	}
}