package SR;

import java.io.*;
import java.util.*;

public class BOJ15903 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static long N, M, ans = 0;
	static PriorityQueue<Long> arr = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr.offer(Long.parseLong(st.nextToken()));
		}

		for (int i = 0; i < M; i++) {
			long first = arr.poll();
			long second = arr.poll();
			long tmp = first + second;
			arr.offer(tmp);
			arr.offer(tmp);
		}

		while (!arr.isEmpty()) {
			ans += arr.poll();
		}
		System.out.println(ans);
	}
}