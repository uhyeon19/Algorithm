package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1158 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int n, k;
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		sb.append('<');
		for(int i = 1; i <= n; i++) {
			q.offer(i);
		}
		
		while(!q.isEmpty()) {
			for(int i = 0; i < k - 1; i++) {
				int tmp = q.poll();
				q.offer(tmp);
			}
			sb.append(q.poll()).append(q.size() == 0 ? ">" : ", ");
		}
		System.out.println(sb.toString());
	}
}