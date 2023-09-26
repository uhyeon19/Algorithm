package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1629 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int A, B, C;

	public static long pow(long a, long b, long c) {
		if (b == 1) return a % c;
		long val = pow(a, b / 2, c);
		val = val * val % c;
		if (b % 2 == 0) return val;
		return val * a % c;
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		System.out.println(pow(A, B, C));
	}
}