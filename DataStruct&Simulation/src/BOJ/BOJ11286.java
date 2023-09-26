package BOJ;

import java.util.*;
import java.io.*;

public class BOJ11286 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static PriorityQueue<Integer> plusQ = new PriorityQueue<>();
	static PriorityQueue<Integer> minusQ = new PriorityQueue<>(Collections.reverseOrder());
	static int N;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());
			if (tmp == 0) {
				if (plusQ.isEmpty() && minusQ.isEmpty()) {
					sb.append(0).append('\n');
				} else if (plusQ.isEmpty()) {
					sb.append(minusQ.poll()).append('\n');
				} else if (minusQ.isEmpty()) {
					sb.append(plusQ.poll()).append('\n');
				} else {
					if (Math.abs(plusQ.peek()) >= Math.abs(minusQ.peek())) {
						sb.append(minusQ.poll()).append('\n');
					} else {
						sb.append(plusQ.poll()).append('\n');
					}
				}
			} else {
				if (tmp > 0)
					plusQ.offer(tmp);
				else
					minusQ.offer(tmp);
			}
		}
		System.out.println(sb.toString());
	}
}