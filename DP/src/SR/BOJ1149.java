package SR;

import java.io.*;
import java.util.*;

public class BOJ1149 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int arr[][], dp[][], N;
	
	public static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		arr = new int[N][3];
		dp = new int[N][3];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < 3; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void dp() {
		for(int i = 0; i < 3; i++) {
			dp[0][i] = arr[0][i];
		}
		for(int i = 1; i < N; i++) {
			for(int k = 0; k < 3; k++) {
				if(k == 0) {
					dp[i][k] = Integer.min(arr[i][k] + dp[i - 1][1], arr[i][k] + dp[i - 1][2]);
				} else if(k == 1) {
					dp[i][k] = Integer.min(arr[i][k] + dp[i - 1][0], arr[i][k] + dp[i - 1][2]);					
				} else {
					dp[i][k] = Integer.min(arr[i][k] + dp[i - 1][0], arr[i][k] + dp[i - 1][1]);
				}
			}
		}
	}
	
	public static int getMin() {
		Arrays.sort(dp[N - 1]);
		return dp[N - 1][0];
	}
	
	public static void main(String[] args) throws IOException {
		init();
		dp();
		bw.write(getMin() + "\n");
		bw.flush();
		bw.close();
	}
}