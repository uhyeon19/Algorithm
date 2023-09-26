package BOJ;

import java.io.*;
import java.util.*;

public class BOJ15650_NP {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, arr[], nextPer[];
	
	public static boolean np(int []arr) {
		int top = N - 1;
		while (top > 0 && arr[top - 1] <= arr[top]) --top;
		if (top == 0) return false;

		int change = N - 1;
		while (arr[top - 1] <= arr[change]) --change;

		swap(arr, top - 1, change);

		int sort = N - 1;
		while (top < sort) swap(arr, top++, sort--);
		return true;
	}

	private static void swap(int []arr, int i, int change) {
		int tmp = arr[i];
		arr[i] = arr[change];
		arr[change] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		nextPer = new int[N];
		
		for(int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		}
		
		int cnt = 0;
		while (cnt < M) nextPer[cnt++] = 1; 

		do {
			for (int i = 0; i < N; i++) {
				if (nextPer[i] == 1)
					sb.append(arr[i] + " ");
			}
			sb.append('\n');
		} while (np(nextPer));
		System.out.print(sb.toString());
	}
}
