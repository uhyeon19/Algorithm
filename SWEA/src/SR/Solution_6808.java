package SR;

import java.util.*;
import java.io.*;

public class Solution_6808 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int TC, win, lose, allWin, allLose;
	// ?…Œ?Š¤?Š¸ì¼??´?Š¤ ?ˆ˜, ê°? 9ê°? ë¹„êµë¡? ?´ê¸? ì´í•© ì°¾ê¸°/ì§? ì´í•© ì°¾ê¸°, ?•œ ?Œ?´ ??‚˜ê³? ?‚˜?„œ ì´í•©?œ¼ë¡? ?´ê²¼ëŠ”ì§? ì¡ŒëŠ”ì§?
	static int[] num, gyuArr, inArr;
	// gyuArr ê·œì˜?´ ì¹´ë“œ
	// inArr ?¸?˜?´ ì¹´ë“œ
	// num = inArr?„ ì¡°í•©?œ¼ë¡? ë§Œë“¤ ?ˆ˜ ?ˆ?Š” ê²ƒë“¤
	static boolean arrCheck[];	// ê·œì˜?´ ì¹´ë“œê°? ?“¤?–´?ˆ?Š” ê³³ì? true
	static boolean[] isSelected;	// ì¡°í•©?—?„œ ?‚¬?š©?•  ?„ ?ƒ ?–ˆ?Š”ì§? ?•ˆ ?–ˆ?Š”ì§? ?Œë³?
		
	public static void isWin(int gyu, int in) {	// ê°? ?Œ ?‚´?˜ 9ë²ˆì˜ ?´ê²¼ëŠ”ì§? ì¡ŒëŠ”ì§?ë¡? ì´í•© êµ¬í•˜ê¸?
		if(gyu > in) win += (gyu + in);
		else if(gyu < in) lose += (in + gyu);
	}
	
	public static void recursionPermutation(int cnt) {	// ì¡°í•© êµ¬í•˜ê¸?
		// 9ê°œë¡œ ì¡°í•©?„ ëª¨ë‘ ì°¾ìœ¼ë©? ê·? ì¡°í•©ê³? ê·œì˜?´?˜ ì¹´ë“œ ë¹„êµ ?‹œ?‘
		if (cnt == 9) {
			win = 0;
			lose = 0;
			for(int i = 0; i < 9; i++) {
				isWin(gyuArr[i], num[i]);	// ì¹´ë“œ ë¹„êµë¡? ì´í•© êµ¬í•˜ê¸?
			}
			if(win > lose) allWin++;	// ?´ê¸? ì´í•©?´ ì§? ì´í•©ë³´ë‹¤ ?¬ë©? ?“œ?””?–´ 1?Œ ?´ê¸? ê±?
			else if(win < lose) allLose++;	// ?•„?‹ˆë©? 1?Œ ì§? ê±?
			// ë¹„ê²¼?„ ê²½ìš°?Š” ?„£?–´ì£¼ì? ?•Š?Œ.
			return;
		} else {
			for (int i = 0; i < 9; i++) {
				if (isSelected[i])	// ?„ ?ƒ?œ ê²ƒì´ë©? ?„˜?–´ê°?
					continue;
				num[cnt] = inArr[i];	// ?ƒˆë¡œìš´ ì¡°í•© num?— inArr?„ ?ƒ?œ ê±? ?„£?–´ì£¼ê¸°
				isSelected[i] = true;	// ?„ ?ƒ?œ ê±? trueë¡?
				recursionPermutation(cnt + 1);	// cntë¥? ?Š˜? ¤?„œ ?‹¤?Œ ?ˆ˜ ì°¾ê¸°
				isSelected[i] = false;	// ?„ ?ƒ??˜ ê±? falseë¡? ?Œ? ¤ì£¼ê¸°(ì¤‘ë³µ ì¡°í•©?´ ?•„?‹ˆ?‹ˆê¹?!)
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			gyuArr = new int[9];
			inArr = new int[9];
			arrCheck = new boolean[19];
			// ë°°ì—´ ì´ˆê¸°?™”
			
			st = new StringTokenizer(br.readLine());
			// ê·œì˜?´ ì¹´ë“œ ?…? ¥
			for(int i = 0; i < 9; i++) {
				gyuArr[i] = Integer.parseInt(st.nextToken());
				// ê·œì˜?´ ì¹´ë“œ ë°°ì—´ ë§Œë“¤ê¸?
				arrCheck[gyuArr[i]] = true;
				// ê·œì˜?´ ì¹´ë“œ ?ˆ?Š” ê³? trueë¡? ë§Œë“¤?–´ì¤˜ì„œ ?¸?˜?´ ê±? ì°¾ì•„?•¼?•¨
			}
			
			int cnt = 0;
			for(int i = 1; i <= 18; i++) {
				if(!arrCheck[i]) inArr[cnt++] = i;
			}
			// false?¸ ë¶?ë¶„ì? ?¸?˜?´ê°? ê°?ì§?ê²? ?˜?Š” ì¹´ë“œ
			// ?¸?˜?´ ì¹´ë“œ ë°°ì—´ ë§Œë“¤ê¸?
			
			allWin = 0;	// ? „ì²? ?´ê¸? ?ˆ˜ ì´ˆê¸°?™”
			allLose = 0;	// ? „ì²? ì§? ?ˆ˜ ì´ˆê¸°?™”
			isSelected = new boolean[9];
			// ?„ ?ƒ?–ˆ?Š”ì§? ì²´í¬?•˜?Š” ë°°ì—´ ì´ˆê¸°?™”
			num = new int[9];
			// ì¡°í•© ë°°ì—´ ì´ˆê¸°?™”
			recursionPermutation(0);
			// ì¡°í•© ?Œë¦¬ê¸° ë°? ê²Œì„ ê³„ì‚°
			sb.append("#" + tc + " ").append(allWin + " " + allLose).append("\n");
		}
		System.out.print(sb.toString());
	}
}
