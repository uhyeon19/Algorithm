package SR;

import java.io.*;
import java.util.*;

public class BOJ2961 {
	static int N, min = Integer.MAX_VALUE;
	static boolean[] visit;
	static int[][] input;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	private static void recur(int depth) {
		if (depth == N) {
			int sour = 1;
			int bitter = 0;
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (visit[i]) {
					cnt++;
					sour *= input[i][0];
					bitter += input[i][1];
				}
			}
			if (cnt == 0) return;
			min = Integer.min(min, Math.abs(sour - bitter));
			return;
		}

		visit[depth] = true;
		recur(depth + 1);
		visit[depth] = false;
		recur(depth + 1);
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		visit = new boolean[N];
		input = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		recur(0);
		System.out.println(min);
	}
}