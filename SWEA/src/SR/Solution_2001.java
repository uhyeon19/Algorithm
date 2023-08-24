package SR;

import java.io.*;
import java.util.*;

public class Solution_2001 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, N, M, ans, arr[][];

	public static void init() throws IOException {	// ?…? ¥ ê°? ë°›ì•„?„œ ?„¸?Œ…?•˜ê¸?!
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];
		ans = Integer.MIN_VALUE;	// ? •?‹µ ê°’ì? ê°??¥ ?° ê°’ì„ ?…? ¥?•  ê²ƒì´?‹ˆ ì´ˆê¸°ê°’ì? ê°??¥ ?‘ê²? ì¤˜ì„œ ë¹„êµ ?‹œ ë¬´ì¡°ê±? ë°”ë?Œê²Œ!

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = 1;
			while (st.hasMoreTokens()) {
				arr[i + 1][cnt++] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void makeAddArr() {	// 2ì°¨ì› ë°°ì—´ ?ˆ„? ?•© êµ¬í•˜ê¸?
		for(int i = 1; i <= N; i++) {
			for(int k = 1; k <= N; k++) {
				arr[i][k] = arr[i][k - 1] + arr[i - 1][k] - arr[i - 1][k - 1] + arr[i][k];
				// ?ˆ„? ?•© ë°°ì—´
			}
		}
	}
	
	public static void solve() {
		int x1 = 1, y1 = 1;	// ì²˜ìŒ ?‹œ?‘ êµ¬ê°„ (1, 1) ~ (M, M)
		int x2 = M, y2 = M;
		for(int i = 0; i <= N - M; i++) {	// N - M ë§Œí¼ ?´?™?•  ?ˆ˜ ?ˆ?Œ
			for(int j = 0; j <= N - M; j++) {
				int sum = arr[x2][y2] - arr[x1 - 1][y2] - arr[x2][y1 - 1] + arr[x1 - 1][y1 - 1];
				// êµ¬ê°„ ?ˆ„? ?•© êµ¬í•˜ê¸?
				ans = Integer.max(ans, sum);	// êµ¬ê°„ ?ˆ„? ?•©ê³? ansë¥? ë¹„êµ?•˜?—¬ ?” ?° ê°’ì„ ?‹´?•„ ansë¥? ë§Œë“ ?‹¤
				y1++;	// ?‹¤?Œ êµ¬ê°„
				y2++;	// ?‹¤?Œ êµ¬ê°„
			}
			x1++;	// yë¥? ?‚¤?›Œ N?— ë§ë‹¿?•˜?œ¼?‹ˆ xë¥? ?•œ ì¹? ?‚¤?›Œ ?•„?˜ìª½ì„ ?™•?¸?•œ?‹¤.
			x2++;
			y1 = 1;	// y1?Š” ?‹¤?‹œ 1ë¡? ì´ˆê¸°?™”
			y2 = M;	// y2?Š” ?‹¤?‹œ M?œ¼ë¡? ì´ˆê¸°?™”
			// ex)
			// 1. (1, 1) ~ (2, 2)
			// 2. (1, 2) ~ (2, 3)
			// 		...
			// 3. (2, 1) ~ (3, 2)
			// 4. (2, 2) ~ (3, 3)
			//		...
		}
		sb.append(ans).append('\n');
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			sb.append("#").append(i + 1 + " ");
			init();
			makeAddArr();
			solve();
		}
		System.out.print(sb.toString());
	}
}
