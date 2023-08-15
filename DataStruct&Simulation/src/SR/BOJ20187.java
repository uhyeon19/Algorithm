package SR;

import java.io.*;
import java.util.*;

// https://www.youtube.com/watch?v=mEMieq2FrWY&ab_channel=%EB%8B%A4%EB%B9%88%EC%B9%98%EC%BD%94%EB%94%A9
public class BOJ20187 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int K, H, ans[][];
	static char arr[];
	static Map<Character, int[]> dicFold = new HashMap<>();
	static List<Integer> foldStep = new ArrayList<>();

	/**
	 * 접힐 때의 경우의 수를 담고 있는 List 만들기
	 * [8, 4, 2, 1]
	 * + 펼칠 때 늘어나는 구멍의 수를 담고 있는 Map 만들기
	 */
	public static void setMapFoldStep() {
		int tmpDU[] = { 2, 3, 0, 1 };
		dicFold.put('D', tmpDU);
		dicFold.put('U', tmpDU);
		int tmpRL[] = { 1, 0, 3, 2 };
		dicFold.put('R', tmpRL);
		dicFold.put('L', tmpRL);
		for (int i = K; i >= 0; i--) {
			foldStep.add((int) Math.pow(2, i));
		}
	}
	
	/**
	 * @param fi 상하로 접어주는 횟수
	 * @param fj 좌우로 접어주는 횟수
	 * @param si 열의 시작
	 * @param ei 열의 끝
	 * @param sj 행의 시작
	 * @param ej 행의 끝
	 */
	public static void solve(int fi, int fj, int si, int ei, int sj, int ej) {
		if (fi == K && fj == K) {
			ans[si][sj] = H;
			return;
		}
		// 접기 시작

		// 현재 접힌 상태 = 상하좌우 접힌 횟수
		char curFold = arr[fi + fj];

		// 밑으로 접었을 때
		if (curFold == 'D') {
			// 위아래로 접는 것이니 fi를 하나 키우고, fj는 그대로
			// ex) 0 ~ 7까지의 열이 위에서 아래로 반 접었으니 4 ~ 7로 좌표 변경
			// 열의 시작을 4로 바꾸기 위해 (si + ei) / 2 + 1을 해준다.
			solve(fi + 1, fj, (si + ei) / 2 + 1, ei, sj, ej);

			// 펼치기
			for (int i = 0; i < foldStep.get(fi); i++) {
				for (int j = sj; j < ej + 1; j++) {
					ans[si + i][j] = dicFold.get(curFold)[ans[ei - i][j]];
				}
			}
		} else if (curFold == 'U') {
			// 위아래로 접는 것이니 fi를 하나 키우고, fj는 그대로
			// ex) 0 ~ 7까지의 열이 아래에서 위로 반 접었으니 0 ~ 3로 좌표 변경
			// 열의 끝을 3로 바꾸기 위해 (si + ei) / 2를 해준다.
			solve(fi + 1, fj, si, (si + ei) / 2, sj, ej);

			// 펼치기
			for (int i = 0; i < foldStep.get(fi); i++) {
				for (int j = sj; j < ej + 1; j++) {
					ans[ei - i][j] = dicFold.get(curFold)[ans[si + i][j]];
				}
			}
		} else if (curFold == 'R') {
			// 좌우로 접는 것이니 fj를 하나 키우고, fi는 그대로
			// ex) 0 ~ 7까지의 행이 좌에서 우로 반 접었으니 4 ~ 7로 좌표 변경
			// 행의 시작을 4로 바꾸기 위해 (sj + ej) / 2 + 1을 해준다.
			solve(fi, fj + 1, si, ei, (sj + ej) / 2 + 1, ej);

			// 펼치기
			for (int i = si; i < ei + 1; i++) {
				for (int j = 0; j < foldStep.get(fj); j++) {
					ans[i][sj + j] = dicFold.get(curFold)[ans[i][ej - j]];
				}
			}
		} else if (curFold == 'L') {
			// 좌우로 접는 것이니 fj를 하나 키우고, fi는 그대로
			// ex) 0 ~ 7까지의 행이 우에서 좌로 반 접었으니 0 ~ 4로 좌표 변경
			// 행의 끝을 4로 바꾸기 위해 (sj + ej) / 2를 해준다.
			solve(fi, fj + 1, si, ei, sj, (sj + ej) / 2);

			// 펼치기
			for (int i = si; i < ei + 1; i++) {
				for (int j = 0; j < foldStep.get(fj); j++) {
					ans[i][ej - j] = dicFold.get(curFold)[ans[i][sj + j]];
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		K = Integer.parseInt(br.readLine());
		int size = (int) Math.pow(2, K);
		arr = new char[2 * K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * K; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		H = Integer.parseInt(br.readLine());
		ans = new int[size][size];
		setMapFoldStep();
		solve(0, 0, 0, size - 1, 0, size - 1);

		// 출력
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(ans[i][j] + " ");
			}
			System.out.println();
		}
	}
}