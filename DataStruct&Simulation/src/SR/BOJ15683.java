package SR;

import java.io.*;
import java.util.*;

public class BOJ15683 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	// N, M, 전체 배열, 현재 회전 도수(0 = 0도, 1 = 90도, 2 = 180도, 3 = 270도), 정답값
	static int N, M, arr[][], indexD[], ans = Integer.MAX_VALUE;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };	// 상 우 하 좌
	static int[][] curD = { {}, { 1 }, { 1, 2 }, { 0, 1 }, { 0, 1, 2 }, { 0, 1, 2, 3 } };	// 문제 사진을 기준으로
	// 0번 cctv는 없다. 
	// 1번 cctv는 오른쪽을 바라보는 형태로 시작
	// 2번 cctv는 좌우 바라본 상태 시작.
	// 3번 cctv는 상 우 바라본 상태 시작
	// 4번 cctv는 좌우상 바라본 상태
	// 5번은 모두 바라봄
	static List<Point> cctvList = new ArrayList<>();
	// cctv 위치 및 번호 담는 리스트

	// cctv 좌표와 번호를 담는 클래스
	static class Point {
		int x, y, cctvNo;

		public Point(int x, int y, int cctvNo) {
			super();
			this.x = x;
			this.y = y;
			this.cctvNo = cctvNo;
		}
	}

	// 배열 범위 내에 있는지 체크
	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M)
			return false;
		return true;
	}

	// 기본 세팅
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
				if (arr[i][k] > 0 && arr[i][k] < 6)	// cctv인 경우 list에 담아줌
					cctvList.add(new Point(i, k, arr[i][k]));
			}
		}
		// cctv의 개수만큼 회전 방향 수를 만들 것임(빙글빙글)
		indexD = new int[cctvList.size()];
	}

	// cctv가 바라볼 수 있는 방향들 true 처리
	public static void showCctv() {
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < cctvList.size(); i++) {
			int d = indexD[i]; // 회전이 얼만큼 돼있니? (몇 도만큼 머리를 틀 것인지)
			Point p = cctvList.get(i);	// 현재 살피는 cctv
			for (int k = 0; k < curD[p.cctvNo].length; k++) { // cctv가 바라보고 있는 방향의 수(1번은 1군데, 2번3번은 2군데, 3번은 3군데, 4번은 4군데)
				// cctv가 바라볼 수 있는 방향에서 d만큼을 회전. ex) 인덱스를 90도 회전(d = 1) 시 0에서 1로 바꿔야함
				d = (d + curD[p.cctvNo][k]) % 4;
				int nx = p.x;
				int ny = p.y;
				while (true) {	// 벽을 만나거나, 배열을 벗어나기 전까지 반복
					nx = nx + dx[d];
					ny = ny + dy[d];
					if (!isIn(nx, ny) || arr[nx][ny] == 6)
						break;
					visited[nx][ny] = true;	// 벽을 만나지 않았거나, 범위 내라면 visited를 true로 만들어주기(살펴보는 범위라는 뜻)
				}
			}
		}
		// true 처리된 visited배열과, arr이 0인곳을 살펴서 사각지대 찾기
		countBlind(visited);
	}

	// cctv의 각 경우의 수에 따른 sum값을 구하고 이 중 낮은 값을 ans에 넣는다.
	// cctv의 각 경우의 수는 중복 조합으로 찾음
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

	// 중복 조합
	// cctv가 회전하는 경우의 수를 모두 indexD로 만들어 담는다.
	// 각 경우의 수가 만들어지면 그 경우의 수에 따른 cctv가 바라볼 수 있는 수를 찾고 ans 구하고 비교...
	public static void cctvDirIndexComb(int cnt) {
		if (cnt == cctvList.size()) {
			showCctv();
			return;
		}

		for (int d = 0; d < 4; d++) {
			indexD[cnt] = d; // 회전! => 0일 경우 0도, 1일 경우 90도, 2일 경우 180도, 3일 경우 270도
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