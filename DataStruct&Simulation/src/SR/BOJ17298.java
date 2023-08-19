package SR;

import java.io.*;
import java.util.*;

public class BOJ17298 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, arr[];
	static Stack<Integer> first = new Stack<>();
	static Stack<Integer> second = new Stack<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) first.push(Integer.parseInt(st.nextToken()));

		arr = new int[N];
		int cnt = 0;
		while (!first.isEmpty()) {
			int f = first.pop();
			if (!second.isEmpty()) {
				while (!second.isEmpty()) {
					int s = second.peek();
					if (s > f) {
						arr[cnt++] = s;
						break;
					} else second.pop();
				}
				if (second.isEmpty()) arr[cnt++] = -1;
			} else arr[cnt++] = -1;
			second.push(f);
		}

		for (int i = N - 1; i >= 0; i--) {
			sb.append(arr[i] + " ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
