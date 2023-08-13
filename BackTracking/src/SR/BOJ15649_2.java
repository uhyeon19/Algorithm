package SR;

import java.util.*;
import java.io.*;

public class BOJ15649_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, num[];
	static boolean isSelected[];

	public static void Permutation(int cnt) {
		if (cnt == M) {
			for (int i : num) {
				sb.append(i + " ");
			}
			sb.append('\n');
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (!isSelected[i - 1]) {
				isSelected[i - 1] = true;
				num[cnt] = i;
				Permutation(cnt + 1);
				isSelected[i - 1] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		isSelected = new boolean[N];
		num = new int[M];
		Permutation(0);
		System.out.print(sb.toString());
	}
}
