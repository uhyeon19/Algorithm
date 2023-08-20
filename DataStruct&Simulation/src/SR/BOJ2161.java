package SR;

import java.io.*;
import java.util.*;

public class BOJ2161 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static Queue<Integer> queue = new ArrayDeque<>();
	static int N;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			sb.append(queue.poll() + " ");
			if(!queue.isEmpty()) {
				int tmp = queue.poll();
				queue.offer(tmp);
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
