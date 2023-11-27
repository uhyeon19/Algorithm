package SWEA;

import java.io.*;
import java.util.*;

public class Solution_7465 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int T, N, M, ans;
	static List<Integer> list[];	// 인접 리스트로 dfs 처리
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		run();
	}
	
	static void run() throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			init();
			for(int i = 1; i <= N; i++) {	// 1번부터 N번의 사람까지 모두 돈다
				if(!visited[i]) {	// 방문한 곳이 아니라면 즉, 지금까지 연결된 곳이 아니라면
					ans++;	// 답을 올려주고
					dfs(i);	// dfs 내에서 연결되어있는 곳들 다 visited 체크함
				}
			}
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * 초기화 메소드
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		ans = 0;
		for(int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		int x, y;
		for(int i = 0; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			list[x].add(y);	// 양방향 연결..
			list[y].add(x);
		}
	}
	
	/**
	 * dfs 알고리즘 메소드
	 * @param index
	 */
	static void dfs(int index) {
		visited[index] = true;
		
		// 현재 인덱스의 연결 리스트 확인
		for(int i : list[index]) {
			// 이어져 있는 사람인데, 방문한 곳이 아니라면 => 이전에 확인했던 사람이 아니라면
			if(!visited[i]) {
				dfs(i);	// dfs
			}
		}
	}
}