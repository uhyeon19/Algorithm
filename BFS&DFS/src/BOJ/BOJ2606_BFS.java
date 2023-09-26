package BOJ;

import java.util.*;
import java.io.*;

public class BOJ2606_BFS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, ans = 0;	// 컴퓨터 수, 간선 수, 정답
	static int[][]arr;
	static boolean[]visit;
	
	public static void init() throws IOException {
		arr = new int[N + 1][N + 1];
		visit = new boolean[N + 1];
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x][y] = arr[y][x] = 1;
		}
	}
	
	public static void BFS(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);	// 시작 정점 = 1번 컴퓨터
		visit[start] = true;
		
		while(!queue.isEmpty()) {
			int v = queue.poll();
			for(int i = 1; i <= N; i++) {
				if(!visit[i] && arr[v][i] == 1) {
					queue.offer(i);
					visit[i] = true;
					ans++;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		BFS(1);
		System.out.println(ans);
	}
}
