package SR;

import java.util.*;
import java.io.*;

public class BOJ14499 {
	static int N, M, x, y, K;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int[] dice = {0, 0, 0, 0, 0, 0};
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int k = 0;
			while(st.hasMoreTokens()) {
				map[i][k++] = Integer.parseInt(st.nextToken());
			}
		}
		
		
	}
}
