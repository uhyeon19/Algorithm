package SR;

import java.util.*;
import java.io.*;

public class BOJ2606_DFS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, ans = 0;	// 컴퓨터 수, 간선 수, 정답
	static int[][]arr;
	static boolean[]visit;
	
	public static void init() throws IOException {
		arr = new int[N + 1][N + 1];
		visit = new boolean[N + 1];
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x][y] = arr[y][x] = 1;
		}
	}
	
	public static void DFS(int start) {
		visit[start] = true;
		
		for(int i = 1; i <= N; i++) {
			if(!visit[i] && arr[start][i] == 1) {
				ans++;
				DFS(i);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		DFS(1);
		System.out.println(ans);
	}
}
