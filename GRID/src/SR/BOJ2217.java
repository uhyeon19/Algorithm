package SR;

import java.io.*;
import java.util.*;

public class BOJ2217 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int []arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		long max = 0;
		for(int i = N - 1; i >= 0; i--) {
			arr[i] = arr[i] * (N - i);
			// arr[i] => 가장 큰 무게를 들 수 있는 로프
			// 가장 큰 무게를 들 수 있는 로프로 들 수 있는 최대 중량 = arr[i] * 1
			// 다음 큰 무게를 들 수 있는 로프로 들 수 있는 최대 중량 = arr[i] * 2
			// 다음 큰 무게를 들 수 있는 로프로 들 수 있는 최대 중량 = arr[i] * 3
			if(max < arr[i]) max = arr[i];
		}
		System.out.println(max);
	}
}
