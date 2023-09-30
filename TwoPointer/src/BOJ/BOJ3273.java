package BOJ;

import java.io.*;
import java.util.*;

public class BOJ3273 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int N, arr[], X;
	static HashSet<Integer> set = new HashSet<>();
	static long ans = 0;

	/**
	 * 방안 1. twoPointer
	 * 양 끝을 포인트로 잡고 비교 연산
	 */
	static void twoPointer() {
		int left = 0, right = N - 1;
		while (left < right) {
			int sum = arr[left] + arr[right];
			if (sum == X) {
				ans++;
				left++;
				right--;
			} else if (sum > X) {
				right--;
			} else {
				left++;
			}
		}
	}
	
	/**
	 * 방안 2. set
	 * a + b = X
	 * a = X - b
	 * 	-> 0 ~ N까지(i) 돌면서 set에 (X - arr[i])가 존재하는지 확인
	 * 	즉, X = 13일 때, i = 0이라면 13 - i = 13 - 0이 존재하는지 확인
	 */
	static void hashSet() {
		for(int i=0; i<N; i++) {
            if(set.contains(X-arr[i])) {
                ans++;
            }
        }
		ans /= 2;
	}
	
	/**
	 * 초기화 메소드
	 * @throws IOException
	 */
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			set.add(arr[i]);
		}
		Arrays.sort(arr);
		X = Integer.parseInt(br.readLine());
	}

	public static void main(String[] args) throws IOException {
		init();
		twoPointer();
//		hashSet();
		System.out.println(ans);
	}
}
