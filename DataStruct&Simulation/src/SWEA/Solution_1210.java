package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1210 {
	static int tc, x, y, d;
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int[][] map;
	static boolean[][] isVisit;
	static int[] dy = { 1, -1 }; // ?��, �?

	public static boolean isIn(int ny) {
		if (ny < 0 || ny >= 100) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			tc = Integer.parseInt(st.nextToken());
			map = new int[100][100];
			for (int k = 0; k < 100; k++) {
				st = new StringTokenizer(br.readLine());
				int cnt = 0;
				while (st.hasMoreTokens()) {
					int tmp = Integer.parseInt(st.nextToken());
					if(tmp == 2) {
						x = k;
						y = cnt;
					}
					map[k][cnt++] = tmp;
				}
			}
			isVisit = new boolean[100][100];
			while (x != 0) {
				isVisit[x][y] = true;
				boolean check = false;
				for(int k = 0; k < 2; k++) {
					int ny = y + dy[k];
					if(isIn(ny) && map[x][ny] == 1 && !isVisit[x][ny]) {
						y = ny;
						check = true;
						break;
					}
				}
				if(!check) x--;
				
			}
			sb.append("#").append(tc).append(" ").append(y).append('\n');
		}
		System.out.print(sb.toString());
	}
}
