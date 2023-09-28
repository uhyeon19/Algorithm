package BOJ;

import java.io.*;
import java.util.*;

public class BOJ20057 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int N, ans, arr[][];
	static int[] dx = { 0, 1, 0, -1 }; // 좌 하 우 상 이동
	static int[] dy = { -1, 0, 1, 0 };
	static int[][] spreadDx = { {0, 1, -1, 2, 1, -1, -2, 1, -1, 0}, {2, 1, 1, 0, 0, 0, 0, -1, -1, 1}, {0, 1, -1, 2, 1, -1, -2, 1, -1, 0}, {-2, -1, -1, 0, 0, 0, 0, 1, 1, -1} };
	static int[][] spreadDy = { {-2, -1, -1, 0, 0, 0, 0, 1, 1, -1}, {0, -1, 1, -2, -1, 1, 2, -1, 1, 0}, {2, 1, 1, 0, 0, 0, 0, -1, -1, 1}, {0, -1, 1, -2, -1, 1, 2, -1, 1, 0} };
	static int[] spreadRate = { 5, 10, 10, 2, 7, 7, 2, 1, 1 };
	static Sand sand;
	static boolean visited[][];

	static class Sand {
		int curD, x, y;

		public Sand(int curD, int x, int y) {
			this.curD = curD;
			this.x = x;
			this.y = y;
		}
	}

	// 범위 검색
	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}

	// 기저 조건
	public static boolean isFin(Sand s) {
		return s.x == 0 && s.y == 0;
	}

	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		sand = new Sand(0, N / 2, N / 2); // 시작 위치
	}

	public static void moveSand(Sand s) {
		int nx = s.x + dx[s.curD];
		int ny = s.y + dy[s.curD];
		int curSand = arr[nx][ny];
		for(int d = 0; d < 9; d++) {
			int spreadNx = nx + spreadDx[s.curD][d];
			int spreadNy = ny + spreadDy[s.curD][d];
			int res = arr[nx][ny] * spreadRate[d] / 100;
			if(isIn(spreadNx, spreadNy)){
				arr[spreadNx][spreadNy] += res;
			} else {
				ans += res;
			}
			curSand -= res;	
		}
		int ax = nx + spreadDx[s.curD][9];
		int ay = ny + spreadDy[s.curD][9];
		if(isIn(ax, ay)) arr[ax][ay] += curSand; 
		else ans += curSand;
		arr[nx][ny] = 0;
		s.x = nx;
		s.y = ny;
	}
	
	public static void simulation() {
		int cnt = 0;	// 이동 횟수
		int check = 0;	// 이동해야하는 칸만큼 이동을 2번 했는지
		int d = 1;	// 이동해야하는 칸 수
		while(true) {
			if(isFin(sand)) break;
			moveSand(sand);
			cnt++;
			if(d == cnt) {
				cnt = 0;
				sand.curD = (sand.curD + 1) % 4;
				check++;
			}
			if(check == 2) {
				check = 0;
				d++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		simulation();
		System.out.println(ans);
	}
}
