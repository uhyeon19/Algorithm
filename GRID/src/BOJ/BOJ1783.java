package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1783 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		if(N == 1) System.out.println(1);
		else if(N == 2) System.out.println(Math.min(4, (M + 1) / 2));
		else if(M < 7) System.out.println(Math.min(4, M));
		else System.out.println(M - 2);
	}
}
