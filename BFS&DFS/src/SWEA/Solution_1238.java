package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1238 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static List<Integer> []arr;
	static PriorityQueue<Integer> pq;
	static int start = 0, ans = 0;
	static boolean visited[];

	@SuppressWarnings("unchecked")
	public static void init() throws IOException {
		arr = new ArrayList[101];
		ans = 0;
		for(int i = 1; i <= 100; i++) arr[i] = new ArrayList<>();
		visited = new boolean[101];
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(to);
		}
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;
		while(!q.isEmpty()) {
			int size = q.size();
			pq = new PriorityQueue<>(Collections.reverseOrder());
			while(--size >= 0) {				
				int v = q.poll();
				pq.offer(v);
				for(int i = 0; i < arr[v].size(); i++) {
					if(!visited[arr[v].get(i)]) {
						visited[arr[v].get(i)] = true;
						q.offer(arr[v].get(i));
					}
				}
			}
		}
		ans = pq.peek();
	}

	public static void main(String[] args) throws IOException {
		for(int tc = 1; tc <= 10; tc++) {
			init();
			bfs(start);
			bw.write("#" + tc + " " + ans + "\n");
		}
		bw.flush();
		bw.close();
	}
}
