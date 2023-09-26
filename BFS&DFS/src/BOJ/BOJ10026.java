package BOJ;

import java.io.*;

public class BOJ10026 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n, ans[];
	static char arr[][];
	static boolean visit[][][];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	
	public static void DFS(int x, int y, int people) {
		visit[people][x][y] = true;
		
		 for (int d = 0; d < 4; d++) {
	            int nx = x + dx[d];
	            int ny = y + dy[d];

	            if (!isIn(nx, ny) || visit[people][nx][ny] || arr[nx][ny] != arr[x][y])
	                continue;
	            DFS(nx, ny, people);
	        }
	}
	
	public static boolean isIn(int nx, int ny) {
		if(nx < 0 || ny < 0 || nx >= n || ny >= n) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		ans = new int[2];
		visit = new boolean[2][n][n];
		
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			for(int k = 0; k < str.length(); k++) {
				arr[i][k] = str.charAt(k);
			}
		}
		
		for(int people = 0; people < 2; people++) {
			for(int i = 0; i < n; i++) {
				for(int k = 0; k < n; k++) {
					if(!visit[people][i][k]) {
						DFS(i, k, people);
						ans[people]++;
					}
					if(arr[i][k] == 'G') {
						arr[i][k] = 'R';
					}
				}
			}	
		}
		for(int i = 0; i < 2; i++) {
			System.out.print(ans[i] + " ");
		}
	}
}
