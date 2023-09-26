package BOJ;

import java.io.*;
import java.util.*;

public class BOJ15650 {
	static int N, M;
	static int []num;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int[M];
		func(0, 1);
		System.out.print(sb.toString());
	}
	
	
	private static void func(int cnt, int start) {
		if (cnt == M) {
			for(int i : num) {
				sb.append(i).append(' ');
			}
			sb.append('\n');
			return;
		}

		for (int i = start; i <= N; i++) {
			num[cnt] = i;
			func(cnt + 1, i + 1);
		}
	}
}