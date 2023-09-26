package BOJ;

import java.io.*;
import java.util.*;

public class BOJ19236 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int max = 0, arr[][] = new int[4][4], copyArr[][];
	static Shark shark;
	static List<Fish> fishes = new ArrayList<>();
	static List<Fish> copyFishes;

	static class Shark {
		int x, y, d, eatSum;

		Shark(int x, int y, int d, int eatSum) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.eatSum = eatSum;
		}
	}

	static class Fish {
		int x, y, num, d;
		boolean isAlive = true;

		public Fish(int x, int y, int d, int num, boolean isAlive) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.d = d;
			this.isAlive = isAlive;
		}
	}

	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
			return false;
		return true;
	}

	static void copyArr(int[][] arr) {
		copyArr = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 4; k++) {
				copyArr[i][k] = arr[i][k];
			}
		}
	}

	static void copyFishes(List<Fish> fishes) {
		copyFishes = new ArrayList<>();
		fishes.forEach(f -> copyFishes.add(new Fish(f.x, f.y, f.d, f.num, f.isAlive)));
	}

	public static void moveFish(Fish f, int[][] arr, List<Fish> fishes) {
		if (f.isAlive == false)
			return;

		for (int i = 0; i < 8; i++) {
			int d = (f.d + i) % 8;
			int nx = f.x + dx[d];
			int ny = f.y + dy[d];
			if (isIn(nx, ny) && arr[nx][ny] > -1) { // ������ ĭ�� �迭���� ���̰� �� �ƴ϶��
				arr[f.x][f.y] = 0; // �� ��ġ ����ֱ�
				if (arr[nx][ny] == 0) { // ���� ĭ�� ��ĭ���� �� �� �ִ� ����� �׳� �� ��ġ�� �� ĭ����
					f.x = nx;
					f.y = ny;
				} else { // ���� ĭ�� ����Ⱑ �ִٸ�
					Fish tmp = fishes.get(arr[nx][ny] - 1); // ����ĭ�� ����� ������ tmp�� �����´�
					tmp.x = f.x; // ����ĭ�� ����� ������ ���� �ִ� ��ġ�� �ٲ��ش�
					tmp.y = f.y;
					arr[f.x][f.y] = tmp.num; // �迭�� ����� ��ȣ�� �ٲ��ش�.
					f.x = nx; // ���� ���� �ִ� ������� ��ġ�� ���� ĭ���� �ű��.
					f.y = ny;
				}
				arr[nx][ny] = f.num; // ���� ĭ�� ����� ��ȣ�� ���� ����� ��ȣ�� ��ü�Ѵ�.
				f.d = d; // ���� ������� ������ 45���� ���� �� �ִٸ� ��� ������ ���̴� �����ش�.
				return; // ��..
			}
		}
	}

	public static void dfs(int[][] arr, Shark shark, List<Fish> fishes) {
		if (max < shark.eatSum) {
			max = shark.eatSum;
		}

		fishes.forEach(f -> moveFish(f, arr, fishes)); // ����� �̵�
		for (int cnt = 1; cnt < 4; cnt++) { // �� ������ �� �ִ� �ִ� �Ÿ��� 3�̴�. ������ 1~3������ �Ÿ��� ���ش�.
			int nx = shark.x + dx[shark.d] * cnt;
			int ny = shark.y + dy[shark.d] * cnt;

			if (isIn(nx, ny) && arr[nx][ny] > 0) { // �迭 ���� ���̸� ����Ⱑ �ִ� ĭ�̶�� ����� �԰� dfs
				copyArr(arr); // dfs ���� ������ ���� copy�迭�� ����Ѵ�.
				copyFishes(fishes); // dfs ���� ������ ���� copy����� ����Ʈ�� ����Ѵ�.
				copyArr[shark.x][shark.y] = 0; // �� ���� �ڸ��� ������ ������ 0���� ä���ְ�
				Fish f = copyFishes.get(arr[nx][ny] - 1); // ���� ĭ ����� ��������(������ �����...)
				Shark newShark = new Shark(f.x, f.y, f.d, shark.eatSum + f.num);
				f.isAlive = false; // ������ ������ false ó��
				copyArr[f.x][f.y] = -1; // ���� ����� ��ġ�� �� ����

				dfs(copyArr, newShark, copyFishes);
			}
		}
	}

	public static void init() throws IOException {
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < 4; k++) {
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1; // 1~8���� ������ ������ �츮�� �ε����� �ޱ� ������ 0~7�� �ٲ��ش�.
				fishes.add(new Fish(i, k, d, num, true));
				arr[i][k] = num;
			}
		}
		Collections.sort(fishes, (o1, o2) -> (o1.num - o2.num));

		Fish f = fishes.get(arr[0][0] - 1); // num - 1�� �ε��� ��ġ�� num ����Ⱑ ���� ���̴�.
		shark = new Shark(0, 0, f.d, f.num);
		f.isAlive = false;
		arr[0][0] = -1; // ��� ��ġ
	}

	public static void main(String[] args) throws IOException {
		init();
		dfs(arr, shark, fishes);
		bw.write(max + "\n");
		bw.flush();
		bw.close();
	}
}