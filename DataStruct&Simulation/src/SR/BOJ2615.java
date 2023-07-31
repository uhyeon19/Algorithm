package SR;

import java.io.*;
import java.util.*;

public class BOJ2615 {
	static int[][] arr = new int[19][19];
	static int[] dx = { 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1 };

	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || nx >= 19 || ny < 0 || ny >= 19) {
			return false;
		}
		return true;
	}

	public static void check() {
		boolean check = false;
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (arr[i][j] != 0) {
					for (int d = 0; d < 4; d++) {
						int cnt = 1;
						int isSix = 0;
						for (int k = 1; k <= 5; k++) {
							if (k == 1) {
								int nx = i + dx[d] * -k;
								int ny = j + dy[d] * -k;
								if (isIn(nx, ny)) {
									if (arr[i][j] == arr[nx][ny]) {
										isSix++;
									}
								}
							}
							int nx = i + dx[d] * k;
							int ny = j + dy[d] * k;
							if (isIn(nx, ny)) {
								if (arr[i][j] == arr[nx][ny]) {
									cnt++;
								} else {
									break;
								}
							} else {
								break;
							}
						}
						if (cnt == 5 && isSix == 0) {
							System.out.println(arr[i][j]);
							System.out.println((i + 1) + " " + (j + 1));
							check = true;
						}
					}
				}
			}
		}
		if (!check)
			System.out.println(0);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = 0;
			while (st.hasMoreTokens()) {
				arr[i][cnt++] = Integer.parseInt(st.nextToken());
			}
		}
		check();
	}
}