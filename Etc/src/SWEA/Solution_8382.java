package SWEA;

import java.io.*;
import java.util.*;

public class Solution_8382 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int T, x1, x2, y1, y2, x, y;
	
	public static void main(String[] args) throws IOException {
		run();
	}
	
	static void run() throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			init();
			sb.append("#" + t + " " + solve() + "\n");
		}
		System.out.println(sb.toString());
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		x1 = Integer.parseInt(st.nextToken());
		y1 = Integer.parseInt(st.nextToken());
		x2 = Integer.parseInt(st.nextToken());
		y2 = Integer.parseInt(st.nextToken());
		x = Math.abs(x1 - x2);
		y = Math.abs(y1 - y2);
	}
	
	static int solve() { 
		int closePos = (x + y) / 2;
		return closePos * 2 + Math.abs(closePos - x) + Math.abs(closePos - y);
	}
}