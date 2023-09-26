package SR;

import java.io.*;
import java.util.*;

public class BOJ16918 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int R, C, N, arr[][], copyArr[][];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		init();
		run();
		for(int i[] : arr) {
			for(int k : i) {
				if(k != 0) bw.write('O');
				else bw.write('.');
			}
			bw.write('\n');
		}
		bw.flush();
		bw.close();
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int k = 0; k < C; k++) {
				if (str.charAt(k) == 'O') {
					arr[i][k] = 3;
				}
			}
		}
	}

	public static void plantBoom() {
		for (int i = 0; i < R; i++) {
			for (int k = 0; k < C; k++) {
				if (arr[i][k] == 0) {
					arr[i][k] = 3;
				}
			}
		}
	}
	
	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < R && ny < C;
	}
	
	public static void boom() {
		copyArr = new int[R][C];
		for(int i = 0; i < R; i++) {
			copyArr[i] = arr[i].clone();
		}
		for(int i = 0; i < R; i++) {
			for(int k = 0; k < C; k++) {
				if(arr[i][k] == 0) {
					for(int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = k + dy[d];
						if(isIn(nx, ny)) {
							copyArr[nx][ny] = 0;
						}
					}
				}
			}
		}
		for(int i = 0; i < R; i++) {
			arr[i] = copyArr[i].clone();
		}
	}
	
	public static void timer() {
		for(int i = 0; i < R; i++) {
			for(int k = 0; k < C; k++) {
				if(arr[i][k] > 0) {
					arr[i][k]--;
				};
			}
		}
	}
	
	public static void run() {
		timer();
		N--;
		while(true) {
			if(N == 0) break;
			timer();
			plantBoom();
			N--;
			if(N == 0) break;
			timer();
			boom();
			N--;
		}
	}

}
