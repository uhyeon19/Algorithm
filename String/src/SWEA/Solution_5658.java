package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5658 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T, N, K;
	static String str;
	static PriorityQueue<Integer> pq;
	
	public static void main(String[] args) throws IOException {
		run();
	}
	
	static void run() throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			init();
			divide();
			sb.append("#" + t + " " +getOrderNum() + "\n");
		}
		System.out.print(sb.toString());
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>(Collections.reverseOrder());
		str = br.readLine();
	}
	
	static void divide() {
		int length = N / 4;
		for(int i = 0; i < N; i++) {
			String tmp = "";
			for(int k = i; k < i + length; k++) {
				tmp += str.charAt(k % N);
			}
			int num = Integer.parseInt(tmp, 16);
			if (!pq.contains(num)) pq.offer(Integer.parseInt(tmp, 16));
		}
	}
	
	static int getOrderNum() {
		for(int i = 0; i < K - 1; i++) {
			pq.poll();
		}
		return pq.poll();
	}
}