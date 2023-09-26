package SWEA;

import java.io.*;
import java.util.*;

public class Solution_3124 {
	static Edge[] edgeList;
	static int V, E, T, parents[];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static void makeSet() {
		parents = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);	// path ?���?
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;	// ?��?��?�� 발생 ?��미로 ?��?��!
		// 치우�? ?��?���? ?�� ?�� ?���? ?��문에 ?��중에?�� ?��?��(깊이) �?리�?? ?��주는�? 좋�?�? �?금�? ?��?�� ?��?���? ?��?��
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {			
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			edgeList = new Edge[E];
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from, to, weight);			
			}
			
			// 간선리스?���? �?중치 기�? ?��름차?�� ?��?��
			Arrays.sort(edgeList);
			
			// V개의 ?��?��?���? make-set ?��?��
			makeSet();
			
			long result = 0;	// MST 비용 ?��?�� -> ?��?���? 1000000?�� ?�� ?��?��... ?�� ?��?��?��보면 int?��?��로는 ?��?��
			int count = 0;	// ?��결된 간선 개수
			for(Edge edge : edgeList) {
				if(union(edge.from, edge.to)) {
					result += edge.weight;
					if(++count == V - 1) {
						break;
					}
				}
			}
			bw.write("#" + t + " " + result + "\n");
		}
		bw.flush();
		bw.close();
	}
}