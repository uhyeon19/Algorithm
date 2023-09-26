package Study;

import java.io.*;
import java.util.*;

public class DijkstraAlgorithm {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int V, E, start, end, distance[]; // 정점개수, 간선개수, 시작노드, 끝노드, 시작점에서 본인까지 오는 최단거리
	static boolean visited[]; // 방문처리
	static Node adjList[]; // 인접리스트
	static final int INF = Integer.MAX_VALUE;

	static class Node {
		int vertex, weight;
		Node next;

		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine().trim());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		adjList = new Node[V];	// 인접 리스트
		distance = new int[V];
		visited = new boolean[V];
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}
	}
	
	public static void dijkstra() {
		Arrays.fill(distance, INF);
		
		distance[start] = 0;
		int min = 0, stopOver = 0;
		
		for(int i = 0; i < V; i++) {	// 모든 정점을 다 처리할 때 까지 반복
			// step1 : 미방문 정점 중 출발지에서 가장 가까운 정점을 경유지로 선택
			stopOver = -1;
			min = INF;
			for(int k = 0 ; k < V; k++) {
				if(!visited[k] && min > distance[k]) {
					min = distance[k];
					stopOver = k;
				}
			}
			if(stopOver == -1) break;
			
			// step2 : 방문 처리
			visited[stopOver] = true;
			// 상황에 따라 처리 : 경유지가 곧 도착지면 끝내기(출발지에서 모든 정점으로의 최단거리를 구할 시에는 break가 필요없다)
			if(stopOver == end) break;
			
			// step3 : 경유지를 이용하여 미방문 정점들의 출발지에서 자신으로의 최소비용 고려
			for(Node tmp = adjList[stopOver]; tmp != null; tmp = tmp.next) {
				// 해당 정덤이 방문 정점이 아니고 현재 정점에서 갈 수 있는 정점의 경우
				// 최소거리 정점을 거쳐서 해당 정점을 갈 경우의 토탈 가중치와 기존까지 계산된 해당 정점까지의 토탈 가중치를 비교하여 최소값 갱신
				if(!visited[tmp.vertex] && distance[tmp.vertex] > min + tmp.weight) {
					distance[tmp.vertex] = min + tmp.weight;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		dijkstra();
		bw.write(distance[end] != INF ? distance[end] : -1);
	}
}
