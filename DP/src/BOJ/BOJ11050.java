package BOJ;

import java.io.*;
import java.util.*;

// 파스칼의 삼각형
public class BOJ11050 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, dp[][];
	
	public static int comb(int n, int r) {
		if(dp[n][r] > 0) {
			return dp[n][r];
		}
		if(n == r || r == 0) {
			return dp[n][r] = 1;
		}
		return dp[n][r] = comb(n - 1, r - 1) + comb(n - 1, r);
	}
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][K + 1];
	}
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(comb(N, K));
	}
}