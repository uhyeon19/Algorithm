package BOJ;

import java.io.*;
import java.util.*;

public class BOJ5014 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int F, S, G, U, D;
	static boolean visited[];
	static String ans;
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(bfs());
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
	}
	
	static String bfs() {
		if(S == G) return "0";
		visited = new boolean[F + 1];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(S);
		visited[S] = true;
		
		int nextUpFloor, nextDownFloor, curFloor, cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			cnt++;
			while(--size >= 0) {
				curFloor = q.poll();
				nextUpFloor = curFloor + U;
				nextDownFloor = curFloor - D;
				if(nextUpFloor == G || nextDownFloor == G) return String.valueOf(cnt);
				if(isIn(nextUpFloor) && !visited[nextUpFloor]) {
					q.offer(nextUpFloor);
					visited[nextUpFloor] = true;
				}
				if(isIn(nextDownFloor) && !visited[nextDownFloor]) {
					q.offer(nextDownFloor);
					visited[nextDownFloor] = true;
				}
			}
		}
		
		return "use the stairs";
	}
	
	static boolean isIn(int nextFloor) {
		return nextFloor > 0 && nextFloor <= F;
	}
}
