package SR;

import java.util.*;
import java.io.*;

public class BOJ16935 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, R, arr[][], rArr[], tmpArr[][];
	static int firstArr[][], secondArr[][], thirdArr[][], fourthArr[][];

	public static void oneCalc() {
		for (int i = 0; i < N / 2; i++) {
			int[] tmp = arr[arr.length - 1 - i].clone();
			arr[arr.length - 1 - i] = arr[i];
			arr[i] = tmp;
		}
	}

	public static void twoCalc() {
		tmpArr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmpArr[i][M - j - 1] = arr[i][j];
			}
		}
		arr = tmpArr;
	}

	public static void threeCalc() {
		tmpArr = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmpArr[j][N - i - 1] = arr[i][j];
			}
		}

		int tmp = N;
		N = M;
		M = tmp;
		arr = tmpArr;
	}

	public static void fourCalc() {
		tmpArr = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmpArr[M - j - 1][i] = arr[i][j];
			}
		}

		int tmp = N;
		N = M;
		M = tmp;
		arr = tmpArr;
	}

	public static int[][] divideArr(int startX, int startY, int finX, int finY) {
		int tmp[][] = new int[N/2][M/2];
		for(int i = startX; i < finX; i++) {
			for(int k = startY; k < finY; k++) {
				tmp[i - startX][k - startY] = arr[i][k];
			}
		}
		return tmp;
	}
	
	public static void attach(int startX, int startY, int finX, int finY, int tmpArr[][]) {
		for(int i = startX; i < finX; i++) {
			for(int k = startY; k < finY; k++) {
				arr[i][k] = tmpArr[i - startX][k - startY];
			}
		}
	}
	
	public static void fiveCalc() {
		firstArr = divideArr(0, 0, N/2, M/2);
		secondArr = divideArr(0, M/2, N/2, M);
		thirdArr = divideArr(N/2, M/2, N, M);
		fourthArr = divideArr(N/2, 0, N, M/2);
		
		attach(0, 0, N/2, M/2, fourthArr);
		attach(0, M/2, N/2, M, firstArr);
		attach(N/2, M/2, N, M, secondArr);
		attach(N/2, 0, N, M/2, thirdArr);
	}
	
	public static void sixCalc() {
		firstArr = divideArr(0, 0, N/2, M/2);
		secondArr = divideArr(0, M/2, N/2, M);
		thirdArr = divideArr(N/2, M/2, N, M);
		fourthArr = divideArr(N/2, 0, N, M/2);
		
		attach(N/2, 0, N, M/2, firstArr);
		attach(0, 0, N/2, M/2, secondArr);
		attach(0, M/2, N/2, M, thirdArr);
		attach(N/2, M/2, N, M, fourthArr);
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		rArr = new int[R];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			rArr[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = 0; i < R; i++) {
			int r = rArr[i];
			if(r == 1) oneCalc();
			else if(r == 2) twoCalc();
			else if(r == 3) threeCalc();
			else if(r == 4) fourCalc();
			else if(r == 5) fiveCalc();
			else if(r == 6) sixCalc();
		}

		for(int[] array : arr) {
			for(int i : array) {
				sb.append(i + " ");
			}
			sb.append('\n');
		}
		
		System.out.print(sb.toString());
	}
}