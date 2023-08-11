package SR;

import java.io.*;
import java.util.*;

public class BOJ2578 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	// 빙고의 인덱스 값에 좌표를 담는다.
	// 25개지만 빙고가 1부터 시작하니 1번 인덱스 부터 시작할 것이라 26 사이즈로 만든다.
	static Bingo map[] = new Bingo[26];
	// 사회자가 말하는 숫자를 순서대로 담을 리스트
	static List<Integer> talkNum = new ArrayList<>();
	// 방문 처리할 배열
	static boolean visit[][];
	static int bingo = 0;	// 빙고의 총 수
	// 상, 좌, 대각선 밑, 대각선 위
	// 반대 방향들은 -, + 로 처리 가능
	static int[] dx = { 1, 0, 1, -1 };
	static int[] dy = { 0, 1, 1, 1 };

	/**
	 * 빙고의 좌표를 담을 클래스
	 * @author SSAFY
	 */
	static class Bingo {
		int x, y;

		public Bingo(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	/**
	 * 처음 세팅을 위한 메소트
	 * @throws IOException
	 */
	public static void init() throws IOException {
		visit = new boolean[5][5];
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < 5; k++) {
				// 배열 채우기
				// ex) 11이 처음 좌표로 나왔다면
				//		map[11] = new Bingo(0, 0);
				map[Integer.parseInt(st.nextToken())] = new Bingo(i, k);
			}
		}

		// 사회자가 말하는 번호를 순서대로 담고 있는 리스트 초기화 및 입력
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < 5; k++) {
				talkNum.add(Integer.parseInt(st.nextToken()));
			}
		}
	}

	/**
	 * 사회자가 말한 곳을 방문처리하고 그 위치부터 팔방탐색(반대 위치는 -로 찾는다.)
	 */
	public static void solve() {
		for (int i = 0; i < talkNum.size(); i++) {
			// 사회자가 말한 위치의 좌표를 가져온다.
			Bingo b = map[talkNum.get(i)];
			// 좌표를 방문 처리
			visit[b.x][b.y] = true;

			for (int d = 0; d < 4; d++) {
				int check = 1;	// 방문한 좌표부터 시작이니 1로 초기화
				for(int cnt = 1; cnt < 5; cnt++) {
					// 정방향 다음 위치 탐색
					int nx = b.x + dx[d] * cnt;
					int ny = b.y + dy[d] * cnt;
					// 정방향 다음 위치 배열 범위 안에 있고, 방문 상태라면 check를 올린다.
					if(isIn(nx, ny) && visit[nx][ny]) {
						check++;
					}
					
					// 반대 방향 다음 위치 탐색
					int nnx = b.x - dx[d] * cnt;
					int nny = b.y - dy[d] * cnt;
					// 반대 방향 다음 위치 배열 범위 안에 있고, 방문 상태라면 check를 올린다.
					if(isIn(nnx, nny) && visit[nnx][nny]) {
						check++;
					}
				}
				// 한 줄 탐색 완료
				// 탐색 완료 후 check가 5라면 그 한 줄이 빙고이다.
				if(check == 5) bingo++;
			}
			// 현재 좌표를 색칠(방문)하고 난 후에 bingo가 늘어서 3 이상이 된다면 게임이 끝난 것!
			if(bingo >= 3) {
				System.out.println(i + 1);
				break;
			}
		}
	}
	
	/**
	 * 배열 범위 안에 있는지 체크하기 위한 메소드
	 * @param nx
	 * @param ny
	 * @return
	 */
	public static boolean isIn(int nx, int ny) {
		if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5) return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}
}