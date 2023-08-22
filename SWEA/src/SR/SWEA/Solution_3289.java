package SR.SWEA;

import java.io.*;
import java.util.*;

public class Solution_3289 {
	static int T, N, M;	// 초기 집합의 개수
	static int parents[];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	private static void makeSet() {
		parents = new int[N + 1];
		for(int i = 0; i < N + 1; i++) {
			parents[i] = i;	// 모든 요소는 자기만 원소로 갖는 단위 서로소 집합이 되게 한다.(자기 자신이 곧 대표자인 루트)
		}
	}
	
	/**
	 * a와 b의 대표값이 같은지 확인한다.
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
	 * a의 대표값을 찾는다.
	 * @param a
	 * @return
	 */
	private static int findSet(int a) {
		if(a == parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	/**
	 * a와 b가 같은 집합이었다면 false 그게 아니라서 합칠 수 있는 상황이라면 합친다.
	 * @param a
	 * @param b
	 * @return
	 */
	private static boolean union(int a, int b) {	// a가 속한 집합과 b가 속한 집합을 합칠 수 있다면 합치고 true 반환, 아니면 false 반환
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;	// 서로의 대표자가 같은 즉, 같은 집합의 상황이므로 union하지 않음
		// union 처리 (bRoot를 aRoot 아래로 붙이기 : 임의로..!)
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
