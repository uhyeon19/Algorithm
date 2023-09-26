package BOJ;

import java.io.*;

class BOJ1699 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n + 1];
		
		dp[1] = 1;
		for(int i = 2; i <= n; i++) {
			dp[i] = 10001;
			for(int j = 1;j <= i / 2; j++) {
				if(j * j == i) {
					dp[i] = 1;
					break;
				}
				dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
			}
		}
		System.out.println(dp[n]);
	}
}
