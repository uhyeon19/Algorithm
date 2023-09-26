package BOJ;

import java.io.*;
import java.util.*;

public class BOJ16953 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static long A, B, ans = 1;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		while (A != B) {
			if (B < A) {
				ans = -1;
				break;
			}
			String tmp = String.valueOf(B);
			if (tmp.charAt(tmp.length() - 1) != '1' && B % 2 != 0) {
				ans = -1;
				break;
			}

			if (B % 2 == 0) {
				B /= 2;
			} else {
				tmp = tmp.substring(0, tmp.length() - 1);
				B = Long.parseLong(tmp);
			}
			ans++;
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}
