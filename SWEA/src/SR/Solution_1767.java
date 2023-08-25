package SR;

import java.io.*;
import java.util.*;

public class Solution_1767 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, N, arr[][], connectCnt, processCnt, ansProcess, ansLine;	// 테스트케이스 수, 배열 크기, 배열, 연결된 프로세스 수, 프로세스 총 수, 정답 프로세스 수, 정답 라인 수
	static List<Process> pList;	// 프로세스 위치 정보 저장 리스트
	static int[] dx = { -1, 0, 1, 0 };	// 상 우 하 좌
	static int[] dy = { 0, 1, 0, -1 };

	// 프로세스 위치 정보 저장하기 편하게 만드는 클래스
	static class Process {
		int x, y;

		public Process(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// 초기값 세팅
	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		pList = new ArrayList<>();
		connectCnt = 0;
		processCnt = 0;
		ansLine = 0;
		ansProcess = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
				if (arr[i][k] == 1) {	// 프로세스인 경우
					pList.add(new Process(i, k));	// 프로세스 리스트에 쌓고
					processCnt++;	// 프로세스 총 개수 세기
				}
			}
		}
	}

	// 배열 내에 있는지 확인 하는 메소드 => 배열 밖으로 나가면 연결 되었다는 뜻으로 사용
	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}

	// DFS
	public static void dfs(int cnt) {
		if (processCnt - cnt + connectCnt < ansProcess) {	// 가지치기(프로세스 총 개수 - 검사한 프로세스 개수 + 연결된 프로세스 개수 < 현재까지 가장 많이 연결되었던 프로세스)
			return;
		}
		if (cnt == processCnt) {	// 모든 프로세스 수 돌았을 때
			int line = 0;	// 연결된 전선 수 셀 거임
			for(int i = 0; i < N; i++) {
				for(int k = 0; k < N; k++) {
					if(arr[i][k] == 2) line++;	// 전선은 2로 저장했음
				}
			}
			if(connectCnt == ansProcess) {	// 연결된 수가 지금까지 연결되었던 프로세스 수가 같다면
				ansLine = Integer.min(ansLine, line);	// 정답 라인은 더 작은 걸로 해야한다.
			} else if(connectCnt > ansProcess) {	// 연결된 수가 이전 연결되었던 많았다고 생각한 프로세스 수보다 크다면
				ansProcess = connectCnt;	// 교체 작업
				ansLine = line;
			}
			return;
		}

		for (int d = 0; d < 4; d++) {	// 4방 탐색
			int x = pList.get(cnt).x;	// 현재 프로세스 위치
			int y = pList.get(cnt).y;
			int c = 0;	// 전선 연결해 나아가는 수를 세서 이를 원복하기 위해 세는 c => 전선 하나하나 연결되는 개수를 센다
			int count = 1;	// 다음 칸 찾기 위한 변수
			boolean check = false;	// 다른 전선이랑 마주쳐서 멈췄거나, 프로세스와 마주쳐서 멈춘 경우 체크
			while (true) {
				int nx = x + dx[d] * count;	// 다음 x칸
				int ny = y + dy[d] * count++;	// 다음 y칸
				if (!isIn(nx, ny)) {	// 다음 칸의 좌표가 배열 범위 밖이라면 전류가 흐른다
					connectCnt++;	// 연결되었다는 뜻!!
					dfs(cnt + 1);	// dfs 바로 해주기!!(연결 전선을 그대로 두고 해야한다!)
					connectCnt--;	// dfs 나오고 나서는 연결 카운트 상태 원복
					break;	// 멈추기
				}
				if (isIn(nx, ny) && arr[nx][ny] == 0) {	// 전선을 놓을 수 있는 상태라면!
					c++;	// 전선 개수 세기
					arr[nx][ny] += 2;	// 전선 놓기
				} else if (isIn(nx, ny) && arr[nx][ny] != 0) {	// 전선 놓을 수 없는 상태!
					check = true;	// 상태 체크 true
					break;	// 바로 멈추기
				}
			}
			for (int i = 1; i <= c; i++) {	// 상태원복 후
				arr[x + dx[d] * i][y + dy[d] * i] -= 2;
			}
			if (check) {	// 범위 밖으로 간 경우가 아니라서 dfs 아직 안 돌았으니까 연결 안 된 버전으로 dfs 돌린다.
				dfs(cnt + 1);
			}
			
			// 백트래킹
			// 연결 됐냐, 안 됐냐
			// 이 두가지 경우로 dfs 따라 들어간다.
		}
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			dfs(0);
			bw.write("#" + t + " " + ansLine + "\n");
		}
		bw.flush();
		bw.close();
	}
}