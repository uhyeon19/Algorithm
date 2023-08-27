package SR;

import java.io.*;
import java.util.*;

public class Solution_2382 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, N, M, K; // 테스트케이스 수, 배열 크기, 격리시간, 군집 수
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };	// 상하좌우
	static Queue<Microbe> microbes;
	static Microbe arr[][], tmpArr[][];

	static class Microbe {
		int x, y, size, dir, maxSize;

		public Microbe(int x, int y, int size, int dir, int maxSize) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.dir = dir;
			this.maxSize = maxSize;
		}
	}
	
	public static int turn(int d) {
		if(d == 1) return 2;
		else if(d == 2) return 1;
		else if(d == 3) return 4;
		else return 3;
	}
	
	// 0이거나 N - 1이면 특수 약품에 닿은 것
	public static boolean isIn(int nx, int ny) {
		return nx >= 1 && ny >= 1 && nx < N - 1 && ny < N - 1;
	}
	
	public static void moveMicrobe() {
		tmpArr = new Microbe[N][N];
		int size = microbes.size();
		for(int i = 0; i < size; i++) {
			Microbe m = microbes.poll();
			int nx = m.x + dx[m.dir];
			int ny = m.y + dy[m.dir];
			if(isIn(nx, ny)) {
				if(tmpArr[nx][ny] != null) {
					Microbe tmp = tmpArr[nx][ny];
					tmpArr[nx][ny] = new Microbe(nx, ny, tmp.size + m.size, tmp.maxSize < m.size ? m.dir : tmp.dir, tmp.maxSize < m.size ? m.size : tmp.maxSize);
				} else {
					tmpArr[nx][ny] = new Microbe(nx, ny, m.size, m.dir, m.size);
				}
			} else {
				if(tmpArr[nx][ny] != null) {
					if(m.size == 1) continue;
					Microbe tmp = tmpArr[nx][ny];
					tmpArr[nx][ny] = new Microbe(nx, ny, (tmp.size + m.size / 2), tmp.size < m.size ? turn(m.dir) : tmp.dir, tmp.maxSize < m.size ? m.size : tmp.maxSize);
				} else {
					if(m.size == 1) continue;
					tmpArr[nx][ny] = new Microbe(nx, ny, m.size / 2, turn(m.dir), m.size);
				}
			}
		}
	}
	
	public static void makeMicrobe() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(tmpArr[i][k] != null) microbes.offer(tmpArr[i][k]);
			}
		}
	}
	
	public static int addAllMicrobe() {
		int ans = 0;
		while(!microbes.isEmpty()) {
			ans += microbes.poll().size;
		}
		return ans;
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		microbes = new ArrayDeque<>();
		arr = new Microbe[N][N];
		tmpArr = new Microbe[N][N];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			arr[x][y] = new Microbe(x, y, size, dir, size);
			microbes.offer(arr[x][y]);
		}
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			for(int i = 0; i < M; i++) {
				moveMicrobe();
				makeMicrobe();
			}
			bw.write("#" + t + " " + addAllMicrobe() + "\n");
		}
		bw.flush();
		bw.close();
	}
}
