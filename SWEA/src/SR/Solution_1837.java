package SR;

import java.io.*;
import java.util.*;

public class Solution_1837 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();	// μΆλ ₯κ°? ???₯ StringBuilder
	static int T, H, W, N, curD;	// ??€?ΈμΌ??΄?€ ?, H x W λ°°μ΄? λ§λ€ κ²?, λͺλ Ή?΄? ? N, ??¬ λ°λΌλ³΄κ³  ?? λ°©ν₯
	static int[] dx = { -1, 0, 1, 0 }; // ??°?μ’?
	static int[] dy = { 0, 1, 0, -1 };
	static char map[][];	// λ§? ??
	static Point curPoint;	// ??¬ ? μ°? ?μΉ?

	/**
	 * ? μ°? ?μΉ? μ’νλ₯? ???₯?κΈ? ?? Point ?΄??€
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
	 * λ°°μ΄ λ²μ λ°μΌλ‘? ?κ°??μ§? ??Έ?? λ©μ?
	 * @param nx
	 * @param ny
	 * @return
	 */
	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= H || ny >= W)	// λ°°μ΄? λ²μ ?¬?΄μ¦? λ°μΈ κ²½μ°
			return false;
		return true;
	}

	/**
	 * λͺλ Ή?΄κ°? U, R, D, L?Ό ? ? μ°? λ¨Έλ¦¬ ?λ¦¬κΈ° + ? μ°? ?΄??΄ κ°??₯??€λ©? ?΄?κΉμ? ??€? λ©μ?
	 */
	public static void move() {
		int x = curPoint.x, y = curPoint.y;	// ??¬ μ’ν
		int nx = x + dx[curD], ny = y + dy[curD];	// ?€? μ’ν
		
		if (curD == 0) map[x][y] = '^';		// ??¬ λ°λΌλ³΄κ³  ?? λ°©ν₯? ?°?Ό ? μ°¨λ?? ?λ¦°λ€.
		else if (curD == 1) map[x][y] = '>';
		else if (curD == 2) map[x][y] = 'v';
		else if (curD == 3) map[x][y] = '<';
		
		// ?€? ? μΉ? κ°? ?, κ°? ? ?? λ²μ?΄λ©? ?μ§??Ό ?
		if (isIn(nx, ny) && map[nx][ny] == '.') {
			map[nx][ny] = map[x][y];	// ?€? μΉΈμ ? μ°¨λ?? ?κ³?
			map[x][y] = '.';			// ??¬ μΉΈμ ?μ§?λ‘? λ°κΏμ€??€.
			curPoint = new Point(nx, ny);	// ??¬ ?μΉ? λ³?κ²?
		}
	}

	/**
	 * λͺλ ΄?΄κ°? S?Έ κ²½μ° λ°μ¬ λ©μ?
	 */
	public static void shoot() {
		int cnt = 1;	// λ°λΌλ³΄κ³  ?? λ°©ν₯? κΈ°μ??Όλ‘? κ³μ?΄ ??Όλ‘? ??κ°?κΈ? ??΄ ?€? 
		while (true) {
			int nx = curPoint.x + dx[curD] * cnt;	// ?μΉ?, ??μΉ?... μ­? ??Έ
			int ny = curPoint.y + dy[curD] * cnt++;
			if (!isIn(nx, ny) || map[nx][ny] == '#')	// λ²μλ₯? λ²μ΄?κ±°λ κ°μ² λ²½μ λ§λλ©? λ©μΆ€
				break;
			if (map[nx][ny] == '*') {	// λ²μλ₯? λ²μ΄?μ§? ?κ³? κ°μ² λ²½λ λ§λμ§? ??? ???? λ²½λ? λ¨Όμ? λ§λλ©?
				map[nx][ny] = '.';	// λ²½λ? λΆ??κ³? ?μ§?κ°? ?? κ·? μΉΈμ ?μ§?λ‘? λ§λ€κ³? λ©μΆ€
				break;
			}
		}
	}

	public static void run() throws IOException {
		N = Integer.parseInt(br.readLine());	// λͺλ Ή?΄? κ°??
		String str = br.readLine();	// λͺλ Ή?΄ ?? ₯ λ°μ
		for (int i = 0; i < N; i++) {	// λͺλ Ή?΄ ?ΈκΈ?
			if (str.charAt(i) == 'U') {	// ??¬ λͺλ Ή?΄κ°? λ¬΄μ?Έμ§? μ‘°κ±΄
				curD = 0;	// ?? λ³΄κ³  ?? ?? curD = 0
				move();		// ?΄?
			} else if (str.charAt(i) == 'R') {
				curD = 1;	// ?€λ₯Έμͺ½? λ³΄κ³  ?? ?? curD = 1
				move();		// ?΄?
			} else if (str.charAt(i) == 'D') {
				curD = 2;	// λ°μ λ³΄κ³  ?? ?? curD = 2
				move();		// ?΄?
			} else if (str.charAt(i) == 'L') {
				curD = 3;	// ?Όμͺ½μ λ³΄κ³  ?? ?? curD = 3
				move();		// ?΄?
			} else if (str.charAt(i) == 'S') {	// S?Ό ?? shoot()
				shoot();
			}
		}
	}

	/**
	 * μ΄κΈ° ?€?  λ©μ?
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
				if (map[i][k] == '^') {	// ??¬ λ³΄κ³  ?? str? char κ°μ΄ ? μ°¨μΈ κ²½μ°
					curD = 0;	// ^?? ?λ₯? λ°λΌλ³΄λ κ²?, curD = 0
					curPoint = new Point(i, k);		// μ²μ ?μΉ? ?Έ?
				} else if (map[i][k] == '>') {
					curD = 1;	// >?? ?€λ₯Έμͺ½? λ°λΌλ³΄λ κ²?, curD = 1
					curPoint = new Point(i, k);
				} else if (map[i][k] == '<') {
					curD = 3;	// <?? ?Όμͺ½μ λ°λΌλ³΄λ κ²?, curD = 2
					curPoint = new Point(i, k);
				} else if (map[i][k] == 'v') {
					curD = 2;	// v? ??λ₯? λ°λΌλ³΄λ κ²?, curD = 3
					curPoint = new Point(i, k);
				}
			}
		}
	}

	/**
	 * λ°°μ΄? μΆλ ₯?κΈ? ?? λ©μ?
	 */
	public static void print() {
		for (char[] arr : map) {	// ? ?? ?Έ λ°°μ΄ μΆλ ₯λ¬Έμ foreachλ‘? μ§°λ€
			for (char c : arr) {
				sb.append(c);	// λ°°μ΄? κ°? ??λ₯? sb? μ°¨κ·Όμ°¨κ·Ό ?κ³?
			}
			// ? ?? ?κ³? ? ?€??? μ€λ°κΏ? ?£κΈ?!
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
