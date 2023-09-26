 package SWEA;

import java.util.*;

public class Solution_1289 {
	static int T;	// testCase ???��
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	// Scanner 객체 ?��?��
		StringBuilder sb = new StringBuilder();	// StringBuilder�? 출력�? ???��
		T = sc.nextInt();						//  testCase ?��?��
		for(int i = 0; i < T; i++) {
			int ans = 0;						// 바�?�는 ?��?�� ???��?�� 공간
			char before = '0';					// ?��?�� ?��?�� ???��, 처음?��?�� 00~~?��?���? 0?���? 초기?��
			String tmp = sc.next();				// ?��?��?�� �? ???��
			for(int k = 0; k < tmp.length(); k++) {	// ?��?��?�� �? 길이만큼 for�?
				if(before != tmp.charAt(k)) {	// ?��?�� 값이?�� 복구?�� �? 비교 ?�� ?��르다�??
					before = tmp.charAt(k);		// ?���? 값을 before?�� ?��?? ?��
					ans++;						// ans ?��?��주기
				}
			}
			sb.append("#").append(i + 1).append(" ").append(ans).append("\n");
			// StringBuilder?�� �? ???��
		}
		System.out.print(sb.toString());	// �? 출력
		sc.close();
	}
}
