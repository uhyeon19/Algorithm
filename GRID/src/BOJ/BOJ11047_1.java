package BOJ;

import java.io.*;
import java.util.*;

public class BOJ11047_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int cnt = 0;
		
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			arr.add(Integer.parseInt(br.readLine()));	
		}
		
		for(int i = arr.size() - 1; i >= 0; i--) {
			int money = arr.get(i);
			while(true) {
				if(k >= money) {
					k -= money;
					cnt++;
				} else {
					break;
				}
			}
		}
		System.out.println(cnt);
	}
}
