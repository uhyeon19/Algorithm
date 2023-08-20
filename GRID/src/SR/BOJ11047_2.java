package SR;

import java.io.*;
import java.util.*;

public class BOJ11047_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, K, arr[], ans = 0;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		for(int i = N - 1; i >= 0; i--) {
			if(arr[i] <= K) {
				ans += K / arr[i];
				K %= arr[i];
			}
			if(K == 0) break;
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}
