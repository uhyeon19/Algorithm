package SR;

import java.io.*;
import java.util.*;

public class BOJ18111 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, B, map[], landHeight = Integer.MIN_VALUE, Time = Integer.MAX_VALUE;
	static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		map = new int[N * M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < M; k++) {
				map[i * M + k] = Integer.parseInt(st.nextToken());
				min = Integer.min(map[i * M + k], min);
				max = Integer.max(map[i * M + k], max);
			}
		}
	}
	
	public static void solve() {
		for(int h = min; h <= max; h++) {
			int time = 0;
			int curB = B;
			for(int i = 0; i < M * N; i++) {
				if(map[i] > h) {
					time += 2 * (map[i] - h);
					curB += 1 * (map[i] - h);
				} else if(map[i] < h) {
					time += 1 * (h - map[i]);
					curB -= h - map[i];
				}
			}
			// 중간 점검!
			// curB가 나중에 다시 추가될 수 있으니 위에서 curB의 조건을 걸지 않아야 한다!!!
			// curB 조건을 주어서 break;를 걸면 틀린다...ㅠㅠ
			if(Time >= time && curB >= 0) {
				Time = time;
				landHeight = h;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		solve();
		System.out.println(Time + " " + landHeight);
	}
}