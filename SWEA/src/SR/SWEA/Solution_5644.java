package SR.SWEA;

import java.io.*;
import java.util.*;

public class Solution_5644 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, M, A, arrA[], arrB[], ans;	// 테스트케이스 수, 시간 수, 충전기 수, A의 이동 배열, B의 이동 배열, 정답
	static PriorityQueue<Integer> a, b;		// 우선순위 큐 => 현재 a와 b가 서있는 위치에서 사용 가능한 배터리충전기의 인덱스를 가지고 올 것
	static batteryCharger bc[];	// 배터리 정보를 담고 있는 bc배열 => bc 배열은 충전 가능한 크기가 높은 순으로 정렬할 것이다.
	static int[] dx = { 0, 0, 1, 0, -1 };	// 가만히 있을 때, 상, 우, 하, 좌
	static int[] dy = { 0, -1, 0, 1, 0 };

	/**
	 * 배터리 충전기 정보를 가지고 있는 클래스
	 */
	static class batteryCharger {
		Point loc;	// 좌표
		int c, p;	// 맨해튼 거리, 충전 가능 크기

		public batteryCharger(Point loc, int c, int p) {
			this.loc = loc;
			this.c = c;
			this.p = p;
		}
	}

	/**
	 * 좌표 값을 쉽게 처리하기 위해 만든 좌표 클래스
	 */
	static class Point {
		int x, y;	// x y 좌표

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * 충전기 선택 메소드
	 */
	public static void choice() {
		// 큐에 담겨있는 충전기를 뽑았을 때 가장 충전크기가 큰 충전기 일 것이다. 그렇게 만들어 놓았다.(bc 배열 정렬 -> 정렬된 배열에서의 큐 offer이었음)
		int firstSum = 0, secondSum = 0;	// a가 사용 가능한 충전기의 충전크기, b가 사용 가능한 충전기의 충전 크기
		int firstIndex = -1, secondIndex = -2;	// a가 사용가능한 충전기의 인덱스, b가 사용가능한 충전기의 인덱스
		if (!a.isEmpty()) {	// 큐 a가 비어있지 않다면 a의 위치에서 충전 가능한 충전기가 있다는 것
			firstIndex = a.poll();	// a가 사용 가능한 충전기의 인덱스를 가져온다.
			firstSum = bc[firstIndex].p;	// sum을 올려준다.
		}
		if (!b.isEmpty()) {	// 큐 b가 비어있지 않다면 b의 위치에서 충전 가능한 충전기가 있다는 것
			secondIndex = b.poll();	// b가 사용 가능한 충전기의 인덱스를 가져온다.
			secondSum = bc[secondIndex].p;	// sum을 올려준다.
		}
		
		// 위의 행위를 하고 났을 때 서로 같은 충전기를 사용하게 되는 경우!!! -> 다른 충전기 사용할 수 있는지 확인한다.
		if (firstIndex == secondIndex) {
			if (!a.isEmpty() && !b.isEmpty()) {	// a, b 둘 다 사용 가능한 또 다른 충전기가 있다면
				if (a.peek() < b.peek()) {	// a와 b에서 사용 가능한 또 다른 충전기의 인덱스를 비교하여 인덱스가 작은 값이 충전이 더 많이 되는 충전기이다.
					firstSum = bc[a.poll()].p;
				} else {
					secondSum = bc[b.poll()].p;
				}
			} else if (!a.isEmpty()) {	// a만 충전할 수 있는 또 다른 충전기가 있는 경우
				firstSum = bc[a.poll()].p;	// a를 다른 충전기에 연결한다.
			} else if (!b.isEmpty()) {	// b만 충전할 수 있는 또 다른 충전기가 있는 경우
				secondSum = bc[b.poll()].p;	// b를 다른 충전기에 연결한다.
			} else {	// 위의 경우가 모두 아니라면 서로 한 충전기를 결국 나눠써야한다.
				firstSum /= 2;
				secondSum /= 2;
			}
		}
		ans += firstSum;
		ans += secondSum;
	}

	public static void solve() {
		// bc 정렬 => 충전 크기가 큰 친구를 기준으로 오름차순
		// 첫번째 인덱스가 가장 좋은 충전기!!
		Arrays.sort(bc, (o1, o2) -> o2.p - o1.p);
		Point curA = new Point(1, 1);	// 처음 A 시작 위치
		Point curB = new Point(10, 10);	// 처음 B 시작 위치
		for (int i = 0; i <= M; i++) {	// arrA, arrB의 배열 크기를 M + 1로 설정해 놓았으며 처음에는 움직이지 않게 0을 넣어놓음
			curA = new Point(curA.x + dx[arrA[i]], curA.y + dy[arrA[i]]);
			curB = new Point(curB.x + dx[arrB[i]], curB.y + dy[arrB[i]]);
			a = new PriorityQueue<>();	// a가 사용할 수 있는 충전기 우선순위큐에 넣기
			b = new PriorityQueue<>();	// b가 사용할 수 있는 충전기 우선순위큐에 넣기
			for (int k = 0; k < A; k++) {
				if (isChargeDistance(curA, bc[k])) {
					a.offer(k);
				}
				if (isChargeDistance(curB, bc[k])) {
					b.offer(k);
				}
			}
			choice();	// ans를 올려준다.
		}
	}

	/**
	 * 맨해튼 거리 구하기
	 * 내가 서있는 위치에서 충전기까지 거리를 구할 것이다.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static int manhattan(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	/**
	 * 현재 위치에서 배터리 위치까지의 맨해튼 거리를 비교하여 충전기 거리가 닿는 곳인지 확인하는 메소드
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
	 * 초기값 세팅
	 * @throws IOException
	 */
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		arrA = new int[M + 1];	// 0초일 때를 고려하여 M + 1
		arrB = new int[M + 1];
		ans = 0;
		bc = new batteryCharger[A];	// 배터리 충전기 갯수만큼의 배열
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {	// 0인덱스는 움직이지 않는 0초일 때
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {	// 0인덱스는 움직이지 않는 0초일 때
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
