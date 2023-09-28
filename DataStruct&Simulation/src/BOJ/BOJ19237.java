package BOJ;

import java.io.*;
import java.util.*;

public class BOJ19237 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int N, M, K;
	static Shark arr[][], copyarr[][];
	static Shark sharkList[];
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };

	static class Shark {
		int x, y, d, k, num;
		int prioty[][] = new int[5][5];
		
		public Shark(int x, int y, int k, int num) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.num = num;
		}

		public Shark(Shark s) {
			this.x = s.x;
			this.y = s.y;
			this.d = s.d;
			this.k = s.k;
			this.num = s.num;
			this.prioty = s.prioty;
		}		
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());	// N, M, K
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		sharkList = new Shark[M + 1];
		arr = new Shark[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());	// shark 위치 정보
			for(int k = 0; k < N; k++) {
				int num = Integer.parseInt(st.nextToken());
				if(num != 0) {
					sharkList[num] = new Shark(i, k, K, num);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());	// shark dir 정보
		for(int i = 1; i <= M; i++) {
			Shark s = sharkList[i];
			s.d = Integer.parseInt(st.nextToken());
			arr[s.x][s.y] = new Shark(s);
		}
		
		for(int i = 1; i <= M; i++) {
			for(int d = 1; d < 5; d++) {	// 바라보고 있는 방향
				st = new StringTokenizer(br.readLine()); 	// shark prioty 정보
				for(int p = 1; p < 5; p++)	// 우선순위 4방향
				sharkList[i].prioty[d][p] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void minusTime() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(arr[i][k] != null) {
					arr[i][k].k--;
					if(arr[i][k].k == 0) arr[i][k] = null;
				}
			}
		}
	}
	
	public static void moveShark() {
		copyarr = new Shark[N][N];
		for(int i = M; i > 0; i--) {
			if(sharkList[i] == null) continue;
			Shark s = sharkList[i];
			boolean noShark = false;
			int tmpNx = -1, tmpNy = -1, tmpNd = -1;
			for(int d = 1; d < 5; d++) {
				int nd = s.prioty[s.d][d];
				int nx = s.x + dx[nd];
				int ny = s.y + dy[nd];
				if(!isIn(nx, ny)) continue;
				if(arr[nx][ny] == null) {
					s.x = nx;
					s.y = ny;
					s.d = nd;
					copyarr[nx][ny] = new Shark(s);
					noShark = true;
					break;
				} else if(arr[nx][ny].num == i && tmpNx == -1 && tmpNy == -1 && tmpNd == -1) {
					tmpNx = nx;
					tmpNy = ny;
					tmpNd = nd;
				}
			}
			if(!noShark) {
				s.x = tmpNx;
				s.y = tmpNy;
				s.d = tmpNd;
				copyarr[tmpNx][tmpNy] = new Shark(s);
			}
		}
	}
	
	public static int spreadAndKillShark() {
		int cnt = 0;
		Shark newList[] = new Shark[M + 1];
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(copyarr[i][k] != null) {
					cnt++;
					Shark s = copyarr[i][k];
					newList[s.num] = new Shark(s);
					arr[i][k] = new Shark(s);
				}
			}
		}
		sharkList = newList.clone();
		return cnt;
	}
	
	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}
	
	public static int solve() {
		for(int i = 1; i <= 1000; i++) {
			moveShark();
			minusTime();
			int cnt = spreadAndKillShark();
			if(cnt == 1) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(solve());
	}
}