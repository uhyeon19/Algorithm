package BOJ;

import java.io.*;
import java.util.*;

public class BOJ17281 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, playerScore[][], outCnt, ans = Integer.MIN_VALUE, playerOrder[];
	static boolean isSelected[];

	public static void play() {
		int total = 0;
		int turn = 1;
		for (int i = 1; i <= N; i++) {
			List<Integer> hit = new ArrayList<>();
			outCnt = 0;
			while (true) {
				int player = playerOrder[turn++];
				if(turn == 10) turn = 1;
				int nRuta = playerScore[i][player];
				if (nRuta == 0) outCnt++; 
				else hit.add(nRuta);
				if (outCnt == 3) break;
			}

			int hitSum = 0;
			for (int k = hit.size() - 1; k >= 0; k--) {
				hitSum += hit.get(k);
				if (hitSum >= 4) {
					total += k + 1;
					break;
				}
			}
		}
		ans = Integer.max(ans, total);

	}

	public static void Permutation(int cnt) {
		if (cnt == 10) {
			play();
			return;
		}
		for (int i = 1; i <= 9; i++) {
			if (!isSelected[i]) {
				playerOrder[i] = cnt;
				isSelected[i] = true;
				Permutation(cnt + 1);
				isSelected[i] = false;
			}
		}
	}

	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		playerScore = new int[N + 1][10];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 1; k <= 9; k++) {
				playerScore[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		isSelected = new boolean[10];
		playerOrder = new int[10];
		playerOrder[4] = 1;
		isSelected[4] = true;
	}

	public static void main(String[] args) throws IOException {
		init();
		Permutation(2);
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}