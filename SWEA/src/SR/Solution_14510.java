package SR;

import java.io.*;
import java.util.*;

// !!! 초기 생각 !!!
// 백트래킹? 그리디??
// 그리디! -> 규칙을 살짝 찾아보자
// https://ghs4593.tistory.com/12
// 2는 1로 변경이 가능하다.
// 2와 1의 차이를 최소화 한다.
// 1) 2와 1 중 2가 많다면 day는 2의 개수 * 2가 된다.
// 2) 2와 1 중 1이 많다면 day는 1의 개수 * 2 - 1이 된다.
// 3) 2와 1의 개수가 같다면 번갈아가면서 물을 주면 되기 때문에 1의 개수 + 2의 개수가 된다.
public class Solution_14510 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, N, arr[], max;

	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		max = 0; // 가장 큰 나무 높이
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Integer.max(max, arr[i]);
		}
	}

	public static int getDay() {
		// 나무가 자라야 할 높이에서 필요한 1, 2의 개 수 구하기
		int even = 0, odd = 0;
		for (int i = 0; i < N; i++) {
			int diff = max - arr[i]; // 큰 나무와 현재 나무의 차이
			if (diff == 0) continue; // 이미 가장 큰 나무
			even += diff / 2; // 2의 개수
			odd += diff % 2; // 1의 개수 -> 1개, 0개
		}

		// 2 -> 1로 변경
		if (even > odd) { // 2의 개수가 1의 개수보다 보통은 많을 것임
			while (Math.abs(even - odd) > 1) { // 2와 1의 개수를 최대한 맞춰준다.
				even--;
				odd += 2;
			}
		}

		int result = 0; // 결과 값
		if (odd > even) { // 1이 많을 경우
			result = odd * 2 - 1;
		} else if (even > odd) { // 2가 많을 경우
			result = even * 2;
		} else { // 1과 2의 숫자가 같을 경우
			result = odd + even;
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			bw.write("#" + t + " " + getDay() + "\n");
		}
		bw.flush();
		bw.close();
	}
}
