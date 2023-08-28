package SR;

import java.io.*;
import java.util.*;

public class BOJ11724 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, M, ans;
	static List<Integer> graph[];
	static boolean visited[];
	
	public static void bfs(int startV) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(startV);
		visited[startV] = true;
		while(!q.isEmpty()) {
			int v = q.poll();
			for(int i = 0; i < graph[v].size(); i++) {
				int nv = graph[v].get(i);
				if(!visited[nv]) {
					q.offer(nv);
					visited[nv] = true;
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		
		for(int i = 1; i <= N; i++) {			
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				bfs(i);
				ans++;
			}
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}
