package SR;

import java.io.*;
import java.util.*;

public class BOJ17070 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int map[][], N, ans;
	// 0 가로 1 세로 2 대각선
	// x, y, dir
	static int direct[][][] = { { { 0, 1, 0 }, { 1, 1, 2 } }, { { 1, 0, 1 }, { 1, 1, 2 } },
			{ { 0, 1, 0 }, { 1, 0, 1 }, { 1, 1, 2 } } };

	public static boolean isIn(int nx, int ny) {
		return nx >= 1 && ny >= 1 && nx <= N && ny <= N;
	}
	
	public static void dfs(int x, int y, int d) {
		if(x == N && y == N) {
			ans++;
			return;
		}
		
		int curd[][] = direct[d];
		for(int i = 0; i < curd.length; i++) {
			int nx = x + curd[i][0];
			int ny = y + curd[i][1];
			int ndir = curd[i][2];
			if(isIn(nx, ny) && map[nx][ny] != 1) {
				if(ndir != 2) {					
					dfs(nx, ny, ndir);
				} else {
					if(isIn(nx - 1, ny) && map[nx - 1][ny] != 1 && isIn(nx, ny - 1) && map[nx][ny - 1] != 1)
						dfs(nx, ny, ndir);
				}
			}
		}
	}

	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 1; k <= N; k++) {
				map[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		dfs(1, 2, 0);
		System.out.println(ans);
	}
}