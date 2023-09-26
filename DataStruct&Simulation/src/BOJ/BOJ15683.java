package BOJ;

import java.io.*;
import java.util.*;

public class BOJ15683 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	// N, M, ��ü �迭, ���� ȸ�� ����(0 = 0��, 1 = 90��, 2 = 180��, 3 = 270��), ���䰪
	static int N, M, arr[][], indexD[], ans = Integer.MAX_VALUE;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };	// �� �� �� ��
	static int[][] curD = { {}, { 1 }, { 1, 2 }, { 0, 1 }, { 0, 1, 2 }, { 0, 1, 2, 3 } };	// ���� ������ ��������
	// 0�� cctv�� ����. 
	// 1�� cctv�� �������� �ٶ󺸴� ���·� ����
	// 2�� cctv�� �¿� �ٶ� ���� ����.
	// 3�� cctv�� �� �� �ٶ� ���� ����
	// 4�� cctv�� �¿�� �ٶ� ����
	// 5���� ��� �ٶ�
	static List<Point> cctvList = new ArrayList<>();
	// cctv ��ġ �� ��ȣ ��� ����Ʈ

	// cctv ��ǥ�� ��ȣ�� ��� Ŭ����
	static class Point {
		int x, y, cctvNo;

		public Point(int x, int y, int cctvNo) {
			super();
			this.x = x;
			this.y = y;
			this.cctvNo = cctvNo;
		}
	}

	// �迭 ���� ���� �ִ��� üũ
	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M)
			return false;
		return true;
	}

	// �⺻ ����
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
				if (arr[i][k] > 0 && arr[i][k] < 6)	// cctv�� ��� list�� �����
					cctvList.add(new Point(i, k, arr[i][k]));
			}
		}
		// cctv�� ������ŭ ȸ�� ���� ���� ���� ����(���ۺ���)
		indexD = new int[cctvList.size()];
	}

	// cctv�� �ٶ� �� �ִ� ����� true ó��
	public static void showCctv() {
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < cctvList.size(); i++) {
			int d = indexD[i]; // ȸ���� ��ŭ ���ִ�? (�� ����ŭ �Ӹ��� Ʋ ������)
			Point p = cctvList.get(i);	// ���� ���Ǵ� cctv
			for (int k = 0; k < curD[p.cctvNo].length; k++) { // cctv�� �ٶ󺸰� �ִ� ������ ��(1���� 1����, 2��3���� 2����, 3���� 3����, 4���� 4����)
				// cctv�� �ٶ� �� �ִ� ���⿡�� d��ŭ�� ȸ��. ex) �ε����� 90�� ȸ��(d = 1) �� 0���� 1�� �ٲ����
				d = (d + curD[p.cctvNo][k]) % 4;
				int nx = p.x;
				int ny = p.y;
				while (true) {	// ���� �����ų�, �迭�� ����� ������ �ݺ�
					nx = nx + dx[d];
					ny = ny + dy[d];
					if (!isIn(nx, ny) || arr[nx][ny] == 6)
						break;
					visited[nx][ny] = true;	// ���� ������ �ʾҰų�, ���� ����� visited�� true�� ������ֱ�(���캸�� ������� ��)
				}
			}
		}
		// true ó���� visited�迭��, arr�� 0�ΰ��� ���켭 �簢���� ã��
		countBlind(visited);
	}

	// cctv�� �� ����� ���� ���� sum���� ���ϰ� �� �� ���� ���� ans�� �ִ´�.
	// cctv�� �� ����� ���� �ߺ� �������� ã��
	public static void countBlind(boolean visited[][]) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				if (!visited[i][k] && arr[i][k] == 0) {
					sum++;
				}
			}
		}
		ans = Integer.min(sum, ans);
	}

	// �ߺ� ����
	// cctv�� ȸ���ϴ� ����� ���� ��� indexD�� ����� ��´�.
	// �� ����� ���� ��������� �� ����� ���� ���� cctv�� �ٶ� �� �ִ� ���� ã�� ans ���ϰ� ��...
	public static void cctvDirIndexComb(int cnt) {
		if (cnt == cctvList.size()) {
			showCctv();
			return;
		}

		for (int d = 0; d < 4; d++) {
			indexD[cnt] = d; // ȸ��! => 0�� ��� 0��, 1�� ��� 90��, 2�� ��� 180��, 3�� ��� 270��
			cctvDirIndexComb(cnt + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		cctvDirIndexComb(0);
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}