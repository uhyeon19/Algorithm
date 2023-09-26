package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1541 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "-");
		// "-"를 기준으로 Token 자르기
		// -를 기준으로 괄호를 치게 됨
		// 50-55+40
		// 50-(55+40)
		// +인 곳은 괄호를 치고 묶은 뒤 -로 한번에 빼는 방식이 가장 min한 값
		int sum = Integer.MAX_VALUE;

		while(st.hasMoreTokens()) {	// hasMoreTokens() -> StringTokenizer 자른 후 다음 token이 있는지 확인
			int tmp = 0;
			StringTokenizer addToken = new StringTokenizer(st.nextToken(), "+");
			while(addToken.hasMoreTokens()) {
				tmp += Integer.parseInt(addToken.nextToken());
			}
			
			if(sum == Integer.MAX_VALUE) sum = tmp;		// 처음 부호가 있는 값은 그냥 sum에 담기
			else sum -= tmp;							// 이후의 합쳐진 값은 한번에 빼기
		}
		System.out.println(sum);
	}
}