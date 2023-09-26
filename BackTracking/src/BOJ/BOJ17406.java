package BOJ;

import java.util.*;
import java.io.*;

public class BOJ17406 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// 기존 배열, 계속 돌려야하니 복사할 배열, rollList에서 조합으로 꺼낼 인덱스, 정답
	static int arr[][], copyArr[][], index[], ans = Integer.MAX_VALUE;
	// x의 크기 N, y의 크기 M, 회전 횟수, 회전 시 시작 위치 잡는 4개의 변수
	static int N, M, rollCnt, startX, startY, endX, endY;
	// 회전 시 방문처리 배열 visit, 조합 사용 시 선택 확인 배열 isSelected
	static boolean visit[][], isSelected[];
	// 회전 정보를 가지고 있는 리스트
	static List<Roll> rollList = new ArrayList<>();
	// 회전 시 사용할 델타 값 -> 시계방향(우, 하, 좌, 상)
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	// 회전 정보를 저장할 수 있는 클래스 만들기
	static class Roll {
		int r, c, s;

		public Roll(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	// 회전 시 사용할 범위 계산
	public static boolean isIn(int nx, int ny) {
		// 이전처럼 0, 0, N, M이 아닌 회전 시 사용되는 r, c, s를 사용한 범위
		if (nx < startX || ny < startY || nx >= endX || ny >= endY) {
			return false;
		}
		return true;
	}

	// 회전 시키는 함수
	public static void rolling(int x, int y) {
		// 처음 시작 방향은 항상 오른쪽
		int curD = 0;
		// 시작 x, y 좌표는 q에 먼저 담아서 바꿔주기 위한 tmp로 사용
		int startX = x;
		int endY = y;
		Queue<Integer> q = new LinkedList<>();
		q.offer(copyArr[x][y]);

		while (!visit[startX][endY]) {
			int nx = x + dx[curD];
			int ny = y + dy[curD];
			// 다음 좌표 만들고 탐색
			if (isIn(nx, ny) && !visit[nx][ny]) {	// 다음 좌표가 범위 안에 있고, 방문한 곳이 아니라면
				q.offer(copyArr[nx][ny]);	// 바꾸기 위해 q에 다음 값을 담아주고
				copyArr[nx][ny] = q.poll();	// 다음 값을 이전에 있던 값으로 넣고
				visit[nx][ny] = true;	// 방문처리하기
				x = nx;	// 현재 위치 변경
				y = ny;
			} else {
				curD = (curD + 1) % 4;	// 벽(범위 외)에 닿았거나 이전 회전을 통해 visit가 처리 되어있는 경우라면 방향 돌리기
			}
		}
	}
	
	public static void minAns() {	// 완성된 회전 배열에서 sum값을 구하고 그 중 가장 작은 값 고르기
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int k = 0; k < M; k++) {
				sum += copyArr[i][k];
			}
			ans = Integer.min(ans, sum);
		}
	}
	
	// 조합 가자...!
	public static void recursionPermutation(int cnt) {
		if (cnt == rollCnt) {	// 회전 수 만큼의 조합이 우리는 필요하다. 회전 수가 된다면 멈추기
			copyArr = new int[N][M];	// 회전을 위한 복사 배열 만들기
			for(int i = 0; i < N; i++) {
				copyArr[i] = arr[i].clone();
			}	// 기존 배열을 복사 배열에 붙여넣기
			
			// 만들어진 인덱스 조합을 가지고 -> 인덱스 순서대로 회전 하기
			for (int i = 0; i < rollCnt; i++) {	
				visit = new boolean[N][M];	// 회전 방문 배열 초기화
				Roll r = rollList.get(index[i]);	// 조합으로 가져온 인덱스의 회전 시작
				startX = r.r - r.s - 1;		// 시작 x좌표
				startY = r.c - r.s - 1;		// 시작 y좌표
				endX = r.r + r.s;			// 마지막 x좌표
				endY = r.c + r.s;			// 마지막 y좌표
				int min = Integer.min(endX - startX, endY - startY);	// 회전 깊이 어디까지인지!
				for(int k = startX; k < startX + min / 2; k++) {
					for(int j = startY; j < startY + min / 2; j++) {
						rolling(k, j);	// 회전
					}
				}
			}
			// 회전 완료 후 ans 값 구하기
			minAns();
			return;
		} else {	// cnt가 아직 rollCnt보다 작을 때 조합을 구하고 있는 중!!
			for (int i = 0; i < rollCnt; i++) {	// 조합 구하기
				if (isSelected[i])	// 선택된 값은 다시 고르지 않음!
					continue;
				index[cnt] = i;	// 선택된 값이 아닐 경우, index로 채택!
				isSelected[i] = true;	// 선택된 값으로 바꾸기
				recursionPermutation(cnt + 1);	// cnt를 1 늘려서 재귀
				isSelected[i] = false;	// 선택된 값 다 끝나면 다시 바꾸기
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		rollCnt = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < rollCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			rollList.add(new Roll(r, c, s));
		}
		// input 받아서 rollList 만들고, N, M 등등 처리(init() 부분)
		
		isSelected = new boolean[rollCnt];
		index = new int[rollCnt];
		recursionPermutation(0);
		
		System.out.println(ans);
	}
}