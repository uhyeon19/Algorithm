package SR;

import java.io.*;
import java.util.*;

public class BOJ1759 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static char arr[], pass[];
	static int L, C;
	static boolean visited[];

	public static String makePass() {
		String tmp = "";
		for(int i = 0; i < pass.length; i++) {
			tmp += pass[i];
		}
		return tmp;
	}
	
	public static boolean isPass(String passTmp) {
		int c = 0;	// 모음
		int v = 0;	// 자음
		for(int i = 0; i < passTmp.length(); i++) {
			if(passTmp.charAt(i) == 'a' || passTmp.charAt(i) == 'i' || passTmp.charAt(i) == 'o' || passTmp.charAt(i) == 'u' || passTmp.charAt(i) == 'e')
				c++;
			else
				v++;
		}
		if(c == 0) return false;
		if(v < 2) return false;
		return true;
	}
	
	public static void Permutation(int cnt, int index) {
		if(cnt == L) {
			String passTmp = makePass();
			if(isPass(passTmp)) sb.append(passTmp + "\n");
			return;
		}
		
		for(int i = index; i < C; i++) {
			pass[cnt] = arr[i];
			Permutation(cnt + 1, i + 1);
		}
	}
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new char[C];
		for(int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		visited = new boolean[C];
		pass = new char[L];		
	}

	public static void main(String[] args) throws IOException {
		init();
		Permutation(0, 0);
		bw.append(sb.toString());
		bw.flush();
		bw.close();
	}
}