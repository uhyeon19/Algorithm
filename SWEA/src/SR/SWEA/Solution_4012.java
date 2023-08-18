package SR.SWEA;

import java.io.*;
import java.util.*;

public class Solution_4012 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int TC, N, arr[][], ans;
	static boolean isSelected[];

	public static int minSynergy() {
		int x = 0;	// 선택한 식재료로 만드는 음식 A
		int y = 0;	// 선택하지 않은 식재료로 만드는 다른 음식 B
		// 완전 탐색
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(i == k) continue;	// 서로 같은 식재료는 사용 X (0임!)
				if(isSelected[i] && isSelected[k]) {	// 선택한 식재료인지 확인
					x += arr[i][k];
				} else if(!isSelected[i] && !isSelected[k]) {	// 선택 안 한 식재료인지 확인
					y += arr[i][k];
				}
			}
		}
		return Math.abs(x - y);	// 음식 A의 시너지, 음식 B의 시너지 차이 절댓값
	}
	
	public static void recursionComb(int index, int cnt) { // 조합 구하기
		if (cnt == N / 2) {
			ans = Math.min(ans, minSynergy());
			// 음식 A의 시너지, 음식 B의 시너지 차이 절댓값과 이전했던 요리들 절댓값 비교
			return;
		} else {
			for (int i = index; i < N; i++) {
				if (isSelected[i]) // 선택된 것이면 넘어감
					continue;
				isSelected[i] = true; // 선택된 건 true로
				recursionComb(i + 1, cnt + 1); // cnt를 늘려서 다음 수 찾기
				isSelected[i] = false; // 선택됐던 거 false로 돌려주기
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
			
			ans = Integer.MAX_VALUE;	// ans 초기화
			isSelected = new boolean[N];	// 선택 배열 초기화
			recursionComb(0, 0);
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.print(sb.toString());
	}
}
