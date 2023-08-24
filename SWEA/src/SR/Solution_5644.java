package SR;

import java.io.*;
import java.util.*;

public class Solution_5644 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, M, A, arrA[], arrB[], ans;	// ??€?ΈμΌ??΄?€ ?, ?κ°? ?, μΆ©μ κΈ? ?, A? ?΄? λ°°μ΄, B? ?΄? λ°°μ΄, ? ?΅
	static PriorityQueue<Integer> a, b;		// ?°? ?? ? => ??¬ a?? bκ°? ??? ?μΉμ? ?¬?© κ°??₯? λ°°ν°λ¦¬μΆ©? κΈ°μ ?Έ?±?€λ₯? κ°?μ§?κ³? ?¬ κ²?
	static batteryCharger bc[];	// λ°°ν°λ¦? ? λ³΄λ?? ?΄κ³? ?? bcλ°°μ΄ => bc λ°°μ΄?? μΆ©μ  κ°??₯? ?¬κΈ°κ? ??? ??Όλ‘? ? ? ¬?  κ²μ΄?€.
	static int[] dx = { 0, 0, 1, 0, -1 };	// κ°?λ§ν ?? ?, ?, ?°, ?, μ’?
	static int[] dy = { 0, -1, 0, 1, 0 };

	/**
	 * λ°°ν°λ¦? μΆ©μ κΈ? ? λ³΄λ?? κ°?μ§?κ³? ?? ?΄??€
	 */
	static class batteryCharger {
		Point loc;	// μ’ν
		int c, p;	// λ§¨ν΄?Ό κ±°λ¦¬, μΆ©μ  κ°??₯ ?¬κΈ?

		public batteryCharger(Point loc, int c, int p) {
			this.loc = loc;
			this.c = c;
			this.p = p;
		}
	}

	/**
	 * μ’ν κ°μ ?½κ²? μ²λ¦¬?κΈ? ??΄ λ§λ  μ’ν ?΄??€
	 */
	static class Point {
		int x, y;	// x y μ’ν

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * μΆ©μ κΈ? ? ? λ©μ?
	 */
	public static void choice() {
		// ?? ?΄κ²¨μ? μΆ©μ κΈ°λ?? λ½μ? ? κ°??₯ μΆ©μ ?¬κΈ°κ? ?° μΆ©μ κΈ? ?Ό κ²μ΄?€. κ·Έλ κ²? λ§λ€?΄ ???€.(bc λ°°μ΄ ? ? ¬ -> ? ? ¬? λ°°μ΄??? ? offer?΄??)
		int firstSum = 0, secondSum = 0;	// aκ°? ?¬?© κ°??₯? μΆ©μ κΈ°μ μΆ©μ ?¬κΈ?, bκ°? ?¬?© κ°??₯? μΆ©μ κΈ°μ μΆ©μ  ?¬κΈ?
		int firstIndex = -1, secondIndex = -2;	// aκ°? ?¬?©κ°??₯? μΆ©μ κΈ°μ ?Έ?±?€, bκ°? ?¬?©κ°??₯? μΆ©μ κΈ°μ ?Έ?±?€
		if (!a.isEmpty()) {	// ? aκ°? λΉμ΄?μ§? ??€λ©? a? ?μΉμ? μΆ©μ  κ°??₯? μΆ©μ κΈ°κ? ??€? κ²?
			firstIndex = a.poll();	// aκ°? ?¬?© κ°??₯? μΆ©μ κΈ°μ ?Έ?±?€λ₯? κ°?? Έ?¨?€.
			firstSum = bc[firstIndex].p;	// sum? ?¬? €μ€??€.
		}
		if (!b.isEmpty()) {	// ? bκ°? λΉμ΄?μ§? ??€λ©? b? ?μΉμ? μΆ©μ  κ°??₯? μΆ©μ κΈ°κ? ??€? κ²?
			secondIndex = b.poll();	// bκ°? ?¬?© κ°??₯? μΆ©μ κΈ°μ ?Έ?±?€λ₯? κ°?? Έ?¨?€.
			secondSum = bc[secondIndex].p;	// sum? ?¬? €μ€??€.
		}
		
		// ?? ??λ₯? ?κ³? ?¬? ? ?λ‘? κ°μ? μΆ©μ κΈ°λ?? ?¬?©?κ²? ?? κ²½μ°!!! -> ?€λ₯? μΆ©μ κΈ? ?¬?©?  ? ??μ§? ??Έ??€.
		if (firstIndex == secondIndex) {
			if (!a.isEmpty() && !b.isEmpty()) {	// a, b ? ?€ ?¬?© κ°??₯? ? ?€λ₯? μΆ©μ κΈ°κ? ??€λ©?
				if (a.peek() < b.peek()) {	// a?? b?? ?¬?© κ°??₯? ? ?€λ₯? μΆ©μ κΈ°μ ?Έ?±?€λ₯? λΉκ΅??¬ ?Έ?±?€κ°? ??? κ°μ΄ μΆ©μ ?΄ ? λ§μ΄ ?? μΆ©μ κΈ°μ΄?€.
					firstSum = bc[a.poll()].p;
				} else {
					secondSum = bc[b.poll()].p;
				}
			} else if (!a.isEmpty()) {	// aλ§? μΆ©μ ?  ? ?? ? ?€λ₯? μΆ©μ κΈ°κ? ?? κ²½μ°
				firstSum = bc[a.poll()].p;	// aλ₯? ?€λ₯? μΆ©μ κΈ°μ ?°κ²°ν?€.
			} else if (!b.isEmpty()) {	// bλ§? μΆ©μ ?  ? ?? ? ?€λ₯? μΆ©μ κΈ°κ? ?? κ²½μ°
				secondSum = bc[b.poll()].p;	// bλ₯? ?€λ₯? μΆ©μ κΈ°μ ?°κ²°ν?€.
			} else {	// ?? κ²½μ°κ°? λͺ¨λ ???Όλ©? ?λ‘? ? μΆ©μ κΈ°λ?? κ²°κ΅­ ?? ?¨?Ό??€.
				firstSum /= 2;
				secondSum /= 2;
			}
		}
		ans += firstSum;
		ans += secondSum;
	}

	public static void solve() {
		// bc ? ? ¬ => μΆ©μ  ?¬κΈ°κ? ?° μΉκ΅¬λ₯? κΈ°μ??Όλ‘? ?€λ¦μ°¨?
		// μ²«λ²μ§? ?Έ?±?€κ°? κ°??₯ μ’μ? μΆ©μ κΈ?!!
		Arrays.sort(bc, (o1, o2) -> o2.p - o1.p);
		Point curA = new Point(1, 1);	// μ²μ A ?? ?μΉ?
		Point curB = new Point(10, 10);	// μ²μ B ?? ?μΉ?
		for (int i = 0; i <= M; i++) {	// arrA, arrB? λ°°μ΄ ?¬κΈ°λ?? M + 1λ‘? ?€? ?΄ ???Όλ©? μ²μ?? ??μ§μ΄μ§? ?κ²? 0? ?£?΄??
			curA = new Point(curA.x + dx[arrA[i]], curA.y + dy[arrA[i]]);
			curB = new Point(curB.x + dx[arrB[i]], curB.y + dy[arrB[i]]);
			a = new PriorityQueue<>();	// aκ°? ?¬?©?  ? ?? μΆ©μ κΈ? ?°? ???? ?£κΈ?
			b = new PriorityQueue<>();	// bκ°? ?¬?©?  ? ?? μΆ©μ κΈ? ?°? ???? ?£κΈ?
			for (int k = 0; k < A; k++) {
				if (isChargeDistance(curA, bc[k])) {
					a.offer(k);
				}
				if (isChargeDistance(curB, bc[k])) {
					b.offer(k);
				}
			}
			choice();	// ansλ₯? ?¬? €μ€??€.
		}
	}

	/**
	 * λ§¨ν΄?Ό κ±°λ¦¬ κ΅¬νκΈ?
	 * ?΄κ°? ??? ?μΉμ? μΆ©μ κΈ°κΉμ§? κ±°λ¦¬λ₯? κ΅¬ν  κ²μ΄?€.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static int manhattan(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	/**
	 * ??¬ ?μΉμ? λ°°ν°λ¦? ?μΉκΉμ§?? λ§¨ν΄?Ό κ±°λ¦¬λ₯? λΉκ΅??¬ μΆ©μ κΈ? κ±°λ¦¬κ°? ?Ώ? κ³³μΈμ§? ??Έ?? λ©μ?
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
	 * μ΄κΈ°κ°? ?Έ?
	 * @throws IOException
	 */
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		arrA = new int[M + 1];	// 0μ΄μΌ ?λ₯? κ³ λ €??¬ M + 1
		arrB = new int[M + 1];
		ans = 0;
		bc = new batteryCharger[A];	// λ°°ν°λ¦? μΆ©μ κΈ? κ°??λ§νΌ? λ°°μ΄
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {	// 0?Έ?±?€? ??μ§μ΄μ§? ?? 0μ΄μΌ ?
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {	// 0?Έ?±?€? ??μ§μ΄μ§? ?? 0μ΄μΌ ?
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
