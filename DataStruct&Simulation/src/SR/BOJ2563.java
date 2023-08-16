package SR;

import java.util.*;
import java.io.*;

public class BOJ2563 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static boolean arr[][];
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new boolean[101][101];
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int k = x; k < x + 10; k++) {
				for(int j = y; j < y + 10; j++) {
					if(!arr[k][j]) {
						arr[k][j] = true;
						cnt++;
					}
				}
			}
		}
		System.out.println(cnt);
	}
}