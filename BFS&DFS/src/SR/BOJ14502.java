package SR;

import java.util.*;
import java.io.*;

public class BOJ14502 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, originArr[][], copyArr[][], max = Integer.MIN_VALUE;
	static int []dx = {-1, 0 , 1, 0};
	static int []dy = {0, 1, 0, -1};

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	/**
	 * nx, ny가 우리 공간 안에 있는지 확인하는 함수
	 * @param nx
	 * @param ny
	 * @return
	 */
	public static boolean isIn(int nx, int ny) {
		if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
			return false;
		}
		return true;
	}
	
	/**
	 * copyArr이 모두 완료되고 난 후 안전구역 찾기
	 */
	public static void maxSafeZone() {
		int safeZone = 0;
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < M; k++) {
				if(copyArr[i][k] == 0) safeZone++;
			}
		}
		max = Integer.max(max, safeZone);
	}

	/**
	 * 벽 3개를 다 치고 난 후 바이러스를 퍼트리기 시작했을 때의 경우
	 * BFS로 옆으로 퍼지는 양상
	 */
	public static void BFS() {
		Queue<Point> q = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < M; k++) {
				if(originArr[i][k] == 2) q.offer(new Point(i, k));
			}
		}
		
		copyArr = new int[N][M];
		for (int i = 0; i < N; i++) {
			copyArr[i] = originArr[i].clone();	// 얕은 복사.
			// 깊은 복사로 그냥 받아버리면 copyArr을 변경할 시에 originArr도 변경되니
			// 얕은 복사를 위해 clone함수 사용.
			// originArr은 바꾸지 않을 거니까 괜찮아!
        }
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if(isIn(nx, ny) && copyArr[nx][ny] == 0) {
					q.offer(new Point(nx, ny));
					copyArr[nx][ny] = 2;
				}
			}
		}
		
		maxSafeZone();
	}

	/**
	 * 벽 3개를 치는 모든 경우의 수
	 * @param wallCnt
	 */
	public static void makeWallDFS(int wallCnt) {	
		if (wallCnt == 3) {
			BFS();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (originArr[i][j] == 0) {
					originArr[i][j] = 1;
					makeWallDFS(wallCnt + 1);
					originArr[i][j] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		originArr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {
				originArr[i][k] = Integer.parseInt(st.nextToken());
			}
		}

		makeWallDFS(0);
		System.out.println(max);
	}
}
