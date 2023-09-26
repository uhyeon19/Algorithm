package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1208 {
	static int[]arr;
	static int N;
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void dump() {
		for(int i = 0; i < N; i++) {
			Arrays.sort(arr);
			if(arr[arr.length - 1] - arr[0] < 2) break;
			arr[0]++;
			arr[arr.length - 1]--;
		}
	}
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			
			int cnt = 0;
			arr = new int[100];
			while(st.hasMoreTokens()) {
				arr[cnt++] = Integer.parseInt(st.nextToken());
			}
			dump();
			Arrays.sort(arr);
			sb.append("#").append(i + 1).append(" ").append(arr[arr.length - 1] - arr[0]).append('\n');
		}
		System.out.println(sb.toString());
	}
}
