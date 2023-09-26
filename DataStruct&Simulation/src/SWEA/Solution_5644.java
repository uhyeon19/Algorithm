package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5644 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, M, A, arrA[], arrB[], ans;	// ?��?��?���??��?�� ?��, ?���? ?��, 충전�? ?��, A?�� ?��?�� 배열, B?�� ?��?�� 배열, ?��?��
	static PriorityQueue<Integer> a, b;		// ?��?��?��?�� ?�� => ?��?�� a?? b�? ?��?��?�� ?��치에?�� ?��?�� �??��?�� 배터리충?��기의 ?��?��?���? �?�?�? ?�� �?
	static batteryCharger bc[];	// 배터�? ?��보�?? ?���? ?��?�� bc배열 => bc 배열?? 충전 �??��?�� ?��기�? ?��?? ?��?���? ?��?��?�� 것이?��.
	static int[] dx = { 0, 0, 1, 0, -1 };	// �?만히 ?��?�� ?��, ?��, ?��, ?��, �?
	static int[] dy = { 0, -1, 0, 1, 0 };

	/**
	 * 배터�? 충전�? ?��보�?? �?�?�? ?��?�� ?��?��?��
	 */
	static class batteryCharger {
		Point loc;	// 좌표
		int c, p;	// 맨해?�� 거리, 충전 �??�� ?���?

		public batteryCharger(Point loc, int c, int p) {
			this.loc = loc;
			this.c = c;
			this.p = p;
		}
	}

	/**
	 * 좌표 값을 ?���? 처리?���? ?��?�� 만든 좌표 ?��?��?��
	 */
	static class Point {
		int x, y;	// x y 좌표

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * 충전�? ?��?�� 메소?��
	 */
	public static void choice() {
		// ?��?�� ?��겨있?�� 충전기�?? 뽑았?�� ?�� �??�� 충전?��기�? ?�� 충전�? ?�� 것이?��. 그렇�? 만들?�� ?��?��?��.(bc 배열 ?��?�� -> ?��?��?�� 배열?��?��?�� ?�� offer?��?��?��)
		int firstSum = 0, secondSum = 0;	// a�? ?��?�� �??��?�� 충전기의 충전?���?, b�? ?��?�� �??��?�� 충전기의 충전 ?���?
		int firstIndex = -1, secondIndex = -2;	// a�? ?��?���??��?�� 충전기의 ?��?��?��, b�? ?��?���??��?�� 충전기의 ?��?��?��
		if (!a.isEmpty()) {	// ?�� a�? 비어?���? ?��?���? a?�� ?��치에?�� 충전 �??��?�� 충전기�? ?��?��?�� �?
			firstIndex = a.poll();	// a�? ?��?�� �??��?�� 충전기의 ?��?��?���? �??��?��?��.
			firstSum = bc[firstIndex].p;	// sum?�� ?��?���??��.
		}
		if (!b.isEmpty()) {	// ?�� b�? 비어?���? ?��?���? b?�� ?��치에?�� 충전 �??��?�� 충전기�? ?��?��?�� �?
			secondIndex = b.poll();	// b�? ?��?�� �??��?�� 충전기의 ?��?��?���? �??��?��?��.
			secondSum = bc[secondIndex].p;	// sum?�� ?��?���??��.
		}
		
		// ?��?�� ?��?���? ?���? ?��?�� ?�� ?���? 같�? 충전기�?? ?��?��?���? ?��?�� 경우!!! -> ?���? 충전�? ?��?��?�� ?�� ?��?���? ?��?��?��?��.
		if (firstIndex == secondIndex) {
			if (!a.isEmpty() && !b.isEmpty()) {	// a, b ?�� ?�� ?��?�� �??��?�� ?�� ?���? 충전기�? ?��?���?
				if (a.peek() < b.peek()) {	// a?? b?��?�� ?��?�� �??��?�� ?�� ?���? 충전기의 ?��?��?���? 비교?��?�� ?��?��?���? ?��?? 값이 충전?�� ?�� 많이 ?��?�� 충전기이?��.
					firstSum = bc[a.poll()].p;
				} else {
					secondSum = bc[b.poll()].p;
				}
			} else if (!a.isEmpty()) {	// a�? 충전?�� ?�� ?��?�� ?�� ?���? 충전기�? ?��?�� 경우
				firstSum = bc[a.poll()].p;	// a�? ?���? 충전기에 ?��결한?��.
			} else if (!b.isEmpty()) {	// b�? 충전?�� ?�� ?��?�� ?�� ?���? 충전기�? ?��?�� 경우
				secondSum = bc[b.poll()].p;	// b�? ?���? 충전기에 ?��결한?��.
			} else {	// ?��?�� 경우�? 모두 ?��?��?���? ?���? ?�� 충전기�?? 결국 ?��?��?��?��?��?��.
				firstSum /= 2;
				secondSum /= 2;
			}
		}
		ans += firstSum;
		ans += secondSum;
	}

	public static void solve() {
		// bc ?��?�� => 충전 ?��기�? ?�� 친구�? 기�??���? ?��름차?��
		// 첫번�? ?��?��?���? �??�� 좋�? 충전�?!!
		Arrays.sort(bc, (o1, o2) -> o2.p - o1.p);
		Point curA = new Point(1, 1);	// 처음 A ?��?�� ?���?
		Point curB = new Point(10, 10);	// 처음 B ?��?�� ?���?
		for (int i = 0; i <= M; i++) {	// arrA, arrB?�� 배열 ?��기�?? M + 1�? ?��?��?�� ?��?��?���? 처음?��?�� ??직이�? ?���? 0?�� ?��?��?��?��
			curA = new Point(curA.x + dx[arrA[i]], curA.y + dy[arrA[i]]);
			curB = new Point(curB.x + dx[arrB[i]], curB.y + dy[arrB[i]]);
			a = new PriorityQueue<>();	// a�? ?��?��?�� ?�� ?��?�� 충전�? ?��?��?��?��?��?�� ?���?
			b = new PriorityQueue<>();	// b�? ?��?��?�� ?�� ?��?�� 충전�? ?��?��?��?��?��?�� ?���?
			for (int k = 0; k < A; k++) {
				if (isChargeDistance(curA, bc[k])) {
					a.offer(k);
				}
				if (isChargeDistance(curB, bc[k])) {
					b.offer(k);
				}
			}
			choice();	// ans�? ?��?���??��.
		}
	}

	/**
	 * 맨해?�� 거리 구하�?
	 * ?���? ?��?��?�� ?��치에?�� 충전기까�? 거리�? 구할 것이?��.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static int manhattan(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	/**
	 * ?��?�� ?��치에?�� 배터�? ?��치까�??�� 맨해?�� 거리�? 비교?��?�� 충전�? 거리�? ?��?�� 곳인�? ?��?��?��?�� 메소?��
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
	 * 초기�? ?��?��
	 * @throws IOException
	 */
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		arrA = new int[M + 1];	// 0초일 ?���? 고려?��?�� M + 1
		arrB = new int[M + 1];
		ans = 0;
		bc = new batteryCharger[A];	// 배터�? 충전�? �??��만큼?�� 배열
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {	// 0?��?��?��?�� ??직이�? ?��?�� 0초일 ?��
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {	// 0?��?��?��?�� ??직이�? ?��?�� 0초일 ?��
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
