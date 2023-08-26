package SR;

import java.io.*;
import java.util.*;

public class BOJ1753 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int V, E, K, INF = Integer.MAX_VALUE;
	static int[] distance;
	static List<Node>[] graph;
	
	static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		graph = new ArrayList[V + 1];
		for (int i = 0; i < V + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());	// u에서 v로 가는 가중치 w인 간선
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Node(v, w));
		}
	}
	
	private static void dijkstra(int start) {
		distance = new int[V + 1];
		Arrays.fill(distance, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> (Integer.compare(o1.w, o2.w)));
		pq.offer(new Node(start, 0));
		distance[start] = 0;
		
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int curWeight = node.w;
			int curVertex = node.v;
			if (distance[curVertex] < curWeight) continue;
			for (Node n : graph[curVertex]) {
				int cost = curWeight + n.w;
				if (cost < distance[n.v]) {
					distance[n.v] = cost;
					pq.offer(new Node(n.v, cost));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		dijkstra(K);
		for (int i = 1; i < V + 1; i++) {
			bw.write(distance[i] != INF ? distance[i] + "\n" : "INF\n");
		}
		bw.flush();
		bw.close();
	}
}
