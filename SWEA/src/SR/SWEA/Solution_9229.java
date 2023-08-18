package SR.SWEA;

import java.util.*;
import java.io.*;

public class Solution_9229 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, arr[], ans;
	static boolean visit[];
	
	public static void comb(int start, int cnt, int sum) {
		if(cnt == 2 && sum <= M) {
			ans = Integer.max(sum, ans);
			/*
			 * 0 1 2
			 * 0 0 0
			 * 0 0 1
			 * 0 1 0
			 * 0 1 1 => sum
			 * 1 0 0
			 * 1 0 1 => sum
			 * 1 1 0 => sum
			 * 
			*/
			return;
		}
		if (start == N || sum > M) return;
		comb(start + 1, cnt, sum);
		comb(start + 1, cnt + 1, sum + arr[start]);
	}
	
	public static void main(String[] args) throws IOException {
		int tc = Integer.parseInt(br.readLine());
		for(int t = 1; t <= tc; t++) {
			sb.append('#').append(t).append(' ');
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N];
			visit = new boolean[N];
			ans = -1;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			comb(0, 0, 0);
			sb.append(ans).append('\n');
		}
		System.out.print(sb.toString());
	}
}
