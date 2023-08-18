package SR.SWEA;

import java.io.*;
import java.util.*;

public class Solution_1247 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, N, ans;
	static Point arr[], tmpArr[];
	static boolean isSelected[];
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void Permutation(int cnt) {
		if(cnt == N) {
			int sum = 0;
			for(int i = 0; i < N + 1; i++) {
				sum += manhattan(tmpArr[i], tmpArr[i + 1]);
				if(sum >= ans) return;
			}
			ans = sum;
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(!isSelected[i]) {
				tmpArr[cnt + 1] = arr[i];
				isSelected[i] = true;
				Permutation(cnt + 1);
				isSelected[i] = false;
			}
		}
	}
	
	public static int manhattan(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
	
	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new Point[N + 2];
		tmpArr = new Point[N + 2];
		isSelected = new boolean[N + 2];
		ans = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		int companyX = Integer.parseInt(st.nextToken());
		int companyY = Integer.parseInt(st.nextToken());
		arr[0] = new Point(companyX, companyY);
		tmpArr[0] = new Point(companyX, companyY);
		int homeX = Integer.parseInt(st.nextToken());
		int homeY = Integer.parseInt(st.nextToken());
		arr[N + 1] = new Point(homeX, homeY);
		tmpArr[N + 1] = new Point(homeX, homeY);
		
		for(int i = 1; i <= N; i++) {
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i] = new Point(x, y);
		}
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			init();
			Permutation(0);
			sb.append("#" + t + " " + ans + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
