 package SR.SWEA;

import java.util.*;

public class Solution_1289 {
	static int T;	// testCase 저장
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	// Scanner 객체 생성
		StringBuilder sb = new StringBuilder();	// StringBuilder로 출력값 저장
		T = sc.nextInt();						//  testCase 입력
		for(int i = 0; i < T; i++) {
			int ans = 0;						// 바뀌는 횟수 저장할 공간
			char before = '0';					// 이전 상태 저장, 처음에는 00~~이니까 0으로 초기화
			String tmp = sc.next();				// 원래의 값 저장
			for(int k = 0; k < tmp.length(); k++) {	// 원래의 값 길이만큼 for문
				if(before != tmp.charAt(k)) {	// 이전 값이랑 복구할 값 비교 시 다르다면?
					before = tmp.charAt(k);		// 다른 값을 before에 넣은 후
					ans++;						// ans 더해주기
				}
			}
			sb.append("#").append(i + 1).append(" ").append(ans).append("\n");
			// StringBuilder에 값 저장
		}
		System.out.print(sb.toString());	// 값 출력
		sc.close();
	}
}
