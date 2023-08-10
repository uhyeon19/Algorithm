package SR.Study;

import java.io.*;
import java.util.*;

public class PowerSet {
	static int N, M;
	static int[] arr;
	static boolean[] isSelected;

	public static void powerSet(int cnt) {
		if (cnt == N) {
			for(int i = 0; i < N; i++) {
				if(isSelected[i]) System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		// 해당 숫자를 선택하고 재귀를 진행
		isSelected[cnt] = true;
		powerSet(cnt + 1);
		
		// 해당 숫자를 선택하지 않고 재귀를 진행
		isSelected[cnt] = false;
		powerSet(cnt + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		arr = new int[N];
		for(int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		}
		
		isSelected = new boolean[N];
		powerSet(0);
	}
}
