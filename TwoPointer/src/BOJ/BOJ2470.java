package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2470 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int N, arr[];
	
	static void twoPointer() {
		int abs = Integer.MAX_VALUE, temp, sum;
		int left = 0, right = N - 1;
		int ans1 = 0, ans2 = 0;
		while(left < right) {
			sum = arr[left] + arr[right];
			temp = Math.abs(sum);
			if(temp < abs) {
				abs = temp;
				ans1 = arr[left];
				ans2 = arr[right];
			}
			if(sum > 0) {
				right--;
			} else {
				left++;
			}
		}
		sb.append(ans1 + " " + ans2 + "\n");
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
	}
	
	public static void main(String[] args) throws IOException {
		init();
		twoPointer();
		System.out.println(sb.toString());
	}
}
