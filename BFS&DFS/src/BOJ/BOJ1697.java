package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1697 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, K, ans = 0;
	static boolean visit[] = new boolean[100001];
	static Queue<Integer> q = new ArrayDeque<>();
	
	public static void bfs() {
		if(K == N) return;
		q.offer(N);
		visit[N] = true;
		while(!q.isEmpty()) {
			ans++;
			int size = q.size();
			while(--size >= 0) {
				int cur = q.poll();
				int canGo[] = new int[3];
				canGo[0] = cur - 1;
				canGo[1] = cur + 1;
				canGo[2] = 2 * cur;
				for(int i = 0; i < 3; i++) {
					if(canGo[i] == K) return;
					if(canGo[i] >= 0 && canGo[i] <= 100000 && !visit[canGo[i]]) {
						visit[canGo[i]] = true;
						q.offer(canGo[i]);
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bfs();
		System.out.println(ans);
	}
}