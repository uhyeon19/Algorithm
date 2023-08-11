package SR;

import java.util.*;
import java.io.*;

public class BOJ2565 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, LIS[];
	static Wire wire[];

	static class Wire {
		int a, b;

		public Wire(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	// 1. 잘라내야할 전깃줄을 찾는 것 X!!
	// 2. 총 전깃줄 수(N) - 설치 가능한 전깃줄 최대 수 = 잘라내야할 전깃줄 수
	// 3. 설치 가능한 전깃줄 수 = LIS(Longest Increasing Subsequence)
	// 4. LIS를 DP로 구한 후 그 중 가장 큰 값을 지닌 것이 설치 가능한 전깃줄 수 최대!
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		wire = new Wire[N];
		LIS = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			wire[i] = new Wire(a, b);
		}
		
		// A의 순서대로 정렬
		Arrays.sort(wire, (o1, o2) -> o1.a - o2.a);
		// DP로 LIS 구하기
		for(int i = 0; i < N; i++) {
			if(LIS[i] == 0) LIS[i] = 1;
			for(int k = 0; k < i; k++) {
				if(wire[i].b > wire[k].b) {
					LIS[i] = Integer.max(LIS[i], LIS[k] + 1);
				}
			}
		}
		
		Arrays.sort(LIS);	// LIS가 가장 큰 값이 설치할 수 있는 최대의 전깃줄 수
		System.out.println(N - LIS[N - 1]);	// 총 전깃줄 수 - 최대 설치 가능한 전깃줄 수
	}
}