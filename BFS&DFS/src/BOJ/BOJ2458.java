package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2458 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int T, N, M, arr[][], ans, in, out;

	static void outDFS(int start, boolean visited[]) {
		visited[start] = true;
		for(int i = 1; i <= N; i++) {
			if(!visited[i] && arr[start][i] == 1) {
				out++;
				outDFS(i, visited);
			}
		}
	}
	
	static void inDFS(int start, boolean visited[]) {
		visited[start] = true;
		for(int i = 1; i <= N; i++) {
			if(!visited[i] && arr[i][start] == 1) {
				in++;
				inDFS(i, visited);
			}
		}
	}
	
	static int solve() {
		for(int i = 1; i <= N; i++) {
			in = 0;
			inDFS(i, new boolean[N + 1]);
			out = 0;
			outDFS(i, new boolean[N + 1]);
			if(in + out == N - 1) ans++;
		}
		return ans;
	}
	
	static void init() throws IOException {
		ans = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		sb.append(solve() + "\n");
		System.out.print(sb.toString());
	}
}