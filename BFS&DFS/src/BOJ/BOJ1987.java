package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1987 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, ans = Integer.MIN_VALUE;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static char arr[][];
	static boolean[] visit;

	/**
	 * ���� ���� �����ϴ��� Ȯ���ϴ� �޼ҵ�
	 * @param nx
	 * @param ny
	 * @return
	 */
	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= R || ny >= C)
			return false;
		return true;
	}

	/**
	 * dfs Ž�� �޼ҵ�, ��Ʈ��ŷ���� ���� �������� ����
	 * @param x
	 * @param y
	 * @param cnt
	 */
	public static void dfs(int x, int y, int cnt) {
		if(visit[arr[x][y] - 'A']) {	// ���� ���ĺ��� �̹� �־ true�� ���
			ans = Integer.max(cnt, ans);	// cnt�� ���� ���ĺ� ���� ������ +1�ż� ���� ������ �� ���� ans�� ���ؼ� ���
			return;
		}

		visit[arr[x][y] - 'A'] = true;	// ���� Ž�� ��ġ�� ���ĺ� ��ġ �ε����� true ó��
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (isIn(nx, ny)) {	// ���� ��ġ�� �迭 ���� �ȿ� �ִٸ�
				dfs(nx, ny, cnt + 1);	// dfs ������
			}
		}
		visit[arr[x][y] - 'A'] = false;	// Ž�� ��ġ�� ���ĺ� ��ġ �ε��� ���� ó��
	}

	/**
	 * �ʱ�ȭ, ���� �޼ҵ�
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