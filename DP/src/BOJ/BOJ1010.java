package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1010 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, N, M, dp[][];
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[30][30];
	}
	
	public static int comb(int n, int r) {
		if(dp[n][r] > 0) {
			return dp[n][r];
		}
		if(n == r || r == 0) {
			return dp[n][r] = 1;
		}
		return dp[n][r] = comb(n - 1, r - 1) + comb(n - 1, r);
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			init();
			bw.write(comb(M, N) + "\n");
		}
		bw.flush();
		bw.close();
	}
}