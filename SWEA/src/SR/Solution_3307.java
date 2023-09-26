package SR;

import java.util.*;
import java.io.*;

// https://sskl660.tistory.com/89
public class Solution_3307 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	static int T, N, arr[], LIS1[], LIS2[], ans1, ans2; // 케이스수, 배열크기, 기존 받아줄 배열 arr, dp로 받아줄 lis, 답

	/**
	 * 기본 값 초기화
	 * 
	 * @throws IOException
	 */
	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}

	/**
	 * LIS(최장 증가 부분 수열) 구하는 메소드 dp사용(이분 탐색 사용 X)
	 */
	public static void LIS1() {
		ans1 = 1;
		LIS1 = new int[N];
		for (int i = 0; i < N; i++) {
			if (LIS1[i] == 0)
				LIS1[i] = 1; // 자기 자신이 있으니 가장 처음 최장 증가 부분 수열은 1
			for (int k = 0; k < i; k++) { // 이전 값들 찾아가기
				if (arr[i] > arr[k]) { // 이전 값보다 내가 크다면 이전 값의 dp에서 + 1한 값이 후보.
					// 후보들 끼리 비교하여 lis(dp)배열 바꿔나가기
					LIS1[i] = Integer.max(LIS1[i], LIS1[k] + 1);
				}
			}
			// 답안을 교체할지 확인
			ans1 = Integer.max(ans1, LIS1[i]);
		}
	}

	
	/**
	 * LIS 이분탐색을 이용한 시간 복잡도 줄이기
	 */
	public static void LIS2() {
		ans2 = 0; // LIS의 길이 = ans
		LIS2 = new int[N]; // LIS2(dp배열) => 바이너리 서치를 통해 배열의 값 중 최장 길이 수열을 저장해 나아갈 것임

		// 첫 번째 원소부터 N번째 원소까지 LIS2 테이블의 값을 채워 나간다.
		for (int i = 0; i < N; i++) {
			// 이분 탐색을 활용하여 LIS2테이블에 저장된 원소를 탐색하며 현재 선택된 숫자가 LIS2테이블의 어떤 위치에 포함될지 파악한다.
			int idx = BinarySearch(arr[i], 0, ans2, ans2 + 1);

			if (idx == -1) { // 찾지 못한 경우
				// 가장 마지막 위치에 원소를 삽입하고 ans2의 길이를 늘린다.
				LIS2[ans2++] = arr[i];
			} else { // 찾은 경우
				// 해당 위치에 현재 값을 삽입하여 갱신한다.
				LIS2[idx] = arr[i];
			}
		}
	}

	/**
	 * 이분탐색 메소드
	 * @param num
	 * @param start
	 * @param end
	 * @param size
	 * @return
	 */
	private static int BinarySearch(int num, int start, int end, int size) {
		int res = 0;
		while (start <= end) {
			int mid = (start + end) / 2;

			if (num <= LIS2[mid]) {	// 만일 현재 선택된 원소가 해당 원소보다 작거나 같다면, 앞 부분을 탐색한다.
				res = mid; 	// 해당 원소의 위치를 기억해둔다.
				end = mid - 1;
			} else { // 만일 현재 선택된 원소가 해당 원소보다 크다면, 뒷 부분을 탐색한다.
				start = mid + 1;
			}
		}

		// LIS2테이블에서 삽입될 위치를 찾지 못한 경우(즉, 모든 수들보다 큰 경우).
		if (start == size) {
			return -1;
		}
		// LIS2테이블에서 삽입될 위치를 찾은 경우.
		else {
			return res;
		}
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			LIS2();
			bw.write("#" + t + " " + ans2 + "\n");
		}
		bw.flush();
		bw.close();
	}
}