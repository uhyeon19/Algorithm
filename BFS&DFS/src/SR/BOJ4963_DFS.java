package SR;

import java.io.*;
import java.util.*;

public class BOJ4963_DFS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static boolean[][]visit;
	static int w, h, ans, rand[][];
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dy = {0, 1, 0, -1, 1, -1, 1, -1};
	
	public static boolean isIn(int nx, int ny) {
		if(nx < 0 || ny < 0 || nx >= w || ny >= h) {
			return false;
		}
		return true;
	}
	
	public static void DFS(int x, int y) {
		visit[x][y] = true;
		
		for(int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!isIn(nx, ny)) {
				continue;
			}
			
			if(!visit[nx][ny] && rand[nx][ny] == 1) {
				DFS(nx, ny);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		while(true) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			if(w == 0 && h == 0) break;
			rand = new int[w][h];
			visit = new boolean[w][h];
			ans = 0;
			for(int i = 0; i < w; i++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < h; k++) {
					rand[i][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < w; i++) {
				for(int k = 0; k < h; k++) {
					if(!visit[i][k] && rand[i][k] == 1) {
						DFS(i, k);
						ans++;
					}
				}
			}
			sb.append(ans + "\n");
		}
		System.out.print(sb.toString());
	}
}
