package BOJ;

import java.io.*;
import java.util.*;

public class BOJ10866 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	public static ArrayDeque<Integer> deque = new ArrayDeque<>();
	public static int N;

	public static void solve() throws IOException {
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			if (cmd.equals("push_front")) {
				int num = Integer.parseInt(st.nextToken());
				deque.offerFirst(num);
			} else if (cmd.equals("push_back")) {
				int num = Integer.parseInt(st.nextToken());
				deque.offerLast(num);
			} else if (cmd.equals("pop_front")) {
				bw.write(deque.isEmpty() ? -1 + "\n" : deque.pollFirst() + "\n");
			} else if (cmd.equals("pop_back")) {
				bw.write(deque.isEmpty() ? -1 + "\n" : deque.pollLast() + "\n");
			} else if (cmd.equals("size")) {
				bw.write(deque.size() + "\n");
			} else if (cmd.equals("empty")) {
				bw.write(deque.isEmpty() ? 1 + "\n" : 0 + "\n");
			} else if (cmd.equals("front")) {
				bw.write(deque.isEmpty() ? -1 + "\n" : deque.peekFirst() + "\n");
			} else if (cmd.equals("back")) {
				bw.write(deque.isEmpty() ? -1 + "\n" : deque.peekLast() + "\n");
			}
		}

	}

	public static void main(String[] args) throws IOException {
		solve();
		bw.flush();
		bw.close();
	}
}
