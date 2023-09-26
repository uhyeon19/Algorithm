package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2580 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int map[][], N = 9, cnt = 0;
	static boolean c1[][], c2[][], c3[][];
	
	public static int square(int x, int y) {
		return (x / 3) * 3 + (y / 3);
	}
	
	public static boolean go(int z) throws IOException {
		cnt += 1;
		if(cnt >= 10000000) {
			return true;
		}
		if(z == 81) {
			for(int i = 0; i < N; i++) {
				for(int k = 0; k < N; k++) {
					bw.write(map[i][k] + " ");
				}
				bw.write("\n");
			}
			return true;
		}
		
		int x = z / N;
		int y  = z % N;
		if(map[x][y] != 0) {
			return go(z + 1);
		} else {
			for(int i = 1; i <= 9; i++) {
				if(!c1[x][i] && !c2[y][i] && !c3[square(x, y)][i]) {
					c1[x][i] = c2[y][i] = c3[square(x, y)][i] = true;
					map[x][y] = i;
					if(go(z + 1)) {
						return true;
					}
					map[x][y] = 0;
					c1[x][i] = c2[y][i] = c3[square(x, y)][i] = false;
				}
			}
		}
		return false;
	}
	
	public static void init() throws IOException {
		map = new int[N + 1][N + 1];
		c1 = new boolean[N + 1][N + 1];
		c2 = new boolean[N + 1][N + 1];
		c3 = new boolean[N + 1][N + 1];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < N; k++) {
				map[i][k] = Integer.parseInt(st.nextToken());
				if(map[i][k] != 0) {
					c1[i][map[i][k]] = true;
					c2[k][map[i][k]] = true;
					c3[square(i, k)][map[i][k]] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		go(0);
		bw.flush();
		bw.close();
	}
}