package SWEA;

import java.io.*;

public class Solution_1954 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[] dx = {0, 1, 0, -1};	// ?��, �?, ?��, ?��
	static int[] dy = {1, 0, -1, 0};
	static int TC, N;	// ?��?��?�� �??��?�� ?��, N?��기의 배열
	static int[][] arr;	// ?��?��?���? 만들 배열
	static boolean[][] visit;	// ?���? �??��?�� 길인�? ?��?��
	
	public static boolean isIn(int nx, int ny) {	// 배열 밖을 벗어?��?�� 경우�? 막아주기 ?��?��
		if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for(int i = 0; i < TC; i++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visit = new boolean[N][N];
			int d = 0, x = 0, y = 0;	// 처음 ?��?�� 방향?? 0 -> ?���?, 처음 ?��?�� ?���? 0, 0
			
			sb.append("#").append(i + 1).append('\n');		// ?��?��?���??��?�� 번호
			int cnt = 1;		// 1번�??�� ?���? ?��?���? ?��?��
			while(true) {
				arr[x][y] = cnt;	// ?��?�� ?��치에 cnt ?��?��
				visit[x][y] = true;	// ?��?�� ?��치에 ?��?��?��?��?�� visit?�� true�?!
				int nx = x + dx[d];	// ?��?�� x좌표
				int ny = y + dy[d];	// ?��?�� y좌표
				if(isIn(nx, ny) && !visit[nx][ny]) {	// 범위 체크, 방문 체크
					x = nx;		// 범위 ?��?���? 방문?���? ?��?? 곳이?���? ?��?�� 좌표�? ?��?��
					y = ny;
					cnt++;		// ?��?��?�� 곳에 ?��?�� cnt ?��?��
				} else {
					if(cnt == N * N) break;	// cnt�? ?���? N * N까�? ???�� ?�� ?��?�� ?�� 멈춤
					d = (d + 1) % 4;	// 범위�? ?��?��거나 방문?�� 곳이?���? 머리 ?��리기
				}
			}
			
			for(int k = 0; k < N; k++) {
				for(int j = 0; j < N; j++) {
					sb.append(arr[k][j]).append(' ');	// 출력 ?��?�� �?켜서 stringbuilder?�� ?��?��
				}
				sb.append('\n');
			}
		}
		
		System.out.println(sb.toString());	// 출력
		
	}
}
