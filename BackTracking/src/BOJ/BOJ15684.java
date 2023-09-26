package BOJ;

import java.io.*;
import java.util.*;

public class BOJ15684 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, M, H, arr[][], answer;
	static boolean finish;

	public static boolean checkLadder() {
		for (int i = 1; i <= N; i++) {
			int x = 1, y = i;
			for (int k = 0; k < H; k++) {
				if (arr[x][y] == 1)
					y++; // 오른쪽 이동
				else if (arr[x][y] == 2)
					y--; // 왼쪽 이동
				x++; // 아래로 이동
			}
			if (y != i)
				return false; // 이동 결과 도착 y값이 인덱스가 아니면
		}
		return true;
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H + 1][N + 1];

		for (int i = 0; i < M; i++) { // 1인 경우에는 오른쪽 이동, 2인 경우에는 왼쪽 이동
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[r][c] = 1;
			arr[r][c + 1] = 2;
		}
	}

	public static void comb(int index, int cnt) {
		if(finish) return;
		if (cnt == answer) {
			if(checkLadder()) finish = true;
			return;
		}

		for (int r = index; r <= H; r++) {
			for (int c = 1; c < N; c++) {
				if (arr[r][c] == 0 && arr[r][c + 1] == 0) {
					arr[r][c] = 1;
					arr[r][c + 1] = 2;
					comb(r, cnt + 1);
					arr[r][c] = arr[r][c + 1] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		for (int i = 0; i <= 3; i++) { // 사다리의 개수는 0~3개까지만 놓아보면 된다.
			answer = i;
			comb(1, 0);
			if (finish) break;
		}
		bw.write(finish ? answer + "\n" : -1 + "\n");
		bw.flush();
		bw.close();
	}
}
