package BOJ;

import java.io.*;
import java.util.*;

public class BOJ23291 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int arr[][], copyarr[][];
	static int N, K;
	static int []dx = { -1, 0, 1, 0 };
	static int []dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		init();
		run();
		System.out.println();
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) - 1;
		K = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i <= N; i++) {
			arr[N][i] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void run() {
		int time = 0;
		while(true) {
			if(!addFish()) break;
			make2D();
			divideFish();
			makeHalf();
			divideFish();
			serialization();
			time++;
		}
		System.out.println(time);
	}
	
	static boolean addFish() {
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for(int i = 0; i <= N; i++) {
			if(arr[N][i] < min) min = arr[N][i];
			if(arr[N][i] > max) max = arr[N][i];
		}
		if(max - min <= K) return false;
		for(int i = 0; i <= N; i++) {
			if(arr[N][i] == min) arr[N][i]++;
		}
		return true;
	}
	
	static void make2D() {
		// cnt가 짝수일 때 width 늘어나고 홀수일 때 height 늘어남
		int curIdx = 0, width = 1, height = 1, cnt = 0, nx, ny;
		while (curIdx + width + height < N) {
			cnt++;
			for (int i = curIdx; i < curIdx + width; i++) {
				for (int k = N; k > N - height; k--) {
					ny = N - width + i - curIdx;
					nx = curIdx + width + N - k;
					arr[ny][nx] = arr[k][i];
					arr[k][i] = 0;
				}
			}
			curIdx += width;
			if (cnt % 2 == 0) width++;
			else height++;
		}
	}
	
	static void divideFish() {
		int nx, ny, diff;
		copyarr = new int[N + 1][N + 1];
		for(int i = 0; i <= N; i++) {
			for(int k = 0; k <= N; k++) {
				if(arr[i][k] == 0) continue;
				for(int d = 0; d < 4; d++) {						
					nx = i + dx[d];
					ny = k + dy[d];
					if(!isIn(nx, ny)) continue;
					if(arr[nx][ny] == 0) continue;
					diff = (arr[i][k] - arr[nx][ny]) / 5;
					if(diff > 0) {
						copyarr[i][k] -= diff;
						copyarr[nx][ny] += diff;
					}
				}
			}
		}
		for(int i = 0; i <= N; i++) {
			for(int k = 0; k <= N; k++) {
				arr[i][k] += copyarr[i][k];
			}
		}
	}
	
	static void serialization() {
		
	}
	
	static void makeHalf() {
		
	}
	
	static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx <= N && ny <= N;
	}
}
