package SR;

import java.io.*;
import java.util.*;

public class Solution_4012 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int TC, N, arr[][], ans;
	static boolean isSelected[];

	public static int minSynergy() {
		int x = 0;	// ?„ ?ƒ?•œ ?‹?¬ë£Œë¡œ ë§Œë“œ?Š” ?Œ?‹ A
		int y = 0;	// ?„ ?ƒ?•˜ì§? ?•Š?? ?‹?¬ë£Œë¡œ ë§Œë“œ?Š” ?‹¤ë¥? ?Œ?‹ B
		// ?™„? „ ?ƒ?ƒ‰
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(i == k) continue;	// ?„œë¡? ê°™ì? ?‹?¬ë£ŒëŠ” ?‚¬?š© X (0?„!)
				if(isSelected[i] && isSelected[k]) {	// ?„ ?ƒ?•œ ?‹?¬ë£Œì¸ì§? ?™•?¸
					x += arr[i][k];
				} else if(!isSelected[i] && !isSelected[k]) {	// ?„ ?ƒ ?•ˆ ?•œ ?‹?¬ë£Œì¸ì§? ?™•?¸
					y += arr[i][k];
				}
			}
		}
		return Math.abs(x - y);	// ?Œ?‹ A?˜ ?‹œ?„ˆì§?, ?Œ?‹ B?˜ ?‹œ?„ˆì§? ì°¨ì´ ? ˆ?Œ“ê°?
	}
	
	public static void recursionComb(int index, int cnt) { // ì¡°í•© êµ¬í•˜ê¸?
		if (cnt == N / 2) {
			ans = Math.min(ans, minSynergy());
			// ?Œ?‹ A?˜ ?‹œ?„ˆì§?, ?Œ?‹ B?˜ ?‹œ?„ˆì§? ì°¨ì´ ? ˆ?Œ“ê°’ê³¼ ?´? „?–ˆ?˜ ?š”ë¦¬ë“¤ ? ˆ?Œ“ê°? ë¹„êµ
			return;
		} else {
			for (int i = index; i < N; i++) {
				if (isSelected[i]) // ?„ ?ƒ?œ ê²ƒì´ë©? ?„˜?–´ê°?
					continue;
				isSelected[i] = true; // ?„ ?ƒ?œ ê±? trueë¡?
				recursionComb(i + 1, cnt + 1); // cntë¥? ?Š˜? ¤?„œ ?‹¤?Œ ?ˆ˜ ì°¾ê¸°
				isSelected[i] = false; // ?„ ?ƒ??˜ ê±? falseë¡? ?Œ? ¤ì£¼ê¸°
			}
		}
	}

	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					arr[i][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = Integer.MAX_VALUE;	// ans ì´ˆê¸°?™”
			isSelected = new boolean[N];	// ?„ ?ƒ ë°°ì—´ ì´ˆê¸°?™”
			recursionComb(0, 0);
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.print(sb.toString());
	}
}
