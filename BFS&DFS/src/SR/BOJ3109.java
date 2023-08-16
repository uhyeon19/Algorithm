package SR;

import java.io.*;
import java.util.*;

public class BOJ3109 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, ans = 0;
	static char[][] arr;
	static boolean visit[][], flag;
	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };
	
	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
			return false;
		}
		return true;
	}

	public static void dfs(int x, int y) {
		if (y == C - 1) {	// 내 빵집의 열에 만났으면
			ans++;	// 답을 하나 올려준다
			flag = true;	// 빵집에 도착했다고 알리는 플래그
			visit[x][y] = true;	// 마지막 내 빵집 visit 처리
			return;	// 종료
		}

		for (int d = 0; d < 3; d++) {	// 오른쪽 위, 오른쪽, 오른쪽 밑
			int nx = x + dx[d];	// 다음 위치
			int ny = y + dy[d];

			// 다음 위치가 배열 내에 있고, 방문한 곳이 아니며, 빈 공간일 경우(벽이 아님)
			if (isIn(nx, ny) && !visit[nx][ny] && arr[nx][ny] == '.') {
				dfs(nx, ny);	// 재귀
				if (flag) {		// 재귀 돌고 왔을 때 flag값이 true라면
					visit[x][y] = true;	// 방문처리
					return;	// 다른 방향 즉, 오른쪽 밑 같은 곳은 볼 필요 없음
				} else {	// flag값이 false라면
					visit[x][y] = true;	// 갈 수 없었던 곳 방문 처리만 하고 다시 nx, ny를 살핀다
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		visit = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int k = 0; k < C; k++) {
				arr[i][k] = str.charAt(k);
			}
		}

		for (int i = 0; i < R; i++) {
			flag = false;	// dfs 재귀를 끝냈을 때 내 빵집을 만났는지 확인할 수 있게 설정
			dfs(i, 0);
		}
		System.out.println(ans);
	}
}