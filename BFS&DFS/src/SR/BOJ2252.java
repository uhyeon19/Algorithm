package SR;

import java.io.*;
import java.util.*;

// 위상정렬
public class BOJ2252 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, K, A, B, edgeCnt[];
	static List<List<Integer>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		edgeCnt = new int[N + 1];
		for(int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
			edgeCnt[B]++;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		
		// 진입 차수가 0인 값 큐에 담기
		for(int i = 1; i < edgeCnt.length; i++) {
			if(edgeCnt[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int node = q.poll();
			bw.write(String.valueOf(node) + " ");
			List<Integer> list = graph.get(node);
			for(int i = 0; i < list.size(); i++) {
				edgeCnt[list.get(i)]--;
				if(edgeCnt[list.get(i)] == 0) {
					q.offer(list.get(i));
				}
			}
		}
		
		bw.flush();
		bw.close();
	}
}