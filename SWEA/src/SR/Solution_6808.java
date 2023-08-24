package SR;

import java.util.*;
import java.io.*;

public class Solution_6808 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int TC, win, lose, allWin, allLose;
	// ??€?ΈμΌ??΄?€ ?, κ°? 9κ°? λΉκ΅λ‘? ?΄κΈ? μ΄ν© μ°ΎκΈ°/μ§? μ΄ν© μ°ΎκΈ°, ? ??΄ ??κ³? ?? μ΄ν©?Όλ‘? ?΄κ²Όλμ§? μ‘λμ§?
	static int[] num, gyuArr, inArr;
	// gyuArr κ·μ?΄ μΉ΄λ
	// inArr ?Έ??΄ μΉ΄λ
	// num = inArr? μ‘°ν©?Όλ‘? λ§λ€ ? ?? κ²λ€
	static boolean arrCheck[];	// κ·μ?΄ μΉ΄λκ°? ?€?΄?? κ³³μ? true
	static boolean[] isSelected;	// μ‘°ν©?? ?¬?©?  ? ? ??μ§? ? ??μ§? ?λ³?
		
	public static void isWin(int gyu, int in) {	// κ°? ? ?΄? 9λ²μ ?΄κ²Όλμ§? μ‘λμ§?λ‘? μ΄ν© κ΅¬νκΈ?
		if(gyu > in) win += (gyu + in);
		else if(gyu < in) lose += (in + gyu);
	}
	
	public static void recursionPermutation(int cnt) {	// μ‘°ν© κ΅¬νκΈ?
		// 9κ°λ‘ μ‘°ν©? λͺ¨λ μ°ΎμΌλ©? κ·? μ‘°ν©κ³? κ·μ?΄? μΉ΄λ λΉκ΅ ??
		if (cnt == 9) {
			win = 0;
			lose = 0;
			for(int i = 0; i < 9; i++) {
				isWin(gyuArr[i], num[i]);	// μΉ΄λ λΉκ΅λ‘? μ΄ν© κ΅¬νκΈ?
			}
			if(win > lose) allWin++;	// ?΄κΈ? μ΄ν©?΄ μ§? μ΄ν©λ³΄λ€ ?¬λ©? ???΄ 1? ?΄κΈ? κ±?
			else if(win < lose) allLose++;	// ??λ©? 1? μ§? κ±?
			// λΉκ²Ό? κ²½μ°? ?£?΄μ£Όμ? ??.
			return;
		} else {
			for (int i = 0; i < 9; i++) {
				if (isSelected[i])	// ? ?? κ²μ΄λ©? ??΄κ°?
					continue;
				num[cnt] = inArr[i];	// ?λ‘μ΄ μ‘°ν© num? inArr? ?? κ±? ?£?΄μ£ΌκΈ°
				isSelected[i] = true;	// ? ?? κ±? trueλ‘?
				recursionPermutation(cnt + 1);	// cntλ₯? ?? €? ?€? ? μ°ΎκΈ°
				isSelected[i] = false;	// ? ??? κ±? falseλ‘? ?? €μ£ΌκΈ°(μ€λ³΅ μ‘°ν©?΄ ???κΉ?!)
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			gyuArr = new int[9];
			inArr = new int[9];
			arrCheck = new boolean[19];
			// λ°°μ΄ μ΄κΈ°?
			
			st = new StringTokenizer(br.readLine());
			// κ·μ?΄ μΉ΄λ ?? ₯
			for(int i = 0; i < 9; i++) {
				gyuArr[i] = Integer.parseInt(st.nextToken());
				// κ·μ?΄ μΉ΄λ λ°°μ΄ λ§λ€κΈ?
				arrCheck[gyuArr[i]] = true;
				// κ·μ?΄ μΉ΄λ ?? κ³? trueλ‘? λ§λ€?΄μ€μ ?Έ??΄ κ±? μ°Ύμ?Ό?¨
			}
			
			int cnt = 0;
			for(int i = 1; i <= 18; i++) {
				if(!arrCheck[i]) inArr[cnt++] = i;
			}
			// false?Έ λΆ?λΆμ? ?Έ??΄κ°? κ°?μ§?κ²? ?? μΉ΄λ
			// ?Έ??΄ μΉ΄λ λ°°μ΄ λ§λ€κΈ?
			
			allWin = 0;	// ? μ²? ?΄κΈ? ? μ΄κΈ°?
			allLose = 0;	// ? μ²? μ§? ? μ΄κΈ°?
			isSelected = new boolean[9];
			// ? ???μ§? μ²΄ν¬?? λ°°μ΄ μ΄κΈ°?
			num = new int[9];
			// μ‘°ν© λ°°μ΄ μ΄κΈ°?
			recursionPermutation(0);
			// μ‘°ν© ?λ¦¬κΈ° λ°? κ²μ κ³μ°
			sb.append("#" + tc + " ").append(allWin + " " + allLose).append("\n");
		}
		System.out.print(sb.toString());
	}
}
