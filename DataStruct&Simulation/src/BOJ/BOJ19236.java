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
			if (isIn(nx, ny) && arr[nx][ny] > -1) { // 가려는 칸이 배열범위 안이고 상어가 아니라면
				arr[f.x][f.y] = 0; // 내 위치 비워주기
				if (arr[nx][ny] == 0) { // 다음 칸이 빈칸으로 갈 수 있는 경우라면 그냥 내 위치만 그 칸으로
					f.x = nx;
					f.y = ny;
				} else { // 다음 칸에 물고기가 있다면
					Fish tmp = fishes.get(arr[nx][ny] - 1); // 다음칸의 물고기 정보를 tmp로 가져온다
					tmp.x = f.x; // 다음칸의 물고기 정보를 현재 있던 위치로 바꿔준다
					tmp.y = f.y;
					arr[f.x][f.y] = tmp.num; // 배열의 물고기 번호를 바꿔준다.
					f.x = nx; // 현재 보고 있던 물고기의 위치를 다음 칸으로 옮긴다.
					f.y = ny;
				}
				arr[nx][ny] = f.num; // 다음 칸의 물고기 번호를 현재 물고기 번호로 대체한다.
				f.d = d; // 현재 물고기의 방향은 45도씩 돌릴 수 있다면 계속 돌렸을 것이니 돌려준다.
				return; // 끝..
			}
		}
	}

	public static void dfs(int[][] arr, Shark shark, List<Fish> fishes) {
		if (max < shark.eatSum) {
			max = shark.eatSum;
		}

		fishes.forEach(f -> moveFish(f, arr, fishes)); // 물고기 이동
		for (int cnt = 1; cnt < 4; cnt++) { // 상어가 움직일 수 있는 최대 거리는 3이다. 때문에 1~3까지의 거리를 봐준다.
			int nx = shark.x + dx[shark.d] * cnt;
			int ny = shark.y + dy[shark.d] * cnt;

			if (isIn(nx, ny) && arr[nx][ny] > 0) { // 배열 범위 내이며 물고기가 있는 칸이라면 물고기 먹고 dfs
				copyArr(arr); // dfs 상태 원복을 위해 copy배열을 사용한다.
				copyFishes(fishes); // dfs 상태 원복을 위해 copy물고기 리스트를 사용한다.
				copyArr[shark.x][shark.y] = 0; // 상어가 현재 자리를 떠나기 때문에 0으로 채워주고
				Fish f = copyFishes.get(arr[nx][ny] - 1); // 다음 칸 물고기 가져오기(먹히는 물고기...)
				Shark newShark = new Shark(f.x, f.y, f.d, shark.eatSum + f.num);
				f.isAlive = false; // 먹혔기 때문에 false 처리
				copyArr[f.x][f.y] = -1; // 먹힌 물고기 위치에 상어가 존재

				dfs(copyArr, newShark, copyFishes);
			}
		}
	}

	public static void init() throws IOException {
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < 4; k++) {
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1; // 1~8까지 나오는 방향을 우리는 인덱스로 받기 때문에 0~7로 바꿔준다.
				fishes.add(new Fish(i, k, d, num, true));
				arr[i][k] = num;
			}
		}
		Collections.sort(fishes, (o1, o2) -> (o1.num - o2.num));

		Fish f = fishes.get(arr[0][0] - 1); // num - 1의 인덱스 위치에 num 물고기가 있을 것이다.
		shark = new Shark(0, 0, f.d, f.num);
		f.isAlive = false;
		arr[0][0] = -1; // 상어 위치
	}

	public static void main(String[] args) throws IOException {
		init();
		dfs(arr, shark, fishes);
		bw.write(max + "\n");
		bw.flush();
		bw.close();
	}
}