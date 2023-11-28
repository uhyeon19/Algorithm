package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1987 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, ans = 1;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static char arr[][];
	static boolean[] visit;

	/**
	 * 범위 내에 존재하는지 확인하는 메소드
	 * @param nx
	 * @param ny
	 * @return
	 */
	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < R && ny < C;
	}

	/**
	 * dfs 탐색 메소드, 백트래킹으로 멈출 기저조건 존재
	 * @param x
	 * @param y
	 * @param cnt
	 */
	public static void dfs(int x, int y, int cnt) {
		if(visit[arr[x][y] - 'A']) {	// 같은 알파벳이 이미 있어서 true인 경우
			ans = Integer.max(cnt, ans);	// cnt가 같은 알파벳 만날 때까지 +1돼서 오고 있으니 이 값을 ans와 비교해서 담기
			return;
		}

		visit[arr[x][y] - 'A'] = true;	// 현재 탐색 위치의 알파벳 위치 인덱스를 true 처리
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (isIn(nx, ny)) {	// 다음 위치가 배열 범위 안에 있다면
				dfs(nx, ny, cnt + 1);	// dfs 돌리기
			}
		}
		visit[arr[x][y] - 'A'] = false;	// 탐색 위치의 알파벳 위치 인덱스 원복 처리
	}

	/**
	 * 초기화, 세팅 메소드
	 * @throws IOException
	 */
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		visit = new boolean[27];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int k = 0; k < C; k++) {
				arr[i][k] = str.charAt(k);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		dfs(0, 0, 0);
		System.out.println(ans);
	}
}