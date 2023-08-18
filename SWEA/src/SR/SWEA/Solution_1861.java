package SR.SWEA;

import java.util.*;
import java.io.*;

public class Solution_1861 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer sb = new StringBuffer();
	static StringTokenizer st;
	static int T, N, arr[][];
	static PriorityQueue<Ans> pq;
	static boolean visit[][];
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Ans {
		int roomNo, ans;
		Ans(int roomNo, int ans){
			this.roomNo = roomNo;
			this.ans = ans;
		}
	}
	
	static class Point {
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
			return false;
		}
		return true;
	}
	
	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y));
		visit[x][y] = true;
		int cnt = 1;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if(isIn(nx, ny) && !visit[nx][ny] && arr[nx][ny] == arr[p.x][p.y] + 1) {
					q.offer(new Point(nx, ny));
					visit[nx][ny] = true;
					cnt++;
				}
			}
		}
		pq.offer(new Ans(arr[x][y], cnt));
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			N = Integer.parseInt(br.readLine());
			pq = new PriorityQueue<>((o1, o2) -> o1.ans == o2.ans ? o1.roomNo - o2.roomNo : o2.ans - o1.ans);
			arr = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					arr[i][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			for(int i = 0; i < N; i++) {
				for(int k = 0; k < N; k++) {
					visit = new boolean[N][N];
					bfs(i, k);
				}
			}
			
			Ans ans = pq.poll();
			sb.append(ans.roomNo + " " + ans.ans).append('\n');
		}
		System.out.print(sb.toString());
	}
}
