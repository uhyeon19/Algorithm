package BOJ;

import java.io.*;
 
public class BOJ2447 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static char[][] arr;
	static int N;
	
	public static void star(int x, int y, int N, boolean five) {
		if(five) {
			for(int i = x; i < x + N; i++) {
				for(int j = y; j < y + N; j++) {
					arr[i][j] = ' ';
				}
			}
			return;
		}
		
		if(N == 1) {
			arr[x][y] = '*';
			return;
		}
		
		int size = N / 3;
		int cnt = 0;
		for(int i = x; i < x + N; i += size) {
			for(int j = y; j < y + N; j += size) {
				cnt++;
				if(cnt == 5) {
					star(i, j, size, true);
				} else {
					star(i, j, size, false);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		star(0, 0, n, false);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}