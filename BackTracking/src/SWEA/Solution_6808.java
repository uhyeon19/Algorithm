package SWEA;

import java.util.*;
import java.io.*;

public class Solution_6808 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int TC, win, lose, allWin, allLose;
	// ?��?��?���??��?�� ?��, �? 9�? 비교�? ?���? 총합 찾기/�? 총합 찾기, ?�� ?��?�� ?��?���? ?��?�� 총합?���? ?��겼는�? 졌는�?
	static int[] num, gyuArr, inArr;
	// gyuArr 규영?�� 카드
	// inArr ?��?��?�� 카드
	// num = inArr?�� 조합?���? 만들 ?�� ?��?�� 것들
	static boolean arrCheck[];	// 규영?�� 카드�? ?��?��?��?�� 곳�? true
	static boolean[] isSelected;	// 조합?��?�� ?��?��?�� ?��?�� ?��?���? ?�� ?��?���? ?���?
		
	public static void isWin(int gyu, int in) {	// �? ?�� ?��?�� 9번의 ?��겼는�? 졌는�?�? 총합 구하�?
		if(gyu > in) win += (gyu + in);
		else if(gyu < in) lose += (in + gyu);
	}
	
	public static void recursionPermutation(int cnt) {	// 조합 구하�?
		// 9개로 조합?�� 모두 찾으�? �? 조합�? 규영?��?�� 카드 비교 ?��?��
		if (cnt == 9) {
			win = 0;
			lose = 0;
			for(int i = 0; i < 9; i++) {
				isWin(gyuArr[i], num[i]);	// 카드 비교�? 총합 구하�?
			}
			if(win > lose) allWin++;	// ?���? 총합?�� �? 총합보다 ?���? ?��?��?�� 1?�� ?���? �?
			else if(win < lose) allLose++;	// ?��?���? 1?�� �? �?
			// 비겼?�� 경우?�� ?��?��주�? ?��?��.
			return;
		} else {
			for (int i = 0; i < 9; i++) {
				if (isSelected[i])	// ?��?��?�� 것이�? ?��?���?
					continue;
				num[cnt] = inArr[i];	// ?��로운 조합 num?�� inArr?��?��?�� �? ?��?��주기
				isSelected[i] = true;	// ?��?��?�� �? true�?
				recursionPermutation(cnt + 1);	// cnt�? ?��?��?�� ?��?�� ?�� 찾기
				isSelected[i] = false;	// ?��?��?��?�� �? false�? ?��?��주기(중복 조합?�� ?��?��?���?!)
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			gyuArr = new int[9];
			inArr = new int[9];
			arrCheck = new boolean[19];
			// 배열 초기?��
			
			st = new StringTokenizer(br.readLine());
			// 규영?�� 카드 ?��?��
			for(int i = 0; i < 9; i++) {
				gyuArr[i] = Integer.parseInt(st.nextToken());
				// 규영?�� 카드 배열 만들�?
				arrCheck[gyuArr[i]] = true;
				// 규영?�� 카드 ?��?�� �? true�? 만들?��줘서 ?��?��?�� �? 찾아?��?��
			}
			
			int cnt = 0;
			for(int i = 1; i <= 18; i++) {
				if(!arrCheck[i]) inArr[cnt++] = i;
			}
			// false?�� �?분�? ?��?��?���? �?�?�? ?��?�� 카드
			// ?��?��?�� 카드 배열 만들�?
			
			allWin = 0;	// ?���? ?���? ?�� 초기?��
			allLose = 0;	// ?���? �? ?�� 초기?��
			isSelected = new boolean[9];
			// ?��?��?��?���? 체크?��?�� 배열 초기?��
			num = new int[9];
			// 조합 배열 초기?��
			recursionPermutation(0);
			// 조합 ?��리기 �? 게임 계산
			sb.append("#" + tc + " ").append(allWin + " " + allLose).append("\n");
		}
		System.out.print(sb.toString());
	}
}
