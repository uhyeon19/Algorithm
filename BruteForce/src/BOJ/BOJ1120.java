package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1120 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String x = st.nextToken();
		String y = st.nextToken();
		
		int minus = y.length() - x.length();
		int ans = x.length();
		
		for(int i = 0; i < minus + 1; i++) {
			int min = 0;
			for(int j = 0; j < x.length(); j++) {
				if(x.charAt(j) != y.charAt(j + i)) {
					min++;
				}
			}
			ans = Integer.min(ans, min);
		}
		System.out.println(ans);
	}
}
