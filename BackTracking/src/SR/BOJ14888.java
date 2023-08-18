package SR;

import java.io.*;
import java.util.*;

public class BOJ14888 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, arr[], cal[], M, sum, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	
	
	public static void backTrack(int cnt) {
		if(cnt == M) {
			min = Integer.min(min, sum);
			max = Integer.max(max, sum);
			return;
		}
		int cur = sum;
		if(cal[0] != 0) {
			cal[0]--;
			sum += arr[cnt + 1];
			backTrack(cnt + 1);
			cal[0]++;
			sum = cur;
		}
		if(cal[1] != 0) {
			cal[1]--;
			sum -= arr[cnt + 1];
			backTrack(cnt + 1);
			cal[1]++;
			sum = cur;
		}
		if(cal[2] != 0) {
			cal[2]--;
			sum *= arr[cnt + 1];
			backTrack(cnt + 1);
			cal[2]++;
			sum = cur;
		}
		if(cal[3] != 0) {
			cal[3]--;
			sum /= arr[cnt + 1];
			backTrack(cnt + 1);
			cal[3]++;
			sum = cur;
		}
	}
	
	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		cal = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		sum = arr[0];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			cal[i] = Integer.parseInt(st.nextToken());
			M += cal[i];
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		backTrack(0);
		System.out.println(max);
		System.out.println(min);
	}
}