package BOJ;

import java.io.*;

public class BOJ14916_GRID {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, cnt = 0;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		while (true) {
			if (N % 5 == 0) {
				cnt += N / 5;
				System.out.println(cnt);
				break;
			} else {
				N -= 2;
				cnt++;
			}
			if(N < 0) {
				System.out.println(-1);
				break;
			}
		}
	}
}
