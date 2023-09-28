package SWEA;

import java.util.*;
import java.io.*;

public class Solution_1263 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	static int T, N, arr[][];
	static final int INF = Integer.MAX_VALUE;
	
	/**
	 * 초기화 메소드
	 * @throws IOException
	 */
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
				if(i != k && arr[i][k] == 0) arr[i][k] = INF;
			}
		}
	}
	
	/**
	 * floydWarshall 알고리즘 메소드
	 * 노드 1개부터 N개까지 거쳐가는 경우를 모두 고려한다.
	 */
	static void floydWarshall() {
		for(int i = 0; i < N; i++) {
			// 노드 k에서 j로 가는 경우
			for(int k = 0; k < N; k++) {
				for(int j = 0; j < N; j++) {
					// i번째 노드를 거쳐가는 비용이 기존 비용보다 더 작은 경우 갱신
					if(arr[k][i] != INF && arr[i][j] != INF)
						arr[k][j] = Integer.min(arr[k][j], arr[k][i] + arr[i][j]);
				}
			}
		}
	}
	
	static int solve() {
		int ans = INF;
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int k = 0; k < N; k++) {
				sum += arr[i][k];
			}
			ans = Integer.min(ans, sum);
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			init();
			floydWarshall();
			bw.write("#" + t + " " + solve() + "\n");
		}
		bw.flush();
		bw.close();
	}
}