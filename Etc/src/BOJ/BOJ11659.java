package BOJ;

import java.io.*;
import java.util.*;

public class BOJ11659 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int N, M, I, J;
	static int[] arr, addArr;
	
	public static void accumulateSum() {
		addArr[0] = 0;
		for(int i = 1; i <= arr.length; i++) {
			addArr[i] = addArr[i - 1] + arr[i - 1];
		}
	}
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		addArr = new int[N + 1];
		int cnt = 0;
		while(st.hasMoreTokens()) {
			arr[cnt++] = Integer.parseInt(st.nextToken());
		}
	}
	
	public static void solve() throws IOException {
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			I = Integer.parseInt(st.nextToken());
			J = Integer.parseInt(st.nextToken());
			sb.append(addArr[J] - addArr[I - 1]).append('\n');
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		accumulateSum();
		solve();
		System.out.print(sb.toString());
	}
}