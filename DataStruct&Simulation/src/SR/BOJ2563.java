package SR;

import java.util.*;
import java.io.*;

public class BOJ2563 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, arr[][];
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[100][100];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int k = x; k < x + 10; k++) {
				for(int j = y; j < y + 10; j++) {
					arr[k][j] = 1;
				}
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < 100; i++) {
			for(int k = 0; k < 100; k++) {
				if(arr[i][k] == 1) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}