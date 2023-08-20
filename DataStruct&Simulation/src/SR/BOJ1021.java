package SR;

import java.io.*;
import java.util.*;

public class BOJ1021 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static LinkedList<Integer> deque = new LinkedList<>();
	static int N, M, arr[], ans = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			deque.offer(i);
		}

		arr = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			int index = deque.indexOf(arr[i]);
			int half;
			if (deque.size() % 2 == 0) {
				half = deque.size() / 2 - 1;
			} else {
				half = deque.size() / 2;
			}

			if (index <= half) {
				for (int j = 0; j < index; j++) {
					int temp = deque.pollFirst();
					deque.offerLast(temp);
					ans++;
				}
			} else {
				for (int j = 0; j < deque.size() - index; j++) {
					int temp = deque.pollLast();
					deque.offerFirst(temp);
					ans++;
				}
			}
			deque.pollFirst();
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}
