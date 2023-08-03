package SR;

import java.io.*;
import java.util.*;

public class BOJ12891 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int S, P, ans = 0;
	static int[] acgt = new int[4];
	static int[] dnaAcgt = new int[4];
	static String password;
	
	public static boolean isDNA() {
		for(int i = 0 ; i < 4; i++) {
			if(acgt[i] < dnaAcgt[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static void addAcgt(int i) {
		if(password.charAt(i) == 'A') acgt[0]++;
		else if(password.charAt(i) == 'C') acgt[1]++;
		else if(password.charAt(i) == 'G') acgt[2]++;
		else if(password.charAt(i) == 'T') acgt[3]++;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		password = br.readLine();
		st = new StringTokenizer(br.readLine());
		int cnt = 0;
		while(st.hasMoreTokens()) {
			dnaAcgt[cnt++] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < P; i++) {
			addAcgt(i);
		}
		if(isDNA()) ans++;
		
		for(int i = P; i < S; i++) {
			int front = i - P;
			if(password.charAt(front) == 'A') acgt[0]--;
			else if(password.charAt(front) == 'C') acgt[1]--;
			else if(password.charAt(front) == 'G') acgt[2]--;
			else if(password.charAt(front) == 'T') acgt[3]--;
			addAcgt(i);
			if(isDNA()) ans++;
		}
		System.out.println(ans);
	}
}