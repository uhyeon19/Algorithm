package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1182_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int arr[], N, S, ans = 0;
	static boolean isSelected[];

	public static void powerSet(int cnt) {
		if (cnt == N) {
			int sum = 0;
			boolean isNothing = true;
			for(int i = 0; i < N; i++) {
				if(isSelected[i]) {
					sum += arr[i];
					isNothing = false;
				}
			}
			if(!isNothing && sum == S) ans++;
			return;
		}
		
		isSelected[cnt] = true;
		powerSet(cnt + 1);
		isSelected[cnt] = false;
		powerSet(cnt + 1);
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		isSelected = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		powerSet(0);
		System.out.println(ans);
	}
}
