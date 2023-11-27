package SWEA;

import java.io.*;
import java.util.*;

// BOJ11401
// https://st-lab.tistory.com/241
// mod연산의 나눗셈 분배 X => 역원을 이용한 곱셈(페르마의 소정리)
// (a * b)mod p = ((a mod p) * (b mod p)) mod p

// (N! / R!(N - R!)) mod p
// 	= (N! * ((R! * (N - R))^-1)) mod p
//	= ((N! mod p) * (((R! * (N - R)!)^-1) mod p)) mod p

// => ((R! * (N - R)!)^-1) mod p = Math.pow((R! * (N - R)!), -1) mod p 바꿔야한다!!
// => 페르마의 소정리
// Math.pow(a, p - 1) % p는 (1 % p)와 합동이다.
// 	= (a * Math.pow(a, p - 2)) % p는 (1 % p)와 합동이다

// a = (R! * (N - R)!), p = 1234567891
// Math.pow((R! * (N - R)!), -1) mod p = Math.pow((R! * (N - R)!), p - 2) mod p

// (N! * (R! * (N - R)!)^-1) mod p
//	= ((N! mod p) * (R! * (N - R)!)^-1 mod p) mod p
//	= ((N! mod p) * ((R! * (N - R)!)^(p-2)) mod p) mod p

public class Solution_5607 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int T;
	static long N, R;
	final static long P = 1234567891L;
	
	public static void main(String[] args) throws IOException {
		run();
	}
	
	static void run() throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			init();
			sb.append("#" + t + " " + solve() + "\n");
		}
		System.out.println(sb.toString());
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
	}
	
	static long solve() {
		// 분자  N! mod p
		// !! factorial 메소드 내에서 mod연산 모두 해야함 !!
		long child = factorial(N);
		
		// (R! * (N - R)!) mod p
		// 	= (R! mod p * (N - R)! mod p) mod p
		// !! factorial 메소드 내에서 mod연산 모두 해야함 !!
		long parent = factorial(R) * factorial(N - R) % P;
		
		// ((N! mod p) * ((R! * (N - R)!)^(p-2)) mod p) mod p
		//	= (child * pow(parent, P - 2) mod p) mod p
		// !! pow 함수에서도 mod연산 계속 할 것 !!
		return child * pow(parent, P - 2) % P;
	}
	
	/**
	 * 팩토리얼 구하는 메소드
	 * 현재 팩토리얼 시에 모드 연산을 계속 해야한다.
	 * @param n
	 * @return
	 */
	static long factorial(long n) {
		long res = 1L;
		while(n > 1) {
			res = (res * n) % P;
			n--;
		}
		return res;
	}
	
	/**
	 * 거듭 제곱 구하는 메소드
	 * 분할정복 알고리즘(백트)
	 * @param a = 밑
	 * @param b = 지수
	 * @return
	 */
	public static long pow(long a, long b) {
		// 지수가 1일 경우 마지막 모드 연산 후 종료
		if(b == 1) {
			return a % P;
		}
		
		// 분할 정복 -> A^8 = A^4 * A^4 => 지수 반토막 내기
		long temp = pow(a, b / 2);
		
		// 현재 지수가 홀수라면
		// ex) A^9 = A^4 * A^4 * A
		// => 한 번 더 본인 자신을 곱해야함 => 모드 연산이 있기 때문에 a % P한 값이 추가된다.
		if(b % 2 == 1) {
			return (temp * temp % P) * a % P;
		}
		return temp * temp % P;
		
	}
}