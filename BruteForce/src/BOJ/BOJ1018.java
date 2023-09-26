package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1018 {
	static char [][]ans1 = new char[8][8];
	static char [][]ans2 = new char[8][8];
	
	public static void answer(char[][]ans, int flag) {  // 2가지의 답을 만드는 메소드
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(i % 2 == 0) {
					if(j % 2 == flag) {
						ans[i][j] = 'B';
					} else {
						ans[i][j] = 'W';
					}
				} else {
					if(j % 2 == flag) {
						ans[i][j] = 'W';
					} else {
						ans[i][j] = 'B';
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		answer(ans1, 1);  // 2가지 답 만들기
		answer(ans2, 0);  // 2가지 답 만들기
		
		char [][]arr = new char[n][];  // input 받기
		for(int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		
		int res1 = Integer.MAX_VALUE;  // Black으로 시작하는 체스판에서의 경우
		int res2 = Integer.MAX_VALUE;  // White로 시작하는 체스판에서의 경우
		
		for(int i = 0; i <= n - 8; i++) {     // row가 8이상인 경우의 차를 구함
			for(int j = 0; j <= m - 8; j++) {   // col이 8이상인 경우의 차를 구함
				int result1 = 0;
				int result2 = 0;
				for(int ni = 0; ni < 8; ni++) {   // 정답 8 by 8 배열 검사
					for(int mi = 0; mi < 8; mi++) { // 정답 8 by 8 배열 검사
						if(arr[ni + i][mi + j] != ans1[ni][mi]) {  // 정답 체스판이 B로 시작하는 경우
							result1++;
						}
						if(arr[ni + i][mi + j] != ans2[ni][mi]) {  // 정답 체스판이 W로 시작하는 경우
							result2++;
						}
					}
				}
				res1 = Math.min(res1, result1);
				res2 = Math.min(res2, result2);
			}
		}
		
		System.out.println(Math.min(res1, res2));
	}
}