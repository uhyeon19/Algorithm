package Study;

import java.io.*;
import java.util.*;

public class Permutation {
	static int N, M;
	static int[] num, arr;
	static boolean[] isSelected;

	public static void recursionPermutation(int cnt) {
		if (cnt == M) {
			for (int i : num) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!isSelected[i]) {
				num[cnt] = arr[i];
				isSelected[i] = true;
				recursionPermutation(cnt + 1);
				isSelected[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		for(int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		}
		
		num = new int[M];
		isSelected = new boolean[N];
		recursionPermutation(0);
	}
}
