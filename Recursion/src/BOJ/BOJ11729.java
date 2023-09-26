package BOJ;

import java.io.*;

public class BOJ11729 {
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();
	
	public static void hanoi(int n, int start, int end, int other) {
		if(n == 0) return;
		else {
			cnt++;
			hanoi(n - 1, start, other, end);
			sb.append(start + " " + end + "\n");
			hanoi(n - 1, other, end, start);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		hanoi(n, 1, 3, 2);
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
}
