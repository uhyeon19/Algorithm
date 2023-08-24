package SR;

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
		if(calorie > L) return;	// ì¹¼ë¡œë¦? ì´ˆê³¼
		if(calorie <= L) ans = Math.max(ans, grade); // ì£¼ì–´ì§? ? œ?•œ ì¹¼ë¡œë¦? ?´?•˜ ì¡°í•©?¸ ê²½ìš° ë°”ê¿ˆ
		if(start == N) return;	// Nê°œë?? ëª¨ë‘ ?„?‹¬?–ˆ?„ ?•Œ ë©ˆì¶¤

		// ë¶?ë¶? ì§‘í•©
		// ì§?ê¸? ?ž¬ë£? ?‚¬?š©?• ?ž˜!
		// start(index)ë¥? ?•˜?‚˜?”© ?Š˜? ¤?„œ ?‹¤?Œ ?ž¬ë£Œë¡œ ê°?ê¸?
		// ?˜„?ž¬ ?ž¬ë£Œë?? ?‚¬?š©?•˜ê¸? ?•Œë¬¸ì— ?˜„?ž¬ ?ž¬ë£Œì˜ ? •ë³´ë?? ?”?•´?„œ ?„˜ê²¨ì¤Œ
		combination(start + 1, grade + arr.get(start).grade, calorie + arr.get(start).calorie);
		// ì§?ê¸? ?ž¬ë£? ?‚¬?š©?•˜ì§? ?•Š?„?ž˜!
		// ?˜„?ž¬ ?ž¬ë£Œë?? ?‚¬?š©?•˜ì§? ?•Šê¸? ?•Œë¬¸ì— ?˜„?ž¬ ?ž¬ë£Œì˜ ? •ë³´ë?? ?” ?•˜ì§? ?•Š?Š”?‹¤.
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
				arr.add(new Material(grade, calorie));	// ë¦¬ìŠ¤?Š¸?— ?ž¬ë£? ? •ë³? ?‹´ê¸?
			}
			
			ans = 0;
			combination(0, 0, 0);
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.print(sb.toString());
	}
}
