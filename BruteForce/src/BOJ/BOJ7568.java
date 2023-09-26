package BOJ;

import java.io.*;
import java.util.*;

public class BOJ7568 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int [] x = new int[n];
		int [] y = new int[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());	// 몸무게
			y[i] = Integer.parseInt(st.nextToken());	// 키
		}
		
		for(int i = 0; i < n; i++) {
			int rank = 1;
			for(int j = 0; j < n; j++) {
				if(i == j) continue;	// 나 자신을 제외하고 검증
				if (x[i] < x[j] && y[i] < y[j]) {
					rank++;
				}
			}
			System.out.print(rank + " ");
		}
	}
}
