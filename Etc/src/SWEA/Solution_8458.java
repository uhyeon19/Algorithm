package SWEA;

import java.io.*;
import java.util.*;

public class Solution_8458 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int T, N, ans;
	static long manhattan[], max;

	public static void main(String[] args) throws IOException {
		run();
	}

	static void run() throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			sb.append("#" + t + " " + getTime1() + "\n");
//			sb.append("#" + t + " " + getTime2() + "\n");
		}
		System.out.println(sb.toString());
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		manhattan = new long[N];
		max = 0;
		Long x, y, dist;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Long.parseLong(st.nextToken());
			y = Long.parseLong(st.nextToken());
			dist = Math.abs(x) + Math.abs(y);
			manhattan[i] = dist;
			max = Long.max(dist, max);
		}
	}

	// 방법 1. while문 돌리기
	static int getTime1() {
		if (!isPossible()) return -1;
		int i = 0;
		long sum = 0;
		while (true) {
			sum += i;
			if (sum >= max && (sum - max) % 2 == 0) break;
			i++;
		}
		return i;
	}

	// 방법 2. 근의 공식 이용하기
	static int getTime2() {
		if (!isPossible()) return -1;
		int a = 1;
		int b = 1;
		long c = -max * 2;
		double discriminant = Math.sqrt(b * b - 4 * a * c);
		double n1 = (-b + discriminant) / (2 * a);
		double n2 = (-b - discriminant) / (2 * a);
		int i = (int) Math.ceil(Math.max(n1, n2));
		while (true) {
			if (((i + 1) * i / 2 - max) % 2 == 0) break;
			i++;
		}
		return i;
	}

	static boolean isPossible() {
		long first = manhattan[0];
		for (int i = 1; i < N; i++) {
			if (first % 2 != manhattan[i] % 2)
				return false;
		}
		return true;
	}
}