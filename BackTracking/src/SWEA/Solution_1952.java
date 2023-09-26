package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1952 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] ticket = new int[4]; // 0 : 1�� �̿�� �ݾ�, 1 : 1��, 2 : 3��, 3 : 1��
	static int[] plan = new int[12]; // 12���� �̿� ��ȹ ���� �迭
	static int result, T;
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			ticket[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 12; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		result = Integer.MAX_VALUE;
	}
	
	public static void swimming(int month, int pay) {
		if(month >= 12) {
			result = Integer.min(pay, result);
			return;
		}
		
		swimming(month + 1, pay + (ticket[0] * plan[month]));
		swimming(month + 1, pay + ticket[1]);
		swimming(month + 3, pay + ticket[2]);
		swimming(month + 12, pay + ticket[3]);
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			init();
			swimming(0, 0);
			bw.write("#" + t + " " + result + "\n");
		}
		bw.flush();
		bw.close();
	}
}
