package SWEA;

import java.util.*;
import java.io.*;

public class Solution_5215 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int TC, N, L, ans;
	static List<Material> arr;

	static class Material {
		int grade, calorie;

		public Material(int grade, int calorie) {
			this.grade = grade;
			this.calorie = calorie;
		}
	}
	
	public static void combination(int start, int grade, int calorie) {
		if(calorie > L) return;	// 칼로�? 초과
		if(calorie <= L) ans = Math.max(ans, grade); // 주어�? ?��?�� 칼로�? ?��?�� 조합?�� 경우 바꿈
		if(start == N) return;	// N개�?? 모두 ?��?��?��?�� ?�� 멈춤

		// �?�? 집합
		// �?�? ?���? ?��?��?��?��!
		// start(index)�? ?��?��?�� ?��?��?�� ?��?�� ?��료로 �?�?
		// ?��?�� ?��료�?? ?��?��?���? ?��문에 ?��?�� ?��료의 ?��보�?? ?��?��?�� ?��겨줌
		combination(start + 1, grade + arr.get(start).grade, calorie + arr.get(start).calorie);
		// �?�? ?���? ?��?��?���? ?��?��?��!
		// ?��?�� ?��료�?? ?��?��?���? ?���? ?��문에 ?��?�� ?��료의 ?��보�?? ?�� ?���? ?��?��?��.
		combination(start + 1, grade, calorie);		
	}
	
	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			arr = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int grade = Integer.parseInt(st.nextToken());
				int calorie = Integer.parseInt(st.nextToken());
				arr.add(new Material(grade, calorie));	// 리스?��?�� ?���? ?���? ?���?
			}
			
			ans = 0;
			combination(0, 0, 0);
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.print(sb.toString());
	}
}
