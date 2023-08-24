package SR;

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
		return parents[a] = findSet(parents[a]);	// path ?••ì¶?
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;	// ?‹¸?´?´ ë°œìƒ ?˜ë¯¸ë¡œ ?•´?„!
		// ì¹˜ìš°ì¹? ?˜•?ƒœê°? ?  ?ˆ˜ ?ˆê¸? ?•Œë¬¸ì— ?‚˜ì¤‘ì—?Š” ?­?¬(ê¹Šì´) ê´?ë¦¬ë?? ?•´ì£¼ëŠ”ê²? ì¢‹ì?ë§? ì§?ê¸ˆì? ?¼?‹¨ ?´? ‡ê²? ?‚¬?š©
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
			
			// ê°„ì„ ë¦¬ìŠ¤?Š¸ë¥? ê°?ì¤‘ì¹˜ ê¸°ì? ?˜¤ë¦„ì°¨?ˆœ ? •? ¬
			Arrays.sort(edgeList);
			
			// Vê°œì˜ ? •? ?œ¼ë¡? make-set ?‘?—…
			makeSet();
			
			long result = 0;	// MST ë¹„ìš© ?ˆ„?  -> ?•˜?‚˜ê°? 1000000?¼ ?ˆ˜ ?ˆ?‹¤... ?‹¤ ?”?•˜?‹¤ë³´ë©´ int?˜•?œ¼ë¡œëŠ” ?•ˆ?¨
			int count = 0;	// ?—°ê²°ëœ ê°„ì„  ê°œìˆ˜
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