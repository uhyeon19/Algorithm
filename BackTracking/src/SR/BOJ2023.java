package SR;

import java.io.*;

public class BOJ2023 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N, arr[];
	static boolean[] visit;
	
	public static boolean isPrime(int n) {
		 if(n < 2) return false;
		for(int i = 2; i * i <= n; i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void superPrime(int cnt, int prime) {
		if(cnt == N) {
			sb.append(prime).append('\n');
		}
		
		for(int i = 1; i < 10; i++) {
			int tmp = prime * 10 + i;
			if(isPrime(tmp) && cnt < N) {
				superPrime(cnt + 1, tmp);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		superPrime(0, 0);
		System.out.print(sb.toString());
	}
}
