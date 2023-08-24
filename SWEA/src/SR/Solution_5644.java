package SR;

import java.io.*;
import java.util.*;

public class Solution_5644 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, M, A, arrA[], arrB[], ans;	// ?…Œ?Š¤?Š¸ì¼??´?Š¤ ?ˆ˜, ?‹œê°? ?ˆ˜, ì¶©ì „ê¸? ?ˆ˜, A?˜ ?´?™ ë°°ì—´, B?˜ ?´?™ ë°°ì—´, ? •?‹µ
	static PriorityQueue<Integer> a, b;		// ?š°?„ ?ˆœ?œ„ ? => ?˜„?¬ a?? bê°? ?„œ?ˆ?Š” ?œ„ì¹˜ì—?„œ ?‚¬?š© ê°??Š¥?•œ ë°°í„°ë¦¬ì¶©? „ê¸°ì˜ ?¸?±?Š¤ë¥? ê°?ì§?ê³? ?˜¬ ê²?
	static batteryCharger bc[];	// ë°°í„°ë¦? ? •ë³´ë?? ?‹´ê³? ?ˆ?Š” bcë°°ì—´ => bc ë°°ì—´?? ì¶©ì „ ê°??Š¥?•œ ?¬ê¸°ê? ?†’?? ?ˆœ?œ¼ë¡? ? •? ¬?•  ê²ƒì´?‹¤.
	static int[] dx = { 0, 0, 1, 0, -1 };	// ê°?ë§Œíˆ ?ˆ?„ ?•Œ, ?ƒ, ?š°, ?•˜, ì¢?
	static int[] dy = { 0, -1, 0, 1, 0 };

	/**
	 * ë°°í„°ë¦? ì¶©ì „ê¸? ? •ë³´ë?? ê°?ì§?ê³? ?ˆ?Š” ?´?˜?Š¤
	 */
	static class batteryCharger {
		Point loc;	// ì¢Œí‘œ
		int c, p;	// ë§¨í•´?Š¼ ê±°ë¦¬, ì¶©ì „ ê°??Š¥ ?¬ê¸?

		public batteryCharger(Point loc, int c, int p) {
			this.loc = loc;
			this.c = c;
			this.p = p;
		}
	}

	/**
	 * ì¢Œí‘œ ê°’ì„ ?‰½ê²? ì²˜ë¦¬?•˜ê¸? ?œ„?•´ ë§Œë“  ì¢Œí‘œ ?´?˜?Š¤
	 */
	static class Point {
		int x, y;	// x y ì¢Œí‘œ

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * ì¶©ì „ê¸? ?„ ?ƒ ë©”ì†Œ?“œ
	 */
	public static void choice() {
		// ??— ?‹´ê²¨ìˆ?Š” ì¶©ì „ê¸°ë?? ë½‘ì•˜?„ ?•Œ ê°??¥ ì¶©ì „?¬ê¸°ê? ?° ì¶©ì „ê¸? ?¼ ê²ƒì´?‹¤. ê·¸ë ‡ê²? ë§Œë“¤?–´ ?†“?•˜?‹¤.(bc ë°°ì—´ ? •? ¬ -> ? •? ¬?œ ë°°ì—´?—?„œ?˜ ? offer?´?—ˆ?Œ)
		int firstSum = 0, secondSum = 0;	// aê°? ?‚¬?š© ê°??Š¥?•œ ì¶©ì „ê¸°ì˜ ì¶©ì „?¬ê¸?, bê°? ?‚¬?š© ê°??Š¥?•œ ì¶©ì „ê¸°ì˜ ì¶©ì „ ?¬ê¸?
		int firstIndex = -1, secondIndex = -2;	// aê°? ?‚¬?š©ê°??Š¥?•œ ì¶©ì „ê¸°ì˜ ?¸?±?Š¤, bê°? ?‚¬?š©ê°??Š¥?•œ ì¶©ì „ê¸°ì˜ ?¸?±?Š¤
		if (!a.isEmpty()) {	// ? aê°? ë¹„ì–´?ˆì§? ?•Š?‹¤ë©? a?˜ ?œ„ì¹˜ì—?„œ ì¶©ì „ ê°??Š¥?•œ ì¶©ì „ê¸°ê? ?ˆ?‹¤?Š” ê²?
			firstIndex = a.poll();	// aê°? ?‚¬?š© ê°??Š¥?•œ ì¶©ì „ê¸°ì˜ ?¸?±?Š¤ë¥? ê°?? ¸?˜¨?‹¤.
			firstSum = bc[firstIndex].p;	// sum?„ ?˜¬? ¤ì¤??‹¤.
		}
		if (!b.isEmpty()) {	// ? bê°? ë¹„ì–´?ˆì§? ?•Š?‹¤ë©? b?˜ ?œ„ì¹˜ì—?„œ ì¶©ì „ ê°??Š¥?•œ ì¶©ì „ê¸°ê? ?ˆ?‹¤?Š” ê²?
			secondIndex = b.poll();	// bê°? ?‚¬?š© ê°??Š¥?•œ ì¶©ì „ê¸°ì˜ ?¸?±?Š¤ë¥? ê°?? ¸?˜¨?‹¤.
			secondSum = bc[secondIndex].p;	// sum?„ ?˜¬? ¤ì¤??‹¤.
		}
		
		// ?œ„?˜ ?–‰?œ„ë¥? ?•˜ê³? ?‚¬?„ ?•Œ ?„œë¡? ê°™ì? ì¶©ì „ê¸°ë?? ?‚¬?š©?•˜ê²? ?˜?Š” ê²½ìš°!!! -> ?‹¤ë¥? ì¶©ì „ê¸? ?‚¬?š©?•  ?ˆ˜ ?ˆ?Š”ì§? ?™•?¸?•œ?‹¤.
		if (firstIndex == secondIndex) {
			if (!a.isEmpty() && !b.isEmpty()) {	// a, b ?‘˜ ?‹¤ ?‚¬?š© ê°??Š¥?•œ ?˜ ?‹¤ë¥? ì¶©ì „ê¸°ê? ?ˆ?‹¤ë©?
				if (a.peek() < b.peek()) {	// a?? b?—?„œ ?‚¬?š© ê°??Š¥?•œ ?˜ ?‹¤ë¥? ì¶©ì „ê¸°ì˜ ?¸?±?Š¤ë¥? ë¹„êµ?•˜?—¬ ?¸?±?Š¤ê°? ?‘?? ê°’ì´ ì¶©ì „?´ ?” ë§ì´ ?˜?Š” ì¶©ì „ê¸°ì´?‹¤.
					firstSum = bc[a.poll()].p;
				} else {
					secondSum = bc[b.poll()].p;
				}
			} else if (!a.isEmpty()) {	// aë§? ì¶©ì „?•  ?ˆ˜ ?ˆ?Š” ?˜ ?‹¤ë¥? ì¶©ì „ê¸°ê? ?ˆ?Š” ê²½ìš°
				firstSum = bc[a.poll()].p;	// aë¥? ?‹¤ë¥? ì¶©ì „ê¸°ì— ?—°ê²°í•œ?‹¤.
			} else if (!b.isEmpty()) {	// bë§? ì¶©ì „?•  ?ˆ˜ ?ˆ?Š” ?˜ ?‹¤ë¥? ì¶©ì „ê¸°ê? ?ˆ?Š” ê²½ìš°
				secondSum = bc[b.poll()].p;	// bë¥? ?‹¤ë¥? ì¶©ì „ê¸°ì— ?—°ê²°í•œ?‹¤.
			} else {	// ?œ„?˜ ê²½ìš°ê°? ëª¨ë‘ ?•„?‹ˆ?¼ë©? ?„œë¡? ?•œ ì¶©ì „ê¸°ë?? ê²°êµ­ ?‚˜?ˆ ?¨?•¼?•œ?‹¤.
				firstSum /= 2;
				secondSum /= 2;
			}
		}
		ans += firstSum;
		ans += secondSum;
	}

	public static void solve() {
		// bc ? •? ¬ => ì¶©ì „ ?¬ê¸°ê? ?° ì¹œêµ¬ë¥? ê¸°ì??œ¼ë¡? ?˜¤ë¦„ì°¨?ˆœ
		// ì²«ë²ˆì§? ?¸?±?Š¤ê°? ê°??¥ ì¢‹ì? ì¶©ì „ê¸?!!
		Arrays.sort(bc, (o1, o2) -> o2.p - o1.p);
		Point curA = new Point(1, 1);	// ì²˜ìŒ A ?‹œ?‘ ?œ„ì¹?
		Point curB = new Point(10, 10);	// ì²˜ìŒ B ?‹œ?‘ ?œ„ì¹?
		for (int i = 0; i <= M; i++) {	// arrA, arrB?˜ ë°°ì—´ ?¬ê¸°ë?? M + 1ë¡? ?„¤? •?•´ ?†“?•˜?œ¼ë©? ì²˜ìŒ?—?Š” ??ì§ì´ì§? ?•Šê²? 0?„ ?„£?–´?†“?Œ
			curA = new Point(curA.x + dx[arrA[i]], curA.y + dy[arrA[i]]);
			curB = new Point(curB.x + dx[arrB[i]], curB.y + dy[arrB[i]]);
			a = new PriorityQueue<>();	// aê°? ?‚¬?š©?•  ?ˆ˜ ?ˆ?Š” ì¶©ì „ê¸? ?š°?„ ?ˆœ?œ„??— ?„£ê¸?
			b = new PriorityQueue<>();	// bê°? ?‚¬?š©?•  ?ˆ˜ ?ˆ?Š” ì¶©ì „ê¸? ?š°?„ ?ˆœ?œ„??— ?„£ê¸?
			for (int k = 0; k < A; k++) {
				if (isChargeDistance(curA, bc[k])) {
					a.offer(k);
				}
				if (isChargeDistance(curB, bc[k])) {
					b.offer(k);
				}
			}
			choice();	// ansë¥? ?˜¬? ¤ì¤??‹¤.
		}
	}

	/**
	 * ë§¨í•´?Š¼ ê±°ë¦¬ êµ¬í•˜ê¸?
	 * ?‚´ê°? ?„œ?ˆ?Š” ?œ„ì¹˜ì—?„œ ì¶©ì „ê¸°ê¹Œì§? ê±°ë¦¬ë¥? êµ¬í•  ê²ƒì´?‹¤.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static int manhattan(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	/**
	 * ?˜„?¬ ?œ„ì¹˜ì—?„œ ë°°í„°ë¦? ?œ„ì¹˜ê¹Œì§??˜ ë§¨í•´?Š¼ ê±°ë¦¬ë¥? ë¹„êµ?•˜?—¬ ì¶©ì „ê¸? ê±°ë¦¬ê°? ?‹¿?Š” ê³³ì¸ì§? ?™•?¸?•˜?Š” ë©”ì†Œ?“œ
	 * @param p1
	 * @param bc
	 * @return
	 */
	public static boolean isChargeDistance(Point p1, batteryCharger bc) {
		if (manhattan(p1, bc.loc) <= bc.c)
			return true;
		return false;
	}

	/**
	 * ì´ˆê¸°ê°? ?„¸?Œ…
	 * @throws IOException
	 */
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		arrA = new int[M + 1];	// 0ì´ˆì¼ ?•Œë¥? ê³ ë ¤?•˜?—¬ M + 1
		arrB = new int[M + 1];
		ans = 0;
		bc = new batteryCharger[A];	// ë°°í„°ë¦? ì¶©ì „ê¸? ê°??ˆ˜ë§Œí¼?˜ ë°°ì—´
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {	// 0?¸?±?Š¤?Š” ??ì§ì´ì§? ?•Š?Š” 0ì´ˆì¼ ?•Œ
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {	// 0?¸?±?Š¤?Š” ??ì§ì´ì§? ?•Š?Š” 0ì´ˆì¼ ?•Œ
			arrB[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			bc[i] = new batteryCharger(new Point(x, y), c, p);
		}
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solve();
			sb.append("#" + t + " " + ans + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
