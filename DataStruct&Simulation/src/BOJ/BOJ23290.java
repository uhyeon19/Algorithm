package BOJ;

import java.io.*;
import java.util.*;

public class BOJ23290 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int N = 4, M, S, smell[][], dict[], choiceDict[], kill, ans;
	static int []sharkDx = { 0, -1, 0, 1, 0 };
	static int []sharkDy = { 0, 0, -1, 0, 1 };
	static int []fishDx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int []fishDy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static List<Fish> duplFish = new ArrayList<>(), allFish = new ArrayList<>();
	static ArrayList<Fish> map[][];
	static Shark shark;
	static boolean visited[][];
	
	static class Fish implements Cloneable {
		int x, y, d;

		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		protected Fish clone() throws CloneNotSupportedException {
			return (Fish) super.clone();
		}
	}
	
	static class Shark {
		int x, y;

		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		init();
		for(int i = 0; i < S; i++) {
			copy();
			moveFish();
			kill = -1;
			getSharkCase(0);
			moveShark();
			removeSmell();
			setCopyFish();
			ans = setAllFish();
		}
		System.out.println(ans);
	}
	
	@SuppressWarnings("unchecked")
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		smell = new int[N][N];
		dict = new int[3];
		choiceDict = new int[3];
		map = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++)
				map[i][k] = new ArrayList<Fish>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			allFish.add(new Fish(x, y, d));
		}
		st = new StringTokenizer(br.readLine());
		int sharkX = Integer.parseInt(st.nextToken()) - 1;
		int sharkY = Integer.parseInt(st.nextToken()) - 1;
		shark = new Shark(sharkX, sharkY);
	}
	
	static void copy() throws CloneNotSupportedException {
		duplFish.clear();
		for(int i = 0; i < allFish.size(); i++) {
			Fish f = allFish.get(i);
			duplFish.add(f.clone());
		}
	}
	
	static void moveFish() {
		for(int i = 0; i < allFish.size(); i++) {
			Fish f = allFish.get(i);
			int nx = 0, ny = 0, cnt = 0;
			while(true) {
				nx = f.x + fishDx[f.d];
				ny = f.y + fishDy[f.d];
				if (isIn(nx, ny) && smell[nx][ny] == 0 && !(nx == shark.x && ny == shark.y))
					break;
				f.d = f.d - 1;
				if (f.d <= 0) f.d = 8;
				cnt++;
				if (cnt == 8) break;
			}
			if (cnt < 8) {
				f.x = nx;
				f.y = ny;
			}
		}
		for(int i = 0; i < allFish.size(); i++) {
			Fish f = allFish.get(i);
			map[f.x][f.y].add(f);
		}
	}
	
	static void getSharkCase(int cnt) {
		if(cnt == 3) {
			int curkill = getKillCnt(dict);
			if(curkill == -1) return;
			if(kill < curkill) {
				kill = curkill;
				choiceDict = dict.clone();
			}
			return;
		}
		
		for(int i = 1; i <= 4; i++) {
			dict[cnt] = i;
			getSharkCase(cnt + 1);
		}
	}
	
	static int getKillCnt(int dict[]) {
		visited = new boolean[N][N];
		int x = shark.x, y = shark.y, kill = 0;
		for(int i = 0; i < dict.length; i++) {
			x += sharkDx[dict[i]];
			y += sharkDy[dict[i]];
			if(!isIn(x, y)) return -1;
			if(visited[x][y]) continue;
			kill += map[x][y].size();
			visited[x][y] = true;
		}
		return kill;
	}
	
	static void moveShark() {
		for(int i = 0; i < choiceDict.length; i++) {
			shark.x += sharkDx[choiceDict[i]];
			shark.y += sharkDy[choiceDict[i]];
			if(map[shark.x][shark.y].size() > 0) {
				map[shark.x][shark.y].clear();
				smell[shark.x][shark.y] = 3;
			}
		}
	}
	
	static void removeSmell() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(smell[i][k] > 0) smell[i][k]--;
			}
		}
	}
	
	static void setCopyFish() throws CloneNotSupportedException {
		for(int i = 0; i < duplFish.size(); i++) {
			Fish f = duplFish.get(i).clone();
			map[f.x][f.y].add(f);
		}
	}
	
	static int setAllFish() throws CloneNotSupportedException {
		allFish.clear();
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				for(int j = 0; j < map[i][k].size(); j++) {
					allFish.add(map[i][k].get(j).clone());
				}
				map[i][k].clear();
			}
		}
		return allFish.size();
	}
	
	static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}
}