package SR;

import java.io.*;

public class BOJ2720 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int T, C, q, d, n, p;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			C = Integer.parseInt(br.readLine());
			q = d = n = p = 0;

			q = C / 25;
			C %= 25;

			d = C / 10;
			C %= 10;

			n = C / 5;
			C %= 5;

			p = C;
			sb.append(q + " " + d + " " + n + " " + p + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
