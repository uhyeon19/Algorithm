package BOJ;

import java.io.*;
import java.util.*;

public class BOJ21611 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int N, M, arr[][], copyArr[][], boomCntList[];
	static int []dx = { 0, -1, 1, 0, 0 };
	static int []dy = { 0, 0, 0, -1, 1 };
	static int[] ddx = { 0, 1, 0, -1 }; // 좌 하 우 상 이동
	static int[] ddy = { -1, 0, 1, 0 };
	static Point start, end;
	static List<Integer> list, newList;
	static Skill skillList[];
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Skill {
		int d, s;

		public Skill(int d, int s) {
			this.d = d;
			this.s = s;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		run();
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		skillList = new Skill[M];
		start = new Point(N / 2, N / 2);
		end = new Point(0, 0);
		boomCntList = new int[4];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			skillList[i] = new Skill(d, s);
		}
	}
	
	static void run() {
		for(int i = 0; i < M; i++) {
			skill(skillList[i]);
			makeList();
			boom();
			grouping();
			makeNewArr();
		}
		System.out.println(boomCntList[1] + boomCntList[2] * 2 + boomCntList[3] * 3);
	}
	
	// 1. 유리 구슬 부수기
	static void skill(Skill skill) {
		for(int i = 1; i <= skill.s; i++) {
			int nx = start.x + dx[skill.d] * i;
			int ny = start.y + dy[skill.d] * i;
			if(!isIn(nx, ny)) continue;
			arr[nx][ny] = 0;
		}
	}
	
	// 2. 부순 후 부서진 곳 제외 리스트 만들기
	static void makeList() {
		list = new ArrayList<>();
		int x = start.x;
		int y = start.y;
		int moveCnt = 0, moveS = 1, moveD = 0, check = 0;
		while(true) {
			int nx = x + ddx[moveD];
			int ny = y + ddy[moveD];
			if(nx == end.x && ny == end.y) break;
			if(arr[nx][ny] != 0) list.add(arr[nx][ny]);
			moveCnt++;
			if(moveS == moveCnt) {
				moveCnt = 0;
				moveD = (moveD + 1) % 4;
				check++;
			}
			if(check == 2) {
				check = 0;
				moveS++;
			}
			x = nx;
			y = ny;
		}
	}
	
	// 3. 폭발 일으키기
	static void boom() {
		while(true) {
			if(list.size() == 0) return;
			boolean isBoom = false;
			int curNo = 0;
			int cnt = 1;
			int removeIdx = 0;
			int idx = list.size() - 1;
			while(true) {
				if(idx != -1 && curNo == list.get(idx)) {
					removeIdx = idx;
					cnt++;
				} else {
					if(cnt >= 4) {
						isBoom = true;
						boomCntList[curNo] += cnt;
						for(int k = 0; k < cnt; k++) {
							list.remove(removeIdx);
						}
					}
					if(idx == -1) break;
					cnt = 1;
					curNo = list.get(idx);
				}
				idx--;
			}
			if(!isBoom) break;
		}
	}
	
	// 4. 그룹 묶어 변환하기
	static void grouping() {
		if(list.size() == 0) return;
		newList = new ArrayList<>();
		int curNo = list.get(0), cnt = 1;
		for(int i = 1; i < list.size(); i++) {
			if(curNo == list.get(i)) {
				cnt++;
			} else {
				newList.add(cnt);
				newList.add(curNo);
				curNo = list.get(i);
				cnt = 1;
			}
		}
		newList.add(cnt);
		newList.add(curNo);
		list = newList;
	}
	
	// 5. 붙여넣기
	static void makeNewArr() {
		if(list.size() == 0) return;
		copyArr = new int[N][N];
		int x = start.x;
		int y = start.y;
		int moveCnt = 0, moveS = 1, moveD = 0, check = 0;
		for(int i = 0; i < list.size(); i++) {
			if(x == end.x && y == end.y) break;
			int nx = x + ddx[moveD];
			int ny = y + ddy[moveD];
			copyArr[nx][ny] = list.get(i);
			moveCnt++;
			if(moveS == moveCnt) {
				moveCnt = 0;
				moveD = (moveD + 1) % 4;
				check++;
			}
			if(check == 2) {
				check = 0;
				moveS++;
			}
			x = nx;
			y = ny;
		}
		for(int i = 0; i < N; i++) {
			arr[i] = copyArr[i].clone();
		}
		list.clear();
		newList.clear();
	}
		
	static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}
}