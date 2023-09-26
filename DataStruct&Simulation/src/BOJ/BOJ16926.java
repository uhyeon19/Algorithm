package BOJ;

import java.util.*;
import java.io.*;

public class BOJ16926 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, rollCnt, arr[][];
	static boolean visit[][];
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
			return false;
		}
		return true;
	}

	public static void rolling(int x, int y) {
		int curD = 0;
		int startX = x;
		int endY = y;
		Queue<Integer> q = new LinkedList<>();
		q.offer(arr[x][y]);

		while (!visit[startX][endY]) {
			int nx = x + dx[curD];
			int ny = y + dy[curD];
			if (isIn(nx, ny) && !visit[nx][ny]) {
				q.offer(arr[nx][ny]);
				arr[nx][ny] = q.poll();
				visit[nx][ny] = true;
				x = nx;
				y = ny;
			} else {
				curD = (curD + 1) % 4;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		rollCnt = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}

		int min = Integer.min(N, M);
		for (int cnt = 0; cnt < rollCnt; cnt++) {
			visit = new boolean[N][M];
			for (int i = 0; i < min / 2; i++) {
				for (int k = 0; k < min / 2; k++) {
					rolling(i, k);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				System.out.print(arr[i][k] + " ");
			}
			System.out.println();
		}

	}
}