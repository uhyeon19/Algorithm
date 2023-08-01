package SR;

import java.util.*;
import java.io.*;

public class BOJ14499 {
	static int N, M, x, y, K;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int[] dice = { 0, 0, 0, 0, 0, 0 };	//전진, 좌, 천장, 우, 후진, 바닥
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] map;
	static StringBuilder sb = new StringBuilder();

	public static void init() {
		if(map[x][y] != 0) {
			dice[5] = map[x][y];
		} else {
			map[x][y] = dice[5];
		}
	}
	
	public static boolean isIn(int nx, int ny) {
		if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
			return false;
		}
		return true;
	}
	
	public static void rolling() throws IOException {
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int d = Integer.parseInt(st.nextToken());
			int nx = x + dx[d - 1];
			int ny = y + dy[d - 1];
			if(isIn(nx, ny)) {
				int tmp = dice[5];
				if(d == 1) {
					dice[5] = dice[1];
					dice[1] = dice[2];
					dice[2] = dice[3];
					dice[3] = tmp;
				} else if(d == 2) {
					dice[5] = dice[3];
					dice[3] = dice[2];
					dice[2] = dice[1];
					dice[1] = tmp;
				} else if(d == 3) {
					dice[5] = dice[4];
					dice[4] = dice[2];
					dice[2] = dice[0];
					dice[0] = tmp;
				} else if(d == 4) {
					dice[5] = dice[0];
					dice[0] = dice[2];
					dice[2] = dice[4];
					dice[4] = tmp;
				}
				
				if(map[nx][ny] != 0) {
					dice[5] = map[nx][ny];
					map[nx][ny] = 0;
				} else {
					map[nx][ny] = dice[5];
				}
				sb.append(dice[2]).append("\n");
				x = nx;
				y = ny;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int k = 0;
			while (st.hasMoreTokens()) {
				map[i][k++] = Integer.parseInt(st.nextToken());
			}
		}
		
		init();
		rolling();
		System.out.print(sb.toString());
	}
}