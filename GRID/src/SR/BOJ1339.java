package SR;

import java.io.*;
import java.util.*;

/*
 * GCF
 * ACDEB
 * 10000A 100G 10C 1000C 100D 1F 10E 1B
 * 10000A 1B 1010C 100D 10E 1F 100G
 */
public class BOJ1339 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, sum = 0;
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[27];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			int len = (int) Math.pow(10, str.length() - 1);
			for(int k = 0; k < str.length(); k++) {
				arr[str.charAt(k) - 'A'] += len;
				len /= 10;
			}
		}
		
		Arrays.sort(arr);
		int cnt = 9;
		for(int i = arr.length - 1; i >= 0; i--) {
			if(arr[i] != 0) sum += arr[i] * cnt--;
		}
		System.out.println(sum);
	}
}