package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1244 {
	static int N, students, gender, num;
	static int[]switchArr;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	public static boolean isIn(int ly, int ry) {
		if(ly < 1 || ry >= N + 1) {
			return false;
		}
		return true;
	}
	
	public static void init() throws IOException {
		switchArr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			switchArr[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	public static void solve() throws IOException {
		st = new StringTokenizer(br.readLine());
		gender = Integer.parseInt(st.nextToken());
		num = Integer.parseInt(st.nextToken());
		if(gender == 1) {	// 남
			int cnt = 1;
			while(true) {
				if((num * cnt) > N) break;	// 배수 탐색 중 N을 넘는 경우
				switchArr[num * cnt] = switchArr[num * cnt] == 0 ? 1 : 0;
				// 배수 위치들 바꾸기
				cnt++;
			}
		} else {	// 여
			int cnt = 1;
			switchArr[num] = switchArr[num] == 0 ? 1 : 0;	// 본인이 뽑은 위치는 우선 무조건 바꿈
			while(true) {
				int ly = num - cnt;
				int ry = num + cnt;
				if(isIn(ly, ry) && switchArr[ly] == switchArr[ry]) {
					switchArr[ly] = switchArr[ly] == 0 ? 1 : 0;
					switchArr[ry] = switchArr[ry] == 0 ? 1 : 0;
					cnt++;
				} else {
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		init();

		st = new StringTokenizer(br.readLine());
		students = Integer.parseInt(st.nextToken());
		for(int i = 0; i < students; i++) {
			solve();
		}
		for(int i = 1; i <= N; i++) {
			sb.append(switchArr[i]);
			if(i % 20 == 0) {
				sb.append('\n');
			} else {
				sb.append(" ");
			}
		}
		System.out.print(sb.toString());
	}
}