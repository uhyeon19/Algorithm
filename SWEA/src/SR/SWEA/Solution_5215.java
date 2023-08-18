package SR.SWEA;

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
		if(calorie > L) return;	// 칼로리 초과
		if(calorie <= L) ans = Math.max(ans, grade); // 주어진 제한 칼로리 이하 조합인 경우 바꿈
		if(start == N) return;	// N개를 모두 도달했을 때 멈춤

		// 부분 집합
		// 지금 재료 사용할래!
		// start(index)를 하나씩 늘려서 다음 재료로 가기
		// 현재 재료를 사용하기 때문에 현재 재료의 정보를 더해서 넘겨줌
		combination(start + 1, grade + arr.get(start).grade, calorie + arr.get(start).calorie);
		// 지금 재료 사용하지 않을래!
		// 현재 재료를 사용하지 않기 때문에 현재 재료의 정보를 더 하지 않는다.
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
				arr.add(new Material(grade, calorie));	// 리스트에 재료 정보 담기
			}
			
			ans = 0;
			combination(0, 0, 0);
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.print(sb.toString());
	}
}
