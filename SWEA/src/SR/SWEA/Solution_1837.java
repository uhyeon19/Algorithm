package SR.SWEA;

import java.io.*;
import java.util.*;

public class Solution_1837 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();	// 출력값 저장 StringBuilder
	static int T, H, W, N, curD;	// 테스트케이스 수, H x W 배열을 만들 것, 명령어의 수 N, 현재 바라보고 있는 방향
	static int[] dx = { -1, 0, 1, 0 }; // 상우하좌
	static int[] dy = { 0, 1, 0, -1 };
	static char map[][];	// 맵 상태
	static Point curPoint;	// 현재 전차 위치

	/**
	 * 전차 위치 좌표를 저장하기 위한 Point 클래스
	 * @author SSAFY
	 */
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * 배열 범위 밖으로 나가는지 확인하는 메소드
	 * @param nx
	 * @param ny
	 * @return
	 */
	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= H || ny >= W)	// 배열의 범위 사이즈 밖인 경우
			return false;
		return true;
	}

	/**
	 * 명령어가 U, R, D, L일 때 전차 머리 돌리기 + 전차 이동이 가능하다면 이동까지 시키는 메소드
	 */
	public static void move() {
		int x = curPoint.x, y = curPoint.y;	// 현재 좌표
		int nx = x + dx[curD], ny = y + dy[curD];	// 다음 좌표
		
		if (curD == 0) map[x][y] = '^';		// 현재 바라보고 있는 방향에 따라 전차를 돌린다.
		else if (curD == 1) map[x][y] = '>';
		else if (curD == 2) map[x][y] = 'v';
		else if (curD == 3) map[x][y] = '<';
		
		// 다음 한 칸 갈 때, 갈 수 있는 범위이며 평지일 때
		if (isIn(nx, ny) && map[nx][ny] == '.') {
			map[nx][ny] = map[x][y];	// 다음 칸에 전차를 두고
			map[x][y] = '.';			// 현재 칸을 평지로 바꿔준다.
			curPoint = new Point(nx, ny);	// 현재 위치 변경
		}
	}

	/**
	 * 명렴어가 S인 경우 발사 메소드
	 */
	public static void shoot() {
		int cnt = 1;	// 바라보고 있는 방향을 기준으로 계속해 앞으로 나아가기 위해 설정
		while (true) {
			int nx = curPoint.x + dx[curD] * cnt;	// 앞칸, 앞앞칸... 쭉 확인
			int ny = curPoint.y + dy[curD] * cnt++;
			if (!isIn(nx, ny) || map[nx][ny] == '#')	// 범위를 벗어나거나 강철벽을 만나면 멈춤
				break;
			if (map[nx][ny] == '*') {	// 범위를 벗어나지 않고 강철벽도 만나지 않은 상태에서 벽돌을 먼저 만나면
				map[nx][ny] = '.';	// 벽돌을 부수고 평지가 되니 그 칸을 평지로 만들고 멈춤
				break;
			}
		}
	}

	public static void run() throws IOException {
		N = Integer.parseInt(br.readLine());	// 명령어의 갯수
		String str = br.readLine();	// 명령어 입력 받음
		for (int i = 0; i < N; i++) {	// 명령어 세기
			if (str.charAt(i) == 'U') {	// 현재 명령어가 무엇인지 조건
				curD = 0;	// 앞을 보고 있을 때는 curD = 0
				move();		// 이동
			} else if (str.charAt(i) == 'R') {
				curD = 1;	// 오른쪽을 보고 있을 때는 curD = 1
				move();		// 이동
			} else if (str.charAt(i) == 'D') {
				curD = 2;	// 밑을 보고 있을 때는 curD = 2
				move();		// 이동
			} else if (str.charAt(i) == 'L') {
				curD = 3;	// 왼쪽을 보고 있을 때는 curD = 3
				move();		// 이동
			} else if (str.charAt(i) == 'S') {	// S일 때는 shoot()
				shoot();
			}
		}
	}

	/**
	 * 초기 설정 메소드
	 * @throws IOException
	 */
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int k = 0; k < W; k++) {
				map[i][k] = str.charAt(k);
				if (map[i][k] == '^') {	// 현재 보고 있는 str의 char 값이 전차인 경우
					curD = 0;	// ^은 위를 바라보는 것, curD = 0
					curPoint = new Point(i, k);		// 처음 위치 세팅
				} else if (map[i][k] == '>') {
					curD = 1;	// >은 오른쪽을 바라보는 것, curD = 1
					curPoint = new Point(i, k);
				} else if (map[i][k] == '<') {
					curD = 3;	// <은 왼쪽을 바라보는 것, curD = 2
					curPoint = new Point(i, k);
				} else if (map[i][k] == 'v') {
					curD = 2;	// v는 아래를 바라보는 것, curD = 3
					curPoint = new Point(i, k);
				}
			}
		}
	}

	/**
	 * 배열을 출력하기 위한 메소드
	 */
	public static void print() {
		for (char[] arr : map) {	// 전형적인 배열 출력문을 foreach로 짰다
			for (char c : arr) {
				sb.append(c);	// 배열의 값 하나를 sb에 차근차근 쌓고
			}
			// 한 행을 쌓고 난 다음에는 줄바꿈 넣기!
			sb.append('\n');
		}
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			run();
			sb.append("#" + t + " ");
			print();
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
