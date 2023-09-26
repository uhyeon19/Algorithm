package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1228 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static LinkedList<Integer> list;
	
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 10; i++) {
			list = new LinkedList<>();
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			sb.append('#').append(i + 1).append(' ');
			K = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine(), "I ");
			while(st.hasMoreTokens()) {
				int insertIndex = Integer.parseInt(st.nextToken());
				int insertCnt = Integer.parseInt(st.nextToken());
				for(int k = 0; k < insertCnt; k++) {
					list.add(insertIndex + k, Integer.parseInt(st.nextToken()));
				}
			}
			for(int k = 0; k < 10; k++) {
				sb.append(list.get(k)).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}
