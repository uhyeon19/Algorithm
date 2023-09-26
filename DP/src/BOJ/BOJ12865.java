package BOJ;

import java.io.*;
import java.util.*;

public class BOJ12865 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, K, dp[];
	static Info info[];

	static class Info {
		int w, v;

		public Info(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
	
	public static void dp() {
		for(int i = 1; i <= N; i++) {
			// K부터 탐색하여 담을 수 있는 무게 한계치가 넘지 않을 때까지 반복
			for(int k = K; k - info[i].w >= 0; k--) {
				dp[k] = Math.max(dp[k], dp[k - info[i].w] + info[i].v);
			}
		}
	}
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[K + 1];
		info = new Info[N + 1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			info[i] = new Info(w, v);
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		dp();
		bw.write(dp[K] + "\n");
		bw.flush();
		bw.close();
	}
}