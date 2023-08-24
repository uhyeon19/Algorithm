package SR;

import java.io.*;
import java.util.*;

public class Solution_3289 {
	static int T, N, M;	// ì´ˆê¸° ì§‘í•©?˜ ê°œìˆ˜
	static int parents[];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	private static void makeSet() {
		parents = new int[N + 1];
		for(int i = 0; i < N + 1; i++) {
			parents[i] = i;	// ëª¨ë“  ?š”?†Œ?Š” ?ê¸°ë§Œ ?›?†Œë¡? ê°–ëŠ” ?‹¨?œ„ ?„œë¡œì†Œ ì§‘í•©?´ ?˜ê²? ?•œ?‹¤.(?ê¸? ??‹ ?´ ê³? ???‘œ??¸ ë£¨íŠ¸)
		}
	}
	
	/**
	 * a?? b?˜ ???‘œê°’ì´ ê°™ì?ì§? ?™•?¸?•œ?‹¤.
	 * @param a
	 * @param b
	 * @return
	 */
	private static boolean findSet(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return true;
		return false;
	}
	
	/**
	 * a?˜ ???‘œê°’ì„ ì°¾ëŠ”?‹¤.
	 * @param a
	 * @return
	 */
	private static int findSet(int a) {
		if(a == parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	/**
	 * a?? bê°? ê°™ì? ì§‘í•©?´?—ˆ?‹¤ë©? false ê·¸ê²Œ ?•„?‹ˆ?¼?„œ ?•©ì¹? ?ˆ˜ ?ˆ?Š” ?ƒ?™©?´?¼ë©? ?•©ì¹œë‹¤.
	 * @param a
	 * @param b
	 * @return
	 */
	private static boolean union(int a, int b) {	// aê°? ?†?•œ ì§‘í•©ê³? bê°? ?†?•œ ì§‘í•©?„ ?•©ì¹? ?ˆ˜ ?ˆ?‹¤ë©? ?•©ì¹˜ê³  true ë°˜í™˜, ?•„?‹ˆë©? false ë°˜í™˜
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;	// ?„œë¡œì˜ ???‘œ?ê°? ê°™ì? ì¦?, ê°™ì? ì§‘í•©?˜ ?ƒ?™©?´ë¯?ë¡? union?•˜ì§? ?•Š?Œ
		// union ì²˜ë¦¬ (bRootë¥? aRoot ?•„?˜ë¡? ë¶™ì´ê¸? : ?„?˜ë¡?..!)
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			makeSet();
			sb.append("#" + t + " ");
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(cmd == 0) {
					union(a, b);
				} else {
					if(findSet(a, b)) sb.append(1);
					else sb.append(0);
				}
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
