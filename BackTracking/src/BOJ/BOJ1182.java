package BOJ;

import java.util.*;
import java.io.*;

public class BOJ1182 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, S, arr[], ans = 0;
	static boolean isSelected[];
	
	public static void powerSet(int cnt) {	// 부분집합
		if(cnt == N) {
			int sum = 0;
			boolean check = false;	// 공집합인 경우 체크를 위한 변수
			for(int i = 0; i < N; i++) {
				if(isSelected[i]) {
					sum += arr[i];
					check = true;	// 공집합 제외
				}
			}
			if(check && sum == S) ans++;
			return;
		}
		
		isSelected[cnt] = true;		// 나 자신을 선택한 경우
		powerSet(cnt + 1);
		isSelected[cnt] = false;	// 나 자신을 선택하지 않은 경우
		powerSet(cnt + 1);
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		isSelected = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		powerSet(0);
		System.out.println(ans);
	}
}
