package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5653 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, N, M, K, ans;
	static Cell arr[][];
	static boolean visited[][];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Cell {
		int wait, run, hp;
		boolean isNew;

		public Cell(int wait, int run, int hp, boolean isNew) {
			this.wait = wait;
			this.run = run;
			this.hp = hp;
			this.isNew = isNew;
		}
	}

	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N + K * 2 && ny < M + K * 2;
	}

	public static void find() {
		for (Cell[] cells : arr) {
			for (Cell cell : cells) {
				if(cell.wait > 0 || cell.run > 0) ans++;
			}
		}
	}

	public static void solve(int time) {
		for(int i = K - time; i < K + N + time; i++) {
			for(int k = K - time; k < K + M + time; k++) {
				if(arr[i][k].hp == 0) continue;
				Cell tmp = arr[i][k];
				arr[i][k] = new Cell(tmp.wait, tmp.run, tmp.hp, false);
				arr[i][k].wait--;
			}
		}
		for(int i = K - time; i < K + N + time; i++) {
			for(int k = K - time; k < K + M + time; k++) {
				if(arr[i][k].hp == 0) continue;
				if(arr[i][k].run != 0) {
					arr[i][k].run--;
					for(int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = k + dy[d];
						if(isIn(nx, ny) && arr[nx][ny].hp == 0) {
							arr[nx][ny] = new Cell(arr[i][k].hp, 0, arr[i][k].hp, true);
						}
						if(isIn(nx, ny) && arr[nx][ny].isNew) {
							if(arr[nx][ny].hp < arr[i][k].hp)
								arr[nx][ny] = new Cell(arr[i][k].hp, 0, arr[i][k].hp, true);
						}
					}
				}
				if(arr[i][k].wait == 0) arr[i][k].run = arr[i][k].hp;
			}
		}
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new Cell[N + K * 2][M + K * 2];
		ans = 0;
		for (int i = 0; i < N + K * 2; i++) {
			Arrays.fill(arr[i], new Cell(0, 0, 0, false));
		}

		for (int i = K; i < K + N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = K; k < K + M; k++) {
				int hp = Integer.parseInt(st.nextToken());
				arr[i][k] = new Cell(hp, 0, hp, false);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			for (int i = 1; i <= K; i++) {
				solve(i);
			}
			find();
			bw.write("#" + t + " " + ans + "\n");
		}
		bw.flush();
		bw.close();
	}
}