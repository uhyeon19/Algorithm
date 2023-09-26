package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1260 {
	static int[][]graph;
	static boolean[] visited;
	static int N, M, V;	// 정점 개수, 간선 개수, 탐택 시작 정점 번호
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void graphInit() throws IOException {
		graph = new int[N + 1][N + 1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			graph[first][second] = graph[second][first] = 1;
		}
	}

	public static void DFS(int start) {
		visited[start] = true;
		sb.append(start + " ");
	    
	    for(int i = 1; i <= N; i++) {
	      if(graph[start][i] == 1 && !visited[i]) {
	        DFS(i);
	      }
	    }
	}
	
	public static void BFS() {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(V);
		visited[V] = true;
		sb.append(V + " ");
	    
	    while(!queue.isEmpty()) {
	    	int nextV = queue.poll();
	    	
	    	for(int i = 1; i <= N; i++) {
	    		if(graph[nextV][i] == 1 && !visited[i]) {
	    			queue.offer(i);
	    			visited[i] = true;
	    			sb.append(i + " ");
	    		}
	    	}
	    }
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		graphInit();
		
		visited = new boolean[N + 1];
		DFS(V);
		sb.append('\n');
		
		visited = new boolean[N + 1];
		BFS();
		System.out.print(sb.toString());
	}
}
