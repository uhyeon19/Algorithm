package SR;

import java.io.*;
import java.util.*;

public class BOJ11403 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, arr[][];

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(arr[i][k] == 1 && arr[k][j] == 1) {
						// 0 0	0 0
						// 0 0	0 1
						// 0 0	0 2
						
						// 1 0	0 0
						// 1 0	0 1
						// 1 0	0 2
						
						// 2 0	0 0
						// 2 0	0 1
						// 2 0 	0 2
						
						// 0 1	0 0
						// 0 1	0 1
						// 0 1	0 2
						
						// 0 2	0 0
						// 0 2	0 1
						// 0 2	0 2
						arr[i][j] = 1;
					}
				}
			}
		}

		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				sb.append(arr[i][k] + " ");
			}
			sb.append('\n');
		}
		
		System.out.print(sb.toString());
	}
}