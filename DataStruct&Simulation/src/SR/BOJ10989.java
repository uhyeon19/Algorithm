package SR;

import java.io.*;
import java.util.*;

public class BOJ10989 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[] cnt = new int[10001];
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			cnt[Integer.parseInt(br.readLine())]++;
		}
		br.close();


		for (int i = 1; i < 10001; i++) {
			while (cnt[i] > 0) {
				sb.append(i).append('\n');
				cnt[i]--;
			}
		}
		System.out.println(sb);
	}
}