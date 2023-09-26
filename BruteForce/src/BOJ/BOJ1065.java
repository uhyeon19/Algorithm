package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1065 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int res = 0;
		
		for(int i = 1; i <= n; i++) {
			boolean flag = true;
			String str = String.valueOf(i);
			char []tmp = str.toCharArray();  // 각 정수 string으로 치환 후 char 배열로 넣기
			for(int j = 0; j < tmp.length - 2; j++) {
				if((tmp[j] - tmp[j + 1]) != (tmp[j + 1] - tmp[j + 2])) {  // 등차인지 확인
					flag = false;  // 등차가 아니라면 flag값 false
					break;
				}
			}
			if(flag == true) res++;  // 등차인 경우 res 1상승
		}
		System.out.println(res);
	}
}