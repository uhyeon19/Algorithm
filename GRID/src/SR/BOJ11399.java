package SR;

import java.io.*;
import java.util.*;

public class BOJ11399 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int []arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		int ans = 0;
		int []sum = new int[N];
		for(int i = 0; i < N; i++) {
			if(i == 0) sum[i] = arr[i];
			else sum[i] = sum[i - 1] + arr[i];
			ans += sum[i];
		}
		System.out.println(ans);
	}
}