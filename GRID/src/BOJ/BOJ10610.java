package BOJ;

import java.util.*;
import java.io.*;

// 우와... 진짜... 어릴 때 봤던 규칙!! 다시 기억하기!!
/**
 * 3의 배수 조건?
 * 각 자리의 숫자를 모두 더했을 때 3의 배수라면 그 수는 3의 배수이다. 
 * ex) 123 -> (1 + 2 + 3) % 3 == 0 -> 3의 배수이다.
 */
public class BOJ10610 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char arr[] = br.readLine().toCharArray();
		Arrays.sort(arr);
		int size = arr.length;
		
		// String ans = "";
		/**
		 * String ans에 + 연산자로 계속 붙여넣다보니
		 * StringPool에서의 연산이 늦어져 runTimeErr가 났음
		 * StringBuilder 사용을 습관화 하기!!
		 */
		StringBuilder sb = new StringBuilder();
		int sum = 0;
		for(int i = arr.length - 1; i >= 0; i--) {
			int num = arr[i] - '0';
			sum += num;
			// ans += num;
			sb.append(num);
		}
		
		if(sum % 3 == 0 && arr[0] == '0') {
			// System.out.println(ans);
			System.out.println(sb.toString());
		} else {
			System.out.println(-1);
		}
	}
}