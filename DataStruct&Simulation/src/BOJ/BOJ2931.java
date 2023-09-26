package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2931 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int visited[][], map[][], R, C, ansPipe;
	static char ans;
	static Point start, end, breakPoint;
	static String type[];
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, 1, -1, 0 };

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= R || ny >= C)
			return false;
		return true;
	}

	public static void setType() {
		type = new String[8];
		type[1] = "0123";
		type[2] = "03";
		type[3] = "12";
		type[4] = "31";
		type[5] = "01";
		type[6] = "20";
		type[7] = "23";
	}

	public static Point searchVertex(Point p) {
		for (int d = 0; d < 4; d++) {
			int nx = p.x + dx[d];
			int ny = p.y + dy[d];
			if (isIn(nx, ny) && map[nx][ny] > 0) {
				return new Point(nx, ny);
			}
		}
		return null;
	}

	public static void dfs(Point p, int visited[][]) {
		visited[p.x][p.y]++;
		String curType = type[map[p.x][p.y]];
		for (int i = 0; i < curType.length(); i++) {
			int nx = p.x + dx[curType.charAt(i) - '0'];
			int ny = p.y + dy[curType.charAt(i) - '0'];

			if (!isIn(nx, ny)) continue;
			int d = curType.charAt(i) - '0';
			if (map[nx][ny] == 1 && visited[nx][ny] < 2) {
				String nextType = type[map[nx][ny]];
				if (nextType.contains(String.valueOf(3 - d))) {
					dfs(new Point(nx, ny), visited);
				}
			}
			if (map[nx][ny] > 0 && visited[nx][ny] == 0) {
				String nextType = type[map[nx][ny]];
				if (nextType.contains(String.valueOf(3 - d))) {
					dfs(new Point(nx, ny), visited);
				}
			}

			if (map[nx][ny] == 0 && visited[nx][ny] == 0) {
				breakPoint = new Point(nx, ny);
				return;
			}
		}
	}

	public static int findPipe() {
		int x = breakPoint.x;
		int y = breakPoint.y;
		String makePipe = "";
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(!isIn(nx, ny) || map[nx][ny] <= 0) continue;
			String t = type[map[nx][ny]];
			if(t.contains(String.valueOf(3 - d))) {
				makePipe += String.valueOf(d);
			}
		}
		if(makePipe.length() == 4) return 1;
		for(int i = 2; i <= 7; i++) {
			boolean check = true;
			String tmp = type[i];
			for(int k = 0; k < makePipe.length(); k++) {
				if(!tmp.contains(String.valueOf(makePipe.charAt(k)))){
					check = false;
					break;
				}
			}
			if(check) return i;
		}
		return 0;
	}
	
	public static char changePipe(int ansPipe) {
		if (ansPipe == 1) return '+'; 
		else if (ansPipe == 2) return '|';
		else if (ansPipe == 3) return '-';
		else if (ansPipe == 4) return '1';
		else if (ansPipe == 5) return '2';
		else if (ansPipe == 6) return '3';
		else return '4';
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new int[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int k = 0; k < C; k++) {
				if (str.charAt(k) == 'M') {
					map[i][k] = -1;
					start = new Point(i, k);
				} else if (str.charAt(k) == 'Z') {
					map[i][k] = -1;
					end = new Point(i, k);
				} else if (str.charAt(k) == '+') map[i][k] = 1;
				else if (str.charAt(k) == '|') map[i][k] = 2;
				else if (str.charAt(k) == '-') map[i][k] = 3;
				else if (str.charAt(k) == '1') map[i][k] = 4;
				else if (str.charAt(k) == '2') map[i][k] = 5;
				else if (str.charAt(k) == '3') map[i][k] = 6;
				else if (str.charAt(k) == '4') map[i][k] = 7;
			}
		}
		start = searchVertex(start);
		end = searchVertex(end);
	}

	public static void main(String[] args) throws IOException {
		init();
		setType();
		dfs(start, visited);
		bw.write((breakPoint.x + 1)  + " "  + (breakPoint.y + 1) + " " + changePipe(findPipe()));
		bw.flush();
		bw.close();
	}
}