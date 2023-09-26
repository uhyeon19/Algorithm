package SWEA;

import java.io.*;
import java.util.*;

public class Solution_4012 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int TC, N, arr[][], ans;
	static boolean isSelected[];

	public static int minSynergy() {
		int x = 0;	// ?��?��?�� ?��?��료로 만드?�� ?��?�� A
		int y = 0;	// ?��?��?���? ?��?? ?��?��료로 만드?�� ?���? ?��?�� B
		// ?��?�� ?��?��
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(i == k) continue;	// ?���? 같�? ?��?��료는 ?��?�� X (0?��!)
				if(isSelected[i] && isSelected[k]) {	// ?��?��?�� ?��?��료인�? ?��?��
					x += arr[i][k];
				} else if(!isSelected[i] && !isSelected[k]) {	// ?��?�� ?�� ?�� ?��?��료인�? ?��?��
					y += arr[i][k];
				}
			}
		}
		return Math.abs(x - y);	// ?��?�� A?�� ?��?���?, ?��?�� B?�� ?��?���? 차이 ?��?���?
	}
	
	public static void recursionComb(int index, int cnt) { // 조합 구하�?
		if (cnt == N / 2) {
			ans = Math.min(ans, minSynergy());
			// ?��?�� A?�� ?��?���?, ?��?�� B?�� ?��?���? 차이 ?��?��값과 ?��?��?��?�� ?��리들 ?��?���? 비교
			return;
		} else {
			for (int i = index; i < N; i++) {
				if (isSelected[i]) // ?��?��?�� 것이�? ?��?���?
					continue;
				isSelected[i] = true; // ?��?��?�� �? true�?
				recursionComb(i + 1, cnt + 1); // cnt�? ?��?��?�� ?��?�� ?�� 찾기
				isSelected[i] = false; // ?��?��?��?�� �? false�? ?��?��주기
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
			
			ans = Integer.MAX_VALUE;	// ans 초기?��
			isSelected = new boolean[N];	// ?��?�� 배열 초기?��
			recursionComb(0, 0);
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.print(sb.toString());
	}
}
