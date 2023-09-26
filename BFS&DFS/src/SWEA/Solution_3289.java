package SWEA;

import java.io.*;
import java.util.*;

public class Solution_3289 {
	static int T, N, M;	// 초기 집합?�� 개수
	static int parents[];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	private static void makeSet() {
		parents = new int[N + 1];
		for(int i = 0; i < N + 1; i++) {
			parents[i] = i;	// 모든 ?��?��?�� ?��기만 ?��?���? 갖는 ?��?�� ?��로소 집합?�� ?���? ?��?��.(?���? ?��?��?�� �? ???��?��?�� 루트)
		}
	}
	
	/**
	 * a?? b?�� ???��값이 같�?�? ?��?��?��?��.
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
	 * a?�� ???��값을 찾는?��.
	 * @param a
	 * @return
	 */
	private static int findSet(int a) {
		if(a == parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	/**
	 * a?? b�? 같�? 집합?��?��?���? false 그게 ?��?��?��?�� ?���? ?�� ?��?�� ?��?��?��?���? ?��친다.
	 * @param a
	 * @param b
	 * @return
	 */
	private static boolean union(int a, int b) {	// a�? ?��?�� 집합�? b�? ?��?�� 집합?�� ?���? ?�� ?��?���? ?��치고 true 반환, ?��?���? false 반환
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;	// ?��로의 ???��?���? 같�? �?, 같�? 집합?�� ?��?��?���?�? union?���? ?��?��
		// union 처리 (bRoot�? aRoot ?��?���? 붙이�? : ?��?���?..!)
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
