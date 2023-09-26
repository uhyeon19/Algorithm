package SWEA;

import java.util.*;

public class Solution_2805 {
	static int T, N;
	static int[][] arr;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	
	public static void init() {
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			String str = sc.next();
			for(int k = 0; k < str.length(); k++) {
				arr[i][k] = str.charAt(k) - '0';
			}
		}
	}
	
	public static void main(String[] args) {
		T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			N = sc.nextInt();
			init();
			int sum = 0;
			int mid = N/2;
			int start = mid, end = mid;
			for(int k = 0; k < N; k++) {
				for(int j = start; j < end + 1; j++) {
					sum += arr[k][j];
				}
				if(k < mid) {
					start--;
					end++;
				} else {
					start++;
					end--;
				}
			}
			
			sb.append("#").append(i + 1).append(" ").append(sum).append('\n');
		}
		System.out.print(sb.toString());
	}
}
