package SR;

import java.io.*;
import java.util.*;

public class BOJ10809 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		String str = br.readLine();
		arr = new int[26];
		Arrays.fill(arr, -1);
		
		for(int i = str.length() - 1; i >= 0; i--) {
			char c = str.charAt(i);
			arr[c - 'a'] = i;
		}
		
		for(int i = 0; i < 26; i++) {
			bw.write(arr[i] + " ");
		}
		bw.flush();
		bw.close();
	}
}
