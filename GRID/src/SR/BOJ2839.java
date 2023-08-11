package SR;

import java.io.*;

public class BOJ2839 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;

	//  1   2   3   4   5   6   7   8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
	// -1  -1   1  -1   1   2  -1   2  3  2  3  4  3  4  3  4  5  4  5  4  5  6  5  4  5  6  7  6  5  6
	// 규칙 찾기
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		if (N < 3 || N == 4 || N == 7) System.out.println(-1);
		else {
			if (N % 5 == 0) System.out.println(N / 5);
			else if (N % 5 == 1 || N % 5 == 3) System.out.println(N / 5 + 1);
			else if(N % 5 == 2 || N % 5 == 4) System.out.println(N / 5 + 2);
		}
	}
}