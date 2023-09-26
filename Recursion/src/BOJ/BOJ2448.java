package BOJ;

import java.io.*;
 
public class BOJ2448 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static char[][] arr;
	
	public static void star(int x, int y, int n) {
		if(n == 3) {	// N = 3 일 때 가장 위의 기본 삼각형
			arr[x][y] = '*';
			arr[x + 1][y - 1] = arr[x + 1][y + 1] = '*';
			arr[x + 2][y - 2] = arr[x + 2][y - 1]= arr[x + 2][y] = arr[x + 2][y + 1] = arr[x + 2][y + 2] = '*';
			return;
		}
		
		int current = n / 2;
		star(x, y, current);	// 가로로 반으로 나눴을 때 위의 삼각형
		star(x + current, y - current, current);	// 가로로 반으로 나눴을 때 아래 왼쪽 삼각형
		star(x + current, y + current, current);	// 가로로 반으로 나눴을 때 아래 오른쪽 삼각형
	}
	
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		arr = new char[n][2 * n - 1];
		
		star(0, n - 1, n);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < 2 * n - 1; j++) {
				sb.append(arr[i][j] == '*' ? '*' : ' ');
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}