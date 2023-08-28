package SR;

import java.io.*;
import java.util.*;

public class Solution_5656 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, N, W, H, arr[][], copyArr[][], min;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class Wall {
		int x, y, size;

		public Wall(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}

	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < H && ny < W;
	}
	
	public static int getWallCnt(int arr[][]) {
		int cnt = 0;
		for(int i = 0; i < H; i++) {
			for(int k = 0; k < W; k++) {
				if(arr[i][k] > 0) cnt++;
			}
		}
		return cnt;
	}

	public static void breakWall(int arr[][], Wall walls) {
		copyArr = copyArr(arr);
		if(walls == null) return;
		Queue<Wall> q = new ArrayDeque<>();
		q.offer(walls);
		while (!q.isEmpty()) {
			Wall w = q.poll();
			int x = w.x;
			int y = w.y;
			copyArr[x][y] = 0;
			for (int d = 0; d < 4; d++) {
				for (int i = 1; i < w.size; i++) {
					int nx = x + dx[d] * i;
					int ny = y + dy[d] * i;
					if(isIn(nx, ny)) {
						if(copyArr[nx][ny] - 1 > 0) q.offer(new Wall(nx, ny, copyArr[nx][ny]));
						copyArr[nx][ny] = 0;
					}
				}
			}
		}
	}

	public static void dfs(int arr[][], int cnt, Wall walls[]) {
		if (cnt == N) {
			min = Integer.min(min, getWallCnt(arr));
			return;
		}

		for (int i = 0; i < W; i++) {
			breakWall(arr, walls[i]);
			fallingWall(copyArr);
			dfs(copyArr, cnt + 1, getWallHeight(copyArr));
		}
	}

	public static int[][] copyArr(int arr[][]) {
		int[][] copyArr = new int[H][W];
		for (int i = 0; i < H; i++) {
			copyArr[i] = arr[i].clone();
		}
		return copyArr;
	}

	public static void fallingWall(int arr[][]) {
		for (int i = H - 2; i >= 0; i--) {
			for (int k = 0; k < W; k++) {
				if (arr[i][k] > 0) {
					int x = i;
					int y = k;
					while(true) {
						int nx = x + 1;
						int ny = y;
						if(isIn(nx, ny) && arr[nx][ny] == 0) {
							arr[nx][ny] = arr[x][y];
							arr[x][y] = 0;
							x = nx;
						} else {
							break;
						}
					}
				}
			}
		}
	}

	public static Wall[] getWallHeight(int arr[][]) {
		Wall resultWalls[] = new Wall[W];
		for (int i = 0; i < W; i++) {
			for (int k = 0; k < H; k++) {
				if (arr[k][i] > 0) {
					resultWalls[i] = new Wall(k, i, arr[k][i]);
					break;
				}
			}
		}
		return resultWalls;
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		min = Integer.MAX_VALUE;
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < W; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			dfs(arr, 0, getWallHeight(arr));
			bw.write("#" + t + " " + min + "\n");
		}
		bw.flush();
		bw.close();
	}
}