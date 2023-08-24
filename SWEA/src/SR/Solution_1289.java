 package SR;

import java.util.*;

public class Solution_1289 {
	static int T;	// testCase ???¥
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	// Scanner ê°ì²´ ?ƒ?„±
		StringBuilder sb = new StringBuilder();	// StringBuilderë¡? ì¶œë ¥ê°? ???¥
		T = sc.nextInt();						//  testCase ?…? ¥
		for(int i = 0; i < T; i++) {
			int ans = 0;						// ë°”ë?ŒëŠ” ?šŸ?ˆ˜ ???¥?•  ê³µê°„
			char before = '0';					// ?´? „ ?ƒ?ƒœ ???¥, ì²˜ìŒ?—?Š” 00~~?´?‹ˆê¹? 0?œ¼ë¡? ì´ˆê¸°?™”
			String tmp = sc.next();				// ?›?˜?˜ ê°? ???¥
			for(int k = 0; k < tmp.length(); k++) {	// ?›?˜?˜ ê°? ê¸¸ì´ë§Œí¼ forë¬?
				if(before != tmp.charAt(k)) {	// ?´? „ ê°’ì´?‘ ë³µêµ¬?•  ê°? ë¹„êµ ?‹œ ?‹¤ë¥´ë‹¤ë©??
					before = tmp.charAt(k);		// ?‹¤ë¥? ê°’ì„ before?— ?„£?? ?›„
					ans++;						// ans ?”?•´ì£¼ê¸°
				}
			}
			sb.append("#").append(i + 1).append(" ").append(ans).append("\n");
			// StringBuilder?— ê°? ???¥
		}
		System.out.print(sb.toString());	// ê°? ì¶œë ¥
		sc.close();
	}
}
