package SR.SWEA;

import java.io.*;

public class Solution_1954 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[] dx = {0, 1, 0, -1};	// 동, 북, 서, 남
	static int[] dy = {1, 0, -1, 0};
	static int TC, N;	// 테스트 케이스 수, N크기의 배열
	static int[][] arr;	// 달팽이를 만들 배열
	static boolean[][] visit;	// 이미 지나온 길인지 확인
	
	public static boolean isIn(int nx, int ny) {	// 배열 밖을 벗어나는 경우를 막아주기 위함
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
			int d = 0, x = 0, y = 0;	// 처음 시작 방향은 0 -> 동쪽, 처음 시작 위치 0, 0
			
			sb.append("#").append(i + 1).append('\n');		// 테스트케이스 번호
			int cnt = 1;		// 1번부터 크기 키우며 삽입
			while(true) {
				arr[x][y] = cnt;	// 현재 위치에 cnt 삽입
				visit[x][y] = true;	// 현재 위치에 삽입했으니 visit을 true로!
				int nx = x + dx[d];	// 다음 x좌표
				int ny = y + dy[d];	// 다음 y좌표
				if(isIn(nx, ny) && !visit[nx][ny]) {	// 범위 체크, 방문 체크
					x = nx;		// 범위 안이며 방문하지 않은 곳이라면 다음 좌표로 이동
					y = ny;
					cnt++;		// 이동한 곳에 넣을 cnt 상승
				} else {
					if(cnt == N * N) break;	// cnt가 이미 N * N까지 와서 다 삽입 후 멈춤
					d = (d + 1) % 4;	// 범위가 아니거나 방문한 곳이라면 머리 돌리기
				}
			}
			
			for(int k = 0; k < N; k++) {
				for(int j = 0; j < N; j++) {
					sb.append(arr[k][j]).append(' ');	// 출력 형식 지켜서 stringbuilder에 삽입
				}
				sb.append('\n');
			}
		}
		
		System.out.println(sb.toString());	// 출력
		
	}
}
