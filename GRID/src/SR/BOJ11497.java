package SR;

import java.io.*;
import java.util.*;

public class BOJ11497 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, arr[], T, sortList[];
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			sortList = new int[N];
			int leftPointer = 0;
			int rightPointer = N - 1;
			for(int i = 0; i < N; i++) {
				if(i % 2 == 0) {
					sortList[leftPointer++] = arr[i];
				} else {
					sortList[rightPointer--] = arr[i];
				}
			}
			
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < N - 1; i++) {
				max = Integer.max(max, Math.abs(sortList[i] - sortList[i + 1]));
			}
			sb.append(max + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
