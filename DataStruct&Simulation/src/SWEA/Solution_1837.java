package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1837 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();	// 출력�? ???�� StringBuilder
	static int T, H, W, N, curD;	// ?��?��?���??��?�� ?��, H x W 배열?�� 만들 �?, 명령?��?�� ?�� N, ?��?�� 바라보고 ?��?�� 방향
	static int[] dx = { -1, 0, 1, 0 }; // ?��?��?���?
	static int[] dy = { 0, 1, 0, -1 };
	static char map[][];	// �? ?��?��
	static Point curPoint;	// ?��?�� ?���? ?���?

	/**
	 * ?���? ?���? 좌표�? ???��?���? ?��?�� Point ?��?��?��
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
	 * 배열 범위 밖으�? ?���??���? ?��?��?��?�� 메소?��
	 * @param nx
	 * @param ny
	 * @return
	 */
	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= H || ny >= W)	// 배열?�� 범위 ?��?���? 밖인 경우
			return false;
		return true;
	}

	/**
	 * 명령?���? U, R, D, L?�� ?�� ?���? 머리 ?��리기 + ?���? ?��?��?�� �??��?��?���? ?��?��까�? ?��?��?�� 메소?��
	 */
	public static void move() {
		int x = curPoint.x, y = curPoint.y;	// ?��?�� 좌표
		int nx = x + dx[curD], ny = y + dy[curD];	// ?��?�� 좌표
		
		if (curD == 0) map[x][y] = '^';		// ?��?�� 바라보고 ?��?�� 방향?�� ?��?�� ?��차�?? ?��린다.
		else if (curD == 1) map[x][y] = '>';
		else if (curD == 2) map[x][y] = 'v';
		else if (curD == 3) map[x][y] = '<';
		
		// ?��?�� ?�� �? �? ?��, �? ?�� ?��?�� 범위?���? ?���??�� ?��
		if (isIn(nx, ny) && map[nx][ny] == '.') {
			map[nx][ny] = map[x][y];	// ?��?�� 칸에 ?��차�?? ?���?
			map[x][y] = '.';			// ?��?�� 칸을 ?���?�? 바꿔�??��.
			curPoint = new Point(nx, ny);	// ?��?�� ?���? �?�?
		}
	}

	/**
	 * 명렴?���? S?�� 경우 발사 메소?��
	 */
	public static void shoot() {
		int cnt = 1;	// 바라보고 ?��?�� 방향?�� 기�??���? 계속?�� ?��?���? ?��?���?�? ?��?�� ?��?��
		while (true) {
			int nx = curPoint.x + dx[curD] * cnt;	// ?���?, ?��?���?... �? ?��?��
			int ny = curPoint.y + dy[curD] * cnt++;
			if (!isIn(nx, ny) || map[nx][ny] == '#')	// 범위�? 벗어?��거나 강철벽을 만나�? 멈춤
				break;
			if (map[nx][ny] == '*') {	// 범위�? 벗어?���? ?���? 강철벽도 만나�? ?��?? ?��?��?��?�� 벽돌?�� 먼�? 만나�?
				map[nx][ny] = '.';	// 벽돌?�� �??���? ?���?�? ?��?�� �? 칸을 ?���?�? 만들�? 멈춤
				break;
			}
		}
	}

	public static void run() throws IOException {
		N = Integer.parseInt(br.readLine());	// 명령?��?�� �??��
		String str = br.readLine();	// 명령?�� ?��?�� 받음
		for (int i = 0; i < N; i++) {	// 명령?�� ?���?
			if (str.charAt(i) == 'U') {	// ?��?�� 명령?���? 무엇?���? 조건
				curD = 0;	// ?��?�� 보고 ?��?�� ?��?�� curD = 0
				move();		// ?��?��
			} else if (str.charAt(i) == 'R') {
				curD = 1;	// ?��른쪽?�� 보고 ?��?�� ?��?�� curD = 1
				move();		// ?��?��
			} else if (str.charAt(i) == 'D') {
				curD = 2;	// 밑을 보고 ?��?�� ?��?�� curD = 2
				move();		// ?��?��
			} else if (str.charAt(i) == 'L') {
				curD = 3;	// ?��쪽을 보고 ?��?�� ?��?�� curD = 3
				move();		// ?��?��
			} else if (str.charAt(i) == 'S') {	// S?�� ?��?�� shoot()
				shoot();
			}
		}
	}

	/**
	 * 초기 ?��?�� 메소?��
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
				if (map[i][k] == '^') {	// ?��?�� 보고 ?��?�� str?�� char 값이 ?��차인 경우
					curD = 0;	// ^?? ?���? 바라보는 �?, curD = 0
					curPoint = new Point(i, k);		// 처음 ?���? ?��?��
				} else if (map[i][k] == '>') {
					curD = 1;	// >?? ?��른쪽?�� 바라보는 �?, curD = 1
					curPoint = new Point(i, k);
				} else if (map[i][k] == '<') {
					curD = 3;	// <?? ?��쪽을 바라보는 �?, curD = 2
					curPoint = new Point(i, k);
				} else if (map[i][k] == 'v') {
					curD = 2;	// v?�� ?��?���? 바라보는 �?, curD = 3
					curPoint = new Point(i, k);
				}
			}
		}
	}

	/**
	 * 배열?�� 출력?���? ?��?�� 메소?��
	 */
	public static void print() {
		for (char[] arr : map) {	// ?��?��?��?�� 배열 출력문을 foreach�? 짰다
			for (char c : arr) {
				sb.append(c);	// 배열?�� �? ?��?���? sb?�� 차근차근 ?���?
			}
			// ?�� ?��?�� ?���? ?�� ?��?��?��?�� 줄바�? ?���?!
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
