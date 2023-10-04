package BOJ;

import java.io.*;
import java.util.*;

public class BOJ21609 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static PriorityQueue<Group> groupList = new PriorityQueue<>();
	static int arr[][], N, M, score = 0;	// -1 검정, 0 무지개
	static boolean visited[][];
	static Group maxGroup;
	static int []dx = { -1, 0, 1, 0 };
	static int []dy = { 0, 1, 0, -1 };
	static final int BLANK = -2;
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Group implements Comparable<Group> {
		List<Point> listPoint;
		int num;
		int rainbowCnt;
		Point standard;
		
		public Group(List<Point> listPoint, int num, int rainbowCnt, Point standard) {
			this.listPoint = listPoint;
			this.num = num;
			this.rainbowCnt = rainbowCnt;
			this.standard = standard;
		}

		@Override
		public int compareTo(Group o) {
			if(this.listPoint.size() != o.listPoint.size()) {
				return Integer.compare(this.listPoint.size(), o.listPoint.size()) * -1;
			} else if(this.rainbowCnt != o.rainbowCnt) {
				return Integer.compare(this.rainbowCnt, o.rainbowCnt) * -1;
			} else if(this.standard.x != o.standard.x) {
				return Integer.compare(this.standard.x, o.standard.x) * -1;
			} else {
				return Integer.compare(this.standard.y, o.standard.y) * -1;
			}
		}
	}
	
	static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}
	
	static void turning() {
		int [][] tmp = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; ++j) {
                tmp[N-j-1][i] = arr[i][j];
            }
        }
        for(int i = 0; i < N; i++) {
            arr[i] = tmp[i].clone();
        }
	}
	
	static void gravity() {
		for(int i = 0; i < N; i++) {
			for(int k = N - 1; k >= 0; k--) {
				if(arr[k][i] == BLANK || arr[k][i] == -1) continue;
				int dest = k + 1;
				while(true) {
					if(dest == N) break;
					if(arr[dest][i] == BLANK) dest++;
					else break;
				}
				if(dest == k + 1) continue;
				arr[dest - 1][i] = arr[k][i];
				arr[k][i] = BLANK;
			}
		}
	}
	
	static void removeGroup() {
		score += maxGroup.listPoint.size() * maxGroup.listPoint.size();
		for(int i = 0; i < maxGroup.listPoint.size(); i++) {
			Point p = maxGroup.listPoint.get(i);
			arr[p.x][p.y] = BLANK;
		}
	}
	
	static void bfs(Point start) {
		int rainbowCnt = 0;
		int basicCnt = 0;
		int num = arr[start.x][start.y];
		Point standard = null;
		List<Point> list = new ArrayList<>();
		
		Queue<Point> q = new ArrayDeque<>();
		visited[start.x][start.y] = true;
		q.offer(start);
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			list.add(p);
			if(arr[p.x][p.y] == 0) rainbowCnt++;
			else basicCnt++;
			if(arr[p.x][p.y] != 0 && standard == null) standard = new Point(p.x, p.y);
			for(int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if(!isIn(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(arr[nx][ny] == num || arr[nx][ny] == 0) {
					visited[nx][ny] = true;
					q.offer(new Point(nx, ny));
				}
			}
		}
		if(list.size() >= 2 && basicCnt != 0) {
			groupList.offer(new Group(list, num, rainbowCnt, standard));
		}
		isRainbow();
	}
	
	static void isRainbow() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(arr[i][k] == 0) {
					visited[i][k] = false;
				}
			}
		}
	}
	
	static Group getMaxGroup() {
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(arr[i][k] > 0 && !visited[i][k]) {
					bfs(new Point(i, k));
				}
			}
		}
		return !groupList.isEmpty() ? groupList.poll() : null;
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		while(true) {
			groupList = new PriorityQueue<>();
			maxGroup = getMaxGroup();
			if(maxGroup == null) break;
			removeGroup();
			gravity();
			turning();
			gravity();
		}
		System.out.println(score);
	}
}