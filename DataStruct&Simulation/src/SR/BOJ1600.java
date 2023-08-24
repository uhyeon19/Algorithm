package SR;

import java.io.*;
import java.util.*;

public class BOJ1600 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int K, W, H, arr[][], ans = 0;
	static int[] dx = { -1, 0, 1, 0 };	// 인접 사방으로 움직이는 경우
	static int[] dy = { 0, 1, 0, -1 };
	static int[] ddx = { -2, -2, -1, -1, 1, 1, 2, 2 };	// 말 처럼 움직이는 경우
	static int[] ddy = { -1, 1, -2, 2, -2, 2, -1, 1 };
	static boolean[][][] visited;	// 말처럼 움직이는 경우를 하려는데, 원숭이처럼 움직여서 온 경우와 겹쳐서 못 가는 경우가 생기지 않게 3차원 배열

	/**
	 * bfs에서 큐에 담을 좌표와 말처럼 얼마나 움직일 수 있는지 담을 클래스
	 */
	public static class Point {
		int x, y, k;	// (x, y) 좌표, 말처럼 움직일 수 있는 횟수

		public Point(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}

	/**
	 * 배열 내부에 있는지 확인하는 메소드
	 * @param nx
	 * @param ny
	 * @return
	 */
	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < H && ny < W;
	}

	/**
	 * bfs 메소드
	 * @param x
	 * @param y
	 * @return
	 */
	public static int bfs(int x, int y) {	// 처음에는 시작점 0, 0이 매개변수
		Queue<Point> q = new LinkedList<>();	// 갈 수 있는 곳을 담을 큐
		q.offer(new Point(x, y, K));		// 처음 위치 담기
		visited[x][y][K] = true;	// visited 배열 초기화
		int cnt = 0;
		
		while (!q.isEmpty()) {
			int size = q.size();
			while(--size >= 0) {
				Point p = q.poll();		// 갈 수 있는 곳 뽑기
				if (p.x == H - 1 && p.y == W - 1)	// 갈 수 있는 곳으로 도달한 곳이 마지막 위치라면 움직인 횟수 뽑기
					return cnt;

				for (int i = 0; i < 4; i++) {	// 4방 탐색
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if (isIn(nx, ny) && !visited[nx][ny][p.k] && arr[nx][ny] == 0) {	// 다음 위치가 갈 수 있는 곳인지 확인
						visited[nx][ny][p.k] = true;	// 현재 말처럼 움직일 수 있는 수(스킬로 간 것이 아니니까) 3차원 공간 true 처리
						q.offer(new Point(nx, ny, p.k));
					}
				}

				// 만약 말처럼 움직일 수 있는 경우가 아직 남아있다면
				if (p.k > 0) {
					for (int i = 0; i < 8; i++) {	// 말처럼 움직일 수 있는 곳 탐색
						int nx = p.x + ddx[i];
						int ny = p.y + ddy[i];
						if (isIn(nx, ny) && !visited[nx][ny][p.k - 1] && arr[nx][ny] == 0) {	// 갈 수 있다면
							visited[nx][ny][p.k - 1] = true;	// 말처럼 움직일 수 있는 수 하나 줄여준(스킬 사용했으니까) 3차원 공간 true 처리
							q.offer(new Point(nx, ny, p.k - 1));	// 스킬 수를 하나 줄인다.
						}
					}
				}
			}
			cnt++;
		}
		return -1;
	}

	public static void init() throws IOException {
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		visited = new boolean[H][W][K + 1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < W; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		bw.write(bfs(0, 0) + "\n");
		bw.flush();
		bw.close();
	}
}
