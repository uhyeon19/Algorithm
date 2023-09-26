package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1600 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int K, W, H, arr[][], ans = 0;
	static int[] dx = { -1, 0, 1, 0 };	// ���� ������� �����̴� ���
	static int[] dy = { 0, 1, 0, -1 };
	static int[] ddx = { -2, -2, -1, -1, 1, 1, 2, 2 };	// �� ó�� �����̴� ���
	static int[] ddy = { -1, 1, -2, 2, -2, 2, -1, 1 };
	static boolean[][][] visited;	// ��ó�� �����̴� ��츦 �Ϸ��µ�, ������ó�� �������� �� ���� ���ļ� �� ���� ��찡 ������ �ʰ� 3���� �迭

	/**
	 * bfs���� ť�� ���� ��ǥ�� ��ó�� �󸶳� ������ �� �ִ��� ���� Ŭ����
	 */
	public static class Point {
		int x, y, k;	// (x, y) ��ǥ, ��ó�� ������ �� �ִ� Ƚ��

		public Point(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}

	/**
	 * �迭 ���ο� �ִ��� Ȯ���ϴ� �޼ҵ�
	 * @param nx
	 * @param ny
	 * @return
	 */
	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < H && ny < W;
	}

	/**
	 * bfs �޼ҵ�
	 * @param x
	 * @param y
	 * @return
	 */
	public static int bfs(int x, int y) {	// ó������ ������ 0, 0�� �Ű�����
		Queue<Point> q = new LinkedList<>();	// �� �� �ִ� ���� ���� ť
		q.offer(new Point(x, y, K));		// ó�� ��ġ ���
		visited[x][y][K] = true;	// visited �迭 �ʱ�ȭ
		int cnt = 0;
		
		while (!q.isEmpty()) {
			int size = q.size();
			while(--size >= 0) {
				Point p = q.poll();		// �� �� �ִ� �� �̱�
				if (p.x == H - 1 && p.y == W - 1)	// �� �� �ִ� ������ ������ ���� ������ ��ġ��� ������ Ƚ�� �̱�
					return cnt;

				for (int i = 0; i < 4; i++) {	// 4�� Ž��
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if (isIn(nx, ny) && !visited[nx][ny][p.k] && arr[nx][ny] == 0) {	// ���� ��ġ�� �� �� �ִ� ������ Ȯ��
						visited[nx][ny][p.k] = true;	// ���� ��ó�� ������ �� �ִ� ��(��ų�� �� ���� �ƴϴϱ�) 3���� ���� true ó��
						q.offer(new Point(nx, ny, p.k));
					}
				}

				// ���� ��ó�� ������ �� �ִ� ��찡 ���� �����ִٸ�
				if (p.k > 0) {
					for (int i = 0; i < 8; i++) {	// ��ó�� ������ �� �ִ� �� Ž��
						int nx = p.x + ddx[i];
						int ny = p.y + ddy[i];
						if (isIn(nx, ny) && !visited[nx][ny][p.k - 1] && arr[nx][ny] == 0) {	// �� �� �ִٸ�
							visited[nx][ny][p.k - 1] = true;	// ��ó�� ������ �� �ִ� �� �ϳ� �ٿ���(��ų ��������ϱ�) 3���� ���� true ó��
							q.offer(new Point(nx, ny, p.k - 1));	// ��ų ���� �ϳ� ���δ�.
						}
					}
				}
			}
			cnt++;
		}
		return -1;
	}

	public static void init() throws IOException {
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		visited = new boolean[H][W][K + 1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < W; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		bw.write(bfs(0, 0) + "\n");
		bw.flush();
		bw.close();
	}
}
