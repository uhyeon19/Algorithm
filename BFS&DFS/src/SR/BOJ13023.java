package SR;

import java.io.*;
import java.util.*;

public class BOJ13023 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, M, ans = 0; // 깊이가 5인 경우를 찾아야한다.
	static boolean visited[];
	static List<Integer> graph[];

	public static void dfs(int index, int cnt) {
		if (cnt == 4) {
			ans = 1;
			return;
		}

		visited[index] = true;
		for (int i = 0; i < graph[index].size(); i++) {
			if (!visited[graph[index].get(i)]) {
				dfs(graph[index].get(i), cnt + 1);
			}
			if(ans == 1) return;
		}
		visited[index] = false;
	}

	@SuppressWarnings("unchecked")
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[2001];
		graph = new ArrayList[2001];
		for (int i = 0; i < 2001; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			graph[to].add(from);
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		for(int i = 0; i < 2001; i++) {
			if(graph[i].size() != 0) dfs(i, 0);			
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}