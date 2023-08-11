package SR;

import java.util.*;
import java.io.*;

public class BOJ1074 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, R, C, ans = 0;

	public static void solve(int size, int r, int c) {
		if (size == 1)
			return;
		if (r < size / 2 && c < size / 2) {	// 왼족 위 위치
			solve(size / 2, r, c);
		} else if (r < size / 2 && c >= size / 2) {	// 오른쪽 위 위치
			ans += size * size / 4;
			solve(size / 2, r, c - size / 2);
		} else if (r >= size / 2 && c < size / 2) {	// 왼쪽 밑 위치
			ans += (size * size / 4) * 2;
			solve(size / 2, r - size / 2, c);
		} else {	// 오른쪽 밑 위치
			ans += (size * size / 4) * 3;
			solve(size / 2, r - size / 2, c - size / 2);
		}

	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int size = (int) Math.pow(2, N);	// 한 변의 사이즈
		solve(size, R, C);
		System.out.print(ans);
	}
}
