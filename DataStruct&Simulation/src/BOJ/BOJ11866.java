package BOJ;

import java.io.*;
import java.util.*;

public class BOJ11866 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, K;
	static Queue<Integer> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for(int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		sb.append('<');
		while(!queue.isEmpty()) {
			for(int i = 0; i < K - 1; i++) {
				int tmp = queue.poll();
				queue.offer(tmp);
			}
			sb.append(queue.poll()).append(queue.isEmpty() ? ">" : ", ");
		}
		bw.write(sb.toString() + "\n");
		bw.flush();
		bw.close();
	}
}
