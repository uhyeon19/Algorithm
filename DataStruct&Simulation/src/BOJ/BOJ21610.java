package BOJ;

import java.io.*;
import java.util.*;

public class BOJ21610 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, M, arr[][];
	static boolean cloudy[][];
	static Cloud list[];
	static int[] moveDx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] moveDy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] duplDx = { 1, 1, -1, -1 };
	static int[] duplDy = { 1, -1, 1, -1 };

	static class Cloud {
		int d, s;

		public Cloud(int d, int s) {
			this.d = d;
			this.s = s;
		}
	}
	
	static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}
	
	static int sumWater() {
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				ans += arr[i][k];
			}
		}
		return ans;
	}
	
	static void makeCloud() {
		boolean newCloud[][] = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(arr[i][k] >= 2 && !cloudy[i][k]) {
					newCloud[i][k] = true;
					arr[i][k] -= 2;
				}
			}
		}
		for(int i = 0; i < N; i++) {
			cloudy[i] = newCloud[i].clone();
		}
	}
	
	static void duplicate() {
		int copyArr[][] = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				copyArr[i][k] = arr[i][k];
				if(cloudy[i][k]) {
					for(int d = 0; d < 4; d++) {
						int nx = i + duplDx[d];
						int ny = k + duplDy[d];
						if(isIn(nx, ny) && arr[nx][ny] > 0) {
							copyArr[i][k]++;
						}
					}
				}
			}
		}
		for(int i = 0; i < N; i++) {
			arr[i] = copyArr[i].clone();
		}
	}
	
	static void rain() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(cloudy[i][k]) {
					arr[i][k]++;
				}
			}
		}
	}
	
	static void moveCloud(Cloud c) {
		boolean moveCloudy[][] = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(cloudy[i][k]) {
					int nx = (N + i + moveDx[c.d] * (c.s % N)) % N;
					int ny = (N + k + moveDy[c.d] * (c.s % N)) % N;
					moveCloudy[nx][ny] = true;
				}
			}
		}
		for(int i = 0; i < N; i++) {
			cloudy[i] = moveCloudy[i].clone();
		}
	}
	
	static int solve() {
		for(int i = 0; i < M; i++) {
			moveCloud(list[i]);
			rain();
			duplicate();
			makeCloud();
		}
		return sumWater();
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		list = new Cloud[M];
		cloudy = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			list[i] = new Cloud(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		cloudy[N - 1][0] = true;
		cloudy[N - 1][1] = true;
		cloudy[N - 2][0] = true;
		cloudy[N - 2][1] = true;
	}

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(solve());
	}
}
