package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1786 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String input, pattern;
	static int cnt = 0;	// 개수 저장
	
	static void init() throws IOException {
		input = br.readLine();
		pattern = br.readLine();
	}
	
	/**
	 * 부분 문자열의 부분 일치 테이블 생성 메소드
	 * @return
	 */
	static int[] makeTable() {
		int n = pattern.length();	// 패턴의 길이
		int []table = new int[n];	// 패턴의 길이만큼의 테이블 크기가 나옴
		int idx = 0;	// 패턴 일치 인덱스
		for(int i = 1; i < n; i++) {	// 패턴의 현재 위치 i
			// 현재 위치 i에서 패턴의 문자와 일치하지 않을 경우 이전에 일치했던 곳으로 돌아감
			while(idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx - 1];
			}
			// 현재 위치 i에서 패턴의 문자가 일치할 경우
			if(pattern.charAt(i) == pattern.charAt(idx)) {
				idx += 1;	// 다음 인덱스로
				table[i] = idx;	// i에서 일치 한 문자열의 길이를 table에 저장
			}
		}
		return table;
	}
	
	/**
	 * kmp 알고리즘 메소드
	 * table 만드는 메소드 활용
	 */
	static void kmp() {
		int []table = makeTable();
		int n1 = input.length();
		int n2 = pattern.length();
		int idx = 0;
		for(int i = 0; i < n1; i++) {
			while(idx > 0 && input.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx - 1];
			}
			if(input.charAt(i) == pattern.charAt(idx)) {
				// 문자열 마지막 도달
				// 패턴이 들어있는 곳 찾은 것
				if(idx == n2 - 1) {
					cnt++;
					sb.append(i - idx + 1).append(" ");
					idx = table[idx];
				} else {	// 패턴 내 문자(charAt) 일치하는 곳을 탐색하고 있는 것
					// 다음 문자(charAt) 탐색을 위한 idx 증가
					idx += 1;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		kmp();
		System.out.println(cnt + "\n" + sb.toString());
	}
}
