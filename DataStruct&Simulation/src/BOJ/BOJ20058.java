package BOJ;

import java.util.*;
import java.io.*;

public class BOJ20058 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, Q, arr[][], size, lList[], total = 0, maxCnt = 0;
	static boolean visited[][];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	/**
	 * 배열 내에 있는지 확인하는 메소드
	 * @param nx
	 * @param ny
	 * @return
	 */
	static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < size && ny < size;
	}
	
	/**
	 * 녹이기 메소드
	 */
	static int[][] melting() {
		int temp[][] = new int[size][size];
		for(int i = 0; i < size; i++) {
			temp[i] = arr[i].clone();
		}
		for(int i = 0; i < size; i++) {
			for(int k = 0; k < size; k++) {
				if(arr[i][k] == 0) continue;
				int cnt = 0;
				for(int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = k + dy[d];
					if(isIn(nx, ny) && arr[nx][ny] > 0) {
						cnt++;
					}
				}
				if(cnt < 3) temp[i][k]--;
			}
		}
		return temp;
	}
	
	/**
	 * 남은 얼음 합 구하기 + 남은 얼음 중 가장 큰 덩어리가 차지하는 칸 수 구하기
	 * BFS 메소드
	 */
	static void bfs(Point start) {
		int cnt = 0;
		Queue<Point> q = new ArrayDeque<>();
		q.offer(start);
		visited[start.x][start.y] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			total += arr[p.x][p.y];
			cnt++;
			for(int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if(!isIn(nx, ny)) continue;
				if(arr[nx][ny] == 0 || visited[nx][ny]) continue;
				q.offer(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
		maxCnt = Integer.max(cnt, maxCnt);
	}
	
	/**
	 * 배열 시계 방향으로 돌리는 메소드
	 * 자를 배열의 시작 좌표를 매개변수를 받음
	 * L = 자를 배열의 크기 -> 2^lList[i]의 값
	 * temp = 만들어질 새로운 배열
	 * @param p
	 * @param L
	 * @param temp
	 */
	static void turning(Point p, int L, int [][]temp) {
		for (int i = 0; i < L; i++) {
	        for (int j = 0; j < L; j++) {
	            temp[p.x + j][p.y + L - i - 1] = arr[p.x + i][p.y + j];
	        }
	    }
	}
	
	/**
	 * 필요 수만큼 배열을 돌릴 메소드
	 * @param L
	 * @return
	 */
	static int[][] divide(int L) {
	    int[][] temp = new int[size][size];
	    L = (int) Math.pow(2, L);
	    for (int i = 0; i < size; i += L) {
	        for (int k = 0; k < size; k += L) {
	            turning(new Point(i, k), L, temp);
	        }
	    }
	    return temp;
	}
	
	static void solve() {
		for(int i = 0; i < Q; i++) {
			arr = divide(lList[i]);	// 시계방향으로 배열 돌리고 난 후
			arr = melting();		// 돌린 후 얼음 녹이기
		}
		for(int i = 0; i < size; i++) {
			for(int k = 0; k < size; k++) {
				if(arr[i][k] != 0 && !visited[i][k]) {
					bfs(new Point(i, k));
				}
			}
		}
		sb.append(total).append("\n").append(maxCnt).append("\n");
		System.out.println(sb.toString());
	}
	
	/**
	 * 초기화 메소드
	 * @throws IOException
	 */
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		size = (int) Math.pow(2, N);
		arr = new int[size][size];
		lList = new int[Q];
		visited = new boolean[size][size];
		for(int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < size; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < Q; i++) {
			lList[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		solve();
	}
}
