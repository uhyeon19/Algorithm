package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1225 {
	static int T;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		for(int t = 0; t < 10; t++) {
			T = Integer.parseInt(br.readLine());
			sb.append('#').append(T).append(' ');
			st = new StringTokenizer(br.readLine());
			Queue<Integer> q = new LinkedList<>();
			while(st.hasMoreTokens()) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			for(int i = 0;; i++) {
				int tmp = q.poll() - (i % 5 + 1);
				q.offer(tmp <= 0 ? 0 : tmp);
				if(tmp <= 0) break;
			}
			
			for(int i = 0; i < 8; i++) {
				sb.append(q.poll()).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}
