package BOJ;

import java.io.*;
import java.util.*;

public class BOJ14719 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int H, W, rainHeight[];
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		rainHeight = new int[W];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < W; i++) {
			rainHeight[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	public static int calcRain() {
		int result = 0;
		for(int i = 1; i < W - 1; i++) {
			int leftHeight = 0;
			int rightHeight = 0;
			for(int k = 0; k < i; k++) {
				leftHeight = Integer.max(leftHeight, rainHeight[k]);
			}
			for(int k = i + 1; k < W; k++) {
				rightHeight = Integer.max(rightHeight, rainHeight[k]);
			}
			if(rainHeight[i] < leftHeight && rainHeight[i] < rightHeight)
				result += Integer.min(rightHeight, leftHeight) - rainHeight[i];
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		init();
		bw.write(calcRain() + "\n");
		bw.flush();
		bw.close();
	}
}
