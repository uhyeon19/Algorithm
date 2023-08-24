package SR;

import java.io.*;
import java.util.*;

public class Solution_1837 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();	// ì¶œë ¥ê°? ???¥ StringBuilder
	static int T, H, W, N, curD;	// ?…Œ?Š¤?Š¸ì¼??´?Š¤ ?ˆ˜, H x W ë°°ì—´?„ ë§Œë“¤ ê²?, ëª…ë ¹?–´?˜ ?ˆ˜ N, ?˜„?¬ ë°”ë¼ë³´ê³  ?ˆ?Š” ë°©í–¥
	static int[] dx = { -1, 0, 1, 0 }; // ?ƒ?š°?•˜ì¢?
	static int[] dy = { 0, 1, 0, -1 };
	static char map[][];	// ë§? ?ƒ?ƒœ
	static Point curPoint;	// ?˜„?¬ ? „ì°? ?œ„ì¹?

	/**
	 * ? „ì°? ?œ„ì¹? ì¢Œí‘œë¥? ???¥?•˜ê¸? ?œ„?•œ Point ?´?˜?Š¤
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
	 * ë°°ì—´ ë²”ìœ„ ë°–ìœ¼ë¡? ?‚˜ê°??Š”ì§? ?™•?¸?•˜?Š” ë©”ì†Œ?“œ
	 * @param nx
	 * @param ny
	 * @return
	 */
	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= H || ny >= W)	// ë°°ì—´?˜ ë²”ìœ„ ?‚¬?´ì¦? ë°–ì¸ ê²½ìš°
			return false;
		return true;
	}

	/**
	 * ëª…ë ¹?–´ê°? U, R, D, L?¼ ?•Œ ? „ì°? ë¨¸ë¦¬ ?Œë¦¬ê¸° + ? „ì°? ?´?™?´ ê°??Š¥?•˜?‹¤ë©? ?´?™ê¹Œì? ?‹œ?‚¤?Š” ë©”ì†Œ?“œ
	 */
	public static void move() {
		int x = curPoint.x, y = curPoint.y;	// ?˜„?¬ ì¢Œí‘œ
		int nx = x + dx[curD], ny = y + dy[curD];	// ?‹¤?Œ ì¢Œí‘œ
		
		if (curD == 0) map[x][y] = '^';		// ?˜„?¬ ë°”ë¼ë³´ê³  ?ˆ?Š” ë°©í–¥?— ?”°?¼ ? „ì°¨ë?? ?Œë¦°ë‹¤.
		else if (curD == 1) map[x][y] = '>';
		else if (curD == 2) map[x][y] = 'v';
		else if (curD == 3) map[x][y] = '<';
		
		// ?‹¤?Œ ?•œ ì¹? ê°? ?•Œ, ê°? ?ˆ˜ ?ˆ?Š” ë²”ìœ„?´ë©? ?‰ì§??¼ ?•Œ
		if (isIn(nx, ny) && map[nx][ny] == '.') {
			map[nx][ny] = map[x][y];	// ?‹¤?Œ ì¹¸ì— ? „ì°¨ë?? ?‘ê³?
			map[x][y] = '.';			// ?˜„?¬ ì¹¸ì„ ?‰ì§?ë¡? ë°”ê¿”ì¤??‹¤.
			curPoint = new Point(nx, ny);	// ?˜„?¬ ?œ„ì¹? ë³?ê²?
		}
	}

	/**
	 * ëª…ë ´?–´ê°? S?¸ ê²½ìš° ë°œì‚¬ ë©”ì†Œ?“œ
	 */
	public static void shoot() {
		int cnt = 1;	// ë°”ë¼ë³´ê³  ?ˆ?Š” ë°©í–¥?„ ê¸°ì??œ¼ë¡? ê³„ì†?•´ ?•?œ¼ë¡? ?‚˜?•„ê°?ê¸? ?œ„?•´ ?„¤? •
		while (true) {
			int nx = curPoint.x + dx[curD] * cnt;	// ?•ì¹?, ?•?•ì¹?... ì­? ?™•?¸
			int ny = curPoint.y + dy[curD] * cnt++;
			if (!isIn(nx, ny) || map[nx][ny] == '#')	// ë²”ìœ„ë¥? ë²—ì–´?‚˜ê±°ë‚˜ ê°•ì² ë²½ì„ ë§Œë‚˜ë©? ë©ˆì¶¤
				break;
			if (map[nx][ny] == '*') {	// ë²”ìœ„ë¥? ë²—ì–´?‚˜ì§? ?•Šê³? ê°•ì² ë²½ë„ ë§Œë‚˜ì§? ?•Š?? ?ƒ?ƒœ?—?„œ ë²½ëŒ?„ ë¨¼ì? ë§Œë‚˜ë©?
				map[nx][ny] = '.';	// ë²½ëŒ?„ ë¶??ˆ˜ê³? ?‰ì§?ê°? ?˜?‹ˆ ê·? ì¹¸ì„ ?‰ì§?ë¡? ë§Œë“¤ê³? ë©ˆì¶¤
				break;
			}
		}
	}

	public static void run() throws IOException {
		N = Integer.parseInt(br.readLine());	// ëª…ë ¹?–´?˜ ê°??ˆ˜
		String str = br.readLine();	// ëª…ë ¹?–´ ?…? ¥ ë°›ìŒ
		for (int i = 0; i < N; i++) {	// ëª…ë ¹?–´ ?„¸ê¸?
			if (str.charAt(i) == 'U') {	// ?˜„?¬ ëª…ë ¹?–´ê°? ë¬´ì—‡?¸ì§? ì¡°ê±´
				curD = 0;	// ?•?„ ë³´ê³  ?ˆ?„ ?•Œ?Š” curD = 0
				move();		// ?´?™
			} else if (str.charAt(i) == 'R') {
				curD = 1;	// ?˜¤ë¥¸ìª½?„ ë³´ê³  ?ˆ?„ ?•Œ?Š” curD = 1
				move();		// ?´?™
			} else if (str.charAt(i) == 'D') {
				curD = 2;	// ë°‘ì„ ë³´ê³  ?ˆ?„ ?•Œ?Š” curD = 2
				move();		// ?´?™
			} else if (str.charAt(i) == 'L') {
				curD = 3;	// ?™¼ìª½ì„ ë³´ê³  ?ˆ?„ ?•Œ?Š” curD = 3
				move();		// ?´?™
			} else if (str.charAt(i) == 'S') {	// S?¼ ?•Œ?Š” shoot()
				shoot();
			}
		}
	}

	/**
	 * ì´ˆê¸° ?„¤? • ë©”ì†Œ?“œ
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
				if (map[i][k] == '^') {	// ?˜„?¬ ë³´ê³  ?ˆ?Š” str?˜ char ê°’ì´ ? „ì°¨ì¸ ê²½ìš°
					curD = 0;	// ^?? ?œ„ë¥? ë°”ë¼ë³´ëŠ” ê²?, curD = 0
					curPoint = new Point(i, k);		// ì²˜ìŒ ?œ„ì¹? ?„¸?Œ…
				} else if (map[i][k] == '>') {
					curD = 1;	// >?? ?˜¤ë¥¸ìª½?„ ë°”ë¼ë³´ëŠ” ê²?, curD = 1
					curPoint = new Point(i, k);
				} else if (map[i][k] == '<') {
					curD = 3;	// <?? ?™¼ìª½ì„ ë°”ë¼ë³´ëŠ” ê²?, curD = 2
					curPoint = new Point(i, k);
				} else if (map[i][k] == 'v') {
					curD = 2;	// v?Š” ?•„?˜ë¥? ë°”ë¼ë³´ëŠ” ê²?, curD = 3
					curPoint = new Point(i, k);
				}
			}
		}
	}

	/**
	 * ë°°ì—´?„ ì¶œë ¥?•˜ê¸? ?œ„?•œ ë©”ì†Œ?“œ
	 */
	public static void print() {
		for (char[] arr : map) {	// ? „?˜•? ?¸ ë°°ì—´ ì¶œë ¥ë¬¸ì„ foreachë¡? ì§°ë‹¤
			for (char c : arr) {
				sb.append(c);	// ë°°ì—´?˜ ê°? ?•˜?‚˜ë¥? sb?— ì°¨ê·¼ì°¨ê·¼ ?Œ“ê³?
			}
			// ?•œ ?–‰?„ ?Œ“ê³? ?‚œ ?‹¤?Œ?—?Š” ì¤„ë°”ê¿? ?„£ê¸?!
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
