package BOJ;

import java.io.*;
import java.util.*;

/*
 * BFS�� ����ؾ��Ѵ�. -> �ٵ� �迭�� ũ�Ⱑ �������� �� ũ��. 1500 X 1500 => �Ϲ������� ��� �迭�� ���ؼ� ��� ���� ���� ����.
 * ���� �Դ� �� visited üũ�� �ϱ� �ϳ� �׷��� �� ���� ���̴�. => �׷� ��� ������ ��ġ(���� ���� ��ġ)�� ���� bfs Ž�� ��ġ�� �дٸ�?(Ž��ť q, ���� ���� ��ġ nextQ -> �ٲ㳢�� ��)
 * ���� ��ġ ���� ť�� �����Ѵ�. -> ���� �´�� �ִ� ������ ��� �������Ѵ�. �̸� ���ֱ� ���� water ť
 */
public class BOJ3197 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int X, Y, ansDay;
	static char arr[][];
	static boolean visited[][], isMeet;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Point swan[] = new Point[2];
	static Queue<Point> q, water;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static public void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		arr = new char[X][Y];
		water = new ArrayDeque<>();
		q = new ArrayDeque<>();
		visited = new boolean[X][Y];
		int swanCnt = 0;
		for (int i = 0; i < X; i++) {
			String str = br.readLine();
			for (int k = 0; k < Y; k++) {
				arr[i][k] = str.charAt(k);
				if(arr[i][k] == 'L') {
					swan[swanCnt++] = new Point(i, k);
					water.offer(new Point(i, k));
				} else if(arr[i][k] == '.') water.offer(new Point(i, k));
			}
		}
	}
	
	static public boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < X && ny < Y;
	}
	
	static public void melting() {
		int curSize = water.size();
		for(int i = 0; i < curSize; i++) {
			Point p = water.poll();
			for(int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if(isIn(nx, ny)) {
					if(arr[nx][ny] == 'X') {
						arr[nx][ny] = '.';
						water.offer(new Point(nx, ny));
					}
				}
			}
		}
	}

	static public void bfs() {
		q.offer(swan[0]);
		visited[swan[0].x][swan[0].y] = true;
		while(true) {
			Queue<Point> nextQ = new ArrayDeque<>();
			while(!q.isEmpty()) {
				Point p = q.poll();
				if(p.x == swan[1].x && p.y == swan[1].y) {
					isMeet = true;
					break;
				}
				
				for(int d = 0; d < 4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					if(isIn(nx, ny) && !visited[nx][ny]) {
						visited[nx][ny] = true;
						if(arr[nx][ny] == 'X') {
							nextQ.offer(new Point(nx, ny));
						} else {
							q.offer(new Point(nx, ny));
						}
					}
				}
			}
			if(isMeet) break;
			q = nextQ;
			melting();
			ansDay++;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		bfs();
		bw.write(ansDay + "\n");
		bw.flush();
		bw.close();
	}
}
