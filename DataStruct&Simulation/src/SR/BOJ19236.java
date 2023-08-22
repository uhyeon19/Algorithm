package SR;

import java.io.*;
import java.util.*;

public class BOJ19236 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static Fish[][] fish;
	static Point curShark;
	static int ans, curSharkD;
	static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class Fish {
		int num, curD;

		public Fish(int num, int curD) {
			super();
			this.num = num;
			this.curD = curD;
		}
	}
	
	public static boolean isIn(int nx, int ny) {
		if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4) {
			return false;
		}
		return true;
	}
	
	public static void moveFish(int num) {
		for(int i = 0; i < 4; i++) {
			for(int k = 0; k < 4; k++) {
				if(fish[i][k] != null) {
					if(fish[i][k].num == k) {
						
					}
				}
			}
		}
	}

	public static void sharkIn() {
		curShark = new Point(0, 0);
		curSharkD = fish[0][0].curD;
		ans += fish[0][0].num;
		fish[0][0] = null;
	}

	public static void init() throws IOException{
		fish = new Fish[4][4];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			fish[i][0] = new Fish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			fish[i][1] = new Fish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			fish[i][2] = new Fish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			fish[i][3] = new Fish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		sharkIn();
	}
}