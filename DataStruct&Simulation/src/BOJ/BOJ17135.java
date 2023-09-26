package BOJ;

import java.io.*;
import java.util.*;

public class BOJ17135 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	// 원본배열 arr, 복사 배열 tmpArr(궁수가 쏘고 잡으면 배열이 바뀌고, 적이 전진하면 또 배열이 바뀌기 때문)
	static int N, M, D, arr[][], tmpArr[][], ans = Integer.MIN_VALUE;
	static Point[] archer = new Point[3];	// 궁수는 총 3명
	// 조합에 사용할 isSelected, 궁수들이 쏘는 위치 kill, BFS 방문처리 visit
	static boolean isSelected[], kill[][], visit[][];
	static int[] dx = { 0, -1, 0 };	// 왼쪽, 전방, 오른쪽으로 활을 쏠 수 있다.
	// 삼방 중 한 방향만 쏠 수 있는 거리가 아닌 다양한 방향으로도 쏠 수 있다면, 왼쪽이 기준!
	static int[] dy = { -1, 0, 1 };	// 왼쪽을 제일 처음 인덱스로 둔다.

	/**
	 * 좌표 객체 만들기
	 * @author uhyeon
	 */
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * 다음 찾는 값이 안에 있는지 확인하는 메소드
	 * @param nx
	 * @param ny
	 * @return
	 */
	static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
			return false;
		}
		return true;
	}

	/**
	 * 적들이 전진한 후의 배열 만들기
	 */
	static void enemyForward() {
		// 궁수가 쏴서 바뀐 tmpArr을 한 칸씩 땡긴다.
		// 땡긴 배열을 저장할 resultArr
		int[][] resultArr = new int[N][M];
		for (int i = 0; i < N - 1; i++) {
			resultArr[i + 1] = tmpArr[i].clone();
		}
		tmpArr = resultArr;
	}

	/**
	 * 끝나는 조건분기 구하는 메소드
	 * @return
	 */
	static boolean isFin() {
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				if (tmpArr[i][k] == 1) {	// 적이 있는지 검사
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 궁수가 공격하는 적의 위치 찾는 BFS 메소드
	 * @param archer
	 */
	static void attackBFS(Point archer) {
		Queue<Point> q = new LinkedList<>();	// BFS 노드를 담을 큐
		int count = 1;	// 거리를 카운팅하는 count 변수
		visit = new boolean[N][M];	// 방문 배열 처리
		q.offer(archer);	// 처음 위치 담기
		boolean check = false;	// 적을 잡았는지 확인할 조건
		while (!q.isEmpty()) {
			int size = q.size();	// q의 사이즈(높이 찾기)
			while (--size >= 0) {
				Point p = q.poll();
				for (int d = 0; d < 3; d++) {
					int nx = p.x + dx[d];	// 다음 위치 가기
					int ny = p.y + dy[d];
					// 다음 위치에 있고, 방문하지 않은 곳이며, 거리가 아직 궁수에게 닿는 위치라면
					if (isIn(nx, ny) && !visit[nx][ny] && count <= D) {
						if (tmpArr[nx][ny] == 0) {	// 적이 없는 칸이면 다음 칸 찾기
							q.offer(new Point(nx, ny));
							visit[nx][ny] = true;
						} else if (tmpArr[nx][ny] == 1) {	// 적이 있는 칸이면 적을 잡았다고 처리
							kill[nx][ny] = true;
							check = true;
							break;
						}
					}
				}
				if (check) break;
			}
			if (check) break;
			count++;	// 거리 늘리기
		}
	}

	/**
	 * 궁수의 위치 정하기
	 * 조합을 사용한다. nC3 구하기
	 * @param cnt
	 * @param index
	 */
	static void whereArcher_Comb(int cnt, int index) {
		if (cnt == 3) {
			// 현재 조합으로 돌려볼 tmpArr 만들기
			tmpArr = new int[N][M];
			for (int i = 0; i < N; i++) {
				tmpArr[i] = arr[i].clone();
			}

			// 현재 조합에서의 잡을 수 있는 총 적의 수
			int count = 0;
			do {
				kill = new boolean[N][M];
				// 궁수 3명이 적을 쏘고 나면 kill로 몇 명을 잡았는지 확인 가능
				for (int i = 0; i < 3; i++) {
					attackBFS(archer[i]);
				}

				// 몇 명 잡았는지 탐색
				for (int i = 0; i < N; i++) {
					for (int k = 0; k < M; k++) {
						if (kill[i][k]) {
							count++;
							tmpArr[i][k] = 0;
						}
					}
				}
				// 적이 한 칸 다가온다.
				enemyForward();
			} while (!isFin());	// 끝났는지 조건 검사
			ans = Integer.max(count, ans);	// 위의 조합에서 잡은 적이 이전 적보다 많으면 바꿈
			return;
		}
		for (int i = index; i < M; i++) {	// 조합 코드
			if (!isSelected[i]) {
				isSelected[i] = true;
				archer[cnt] = new Point(N, i);	// 궁수의 위치는 N, i이다.
				whereArcher_Comb(cnt + 1, index + 1);
				isSelected[i] = false;
			}
		}
	}

	/**
	 * 초기 상태 세팅
	 * @throws IOException
	 */
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		isSelected = new boolean[M];
	}

	public static void main(String[] args) throws IOException {
		init();
		whereArcher_Comb(0, 0);
		System.out.println(ans);
	}
}
