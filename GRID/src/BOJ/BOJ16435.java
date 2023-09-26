package BOJ;

import java.io.*;
import java.util.*;

public class BOJ16435 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, L, fruitHeight[];
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		fruitHeight = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			fruitHeight[i] = Integer.parseInt(st.nextToken());
		}
		
		// 과일 높이 오름차순 정렬
		// 가장 작은 것부터 차근차근 먹어서 길이 늘려갈 수 있는지 확인
		Arrays.sort(fruitHeight);
		for(int i = 0; i < N; i++) {
			if(L >= fruitHeight[i]) L++;
			else break;
		}
		
		System.out.println(L);
	}
}