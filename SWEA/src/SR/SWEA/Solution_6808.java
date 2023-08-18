package SR.SWEA;

import java.util.*;
import java.io.*;

public class Solution_6808 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int TC, win, lose, allWin, allLose;
	// 테스트케이스 수, 각 9개 비교로 이긴 총합 찾기/진 총합 찾기, 한 판이 끝나고 나서 총합으로 이겼는지 졌는지
	static int[] num, gyuArr, inArr;
	// gyuArr 규영이 카드
	// inArr 인영이 카드
	// num = inArr을 조합으로 만들 수 있는 것들
	static boolean arrCheck[];	// 규영이 카드가 들어있는 곳은 true
	static boolean[] isSelected;	// 조합에서 사용할 선택 했는지 안 했는지 판별
		
	public static void isWin(int gyu, int in) {	// 각 판 내의 9번의 이겼는지 졌는지로 총합 구하기
		if(gyu > in) win += (gyu + in);
		else if(gyu < in) lose += (in + gyu);
	}
	
	public static void recursionPermutation(int cnt) {	// 조합 구하기
		// 9개로 조합을 모두 찾으면 그 조합과 규영이의 카드 비교 시작
		if (cnt == 9) {
			win = 0;
			lose = 0;
			for(int i = 0; i < 9; i++) {
				isWin(gyuArr[i], num[i]);	// 카드 비교로 총합 구하기
			}
			if(win > lose) allWin++;	// 이긴 총합이 진 총합보다 크면 드디어 1판 이긴 거
			else if(win < lose) allLose++;	// 아니면 1판 진 거
			// 비겼을 경우는 넣어주지 않음.
			return;
		} else {
			for (int i = 0; i < 9; i++) {
				if (isSelected[i])	// 선택된 것이면 넘어감
					continue;
				num[cnt] = inArr[i];	// 새로운 조합 num에 inArr선택된 거 넣어주기
				isSelected[i] = true;	// 선택된 건 true로
				recursionPermutation(cnt + 1);	// cnt를 늘려서 다음 수 찾기
				isSelected[i] = false;	// 선택됐던 거 false로 돌려주기(중복 조합이 아니니까!)
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			gyuArr = new int[9];
			inArr = new int[9];
			arrCheck = new boolean[19];
			// 배열 초기화
			
			st = new StringTokenizer(br.readLine());
			// 규영이 카드 입력
			for(int i = 0; i < 9; i++) {
				gyuArr[i] = Integer.parseInt(st.nextToken());
				// 규영이 카드 배열 만들기
				arrCheck[gyuArr[i]] = true;
				// 규영이 카드 있는 곳 true로 만들어줘서 인영이 거 찾아야함
			}
			
			int cnt = 0;
			for(int i = 1; i <= 18; i++) {
				if(!arrCheck[i]) inArr[cnt++] = i;
			}
			// false인 부분은 인영이가 가지게 되는 카드
			// 인영이 카드 배열 만들기
			
			allWin = 0;	// 전체 이긴 수 초기화
			allLose = 0;	// 전체 진 수 초기화
			isSelected = new boolean[9];
			// 선택했는지 체크하는 배열 초기화
			num = new int[9];
			// 조합 배열 초기화
			recursionPermutation(0);
			// 조합 돌리기 및 게임 계산
			sb.append("#" + tc + " ").append(allWin + " " + allLose).append("\n");
		}
		System.out.print(sb.toString());
	}
}
