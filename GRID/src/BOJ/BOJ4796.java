package BOJ;

import java.io.*;
import java.util.*;

public class BOJ4796 {
	public static void main(String[] args) throws IOException {
		int L = 0, P = 0, V = 0;
		ArrayList<String> ans = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int t = 0;
		while(true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			if(L == 0 && P == 0 && V == 0) break;
			ans.add("Case " + ++t + ": " + ((V / P) * L + Math.min(V % P, L)));
		}
		
		for(int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i));
		}
	}
}