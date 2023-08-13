package SR;

import java.io.*;
import java.util.*;

public class BOJ2669 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int x1, y1, x2, y2, arr[][], ans = 0;
	
	public static void main(String[] args) throws IOException {
		arr = new int[101][101];
		for(int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			int xMin = Integer.min(x1, x2);
			int yMin = Integer.min(y1, y2);
			for(int x = xMin; x < xMin + Math.abs(x1 - x2); x++) {
				for(int y = yMin; y < yMin + Math.abs(y1 - y2); y++) {
					arr[x][y] = 1;
				}
			}
		}
		
		for(int x = 0; x < 101; x++) {
			for(int y = 0; y < 101; y++) {
				if(arr[x][y] == 1) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
