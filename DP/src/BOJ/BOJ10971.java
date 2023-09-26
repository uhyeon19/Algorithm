package BOJ;

import java.io.*;
import java.util.*;

public class BOJ10971 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, arr[][];
	static long ans = Long.MAX_VALUE;
	static boolean visited[];

	/**
	 * 모든 도시를 방문했는지 검사하는 메소드
	 * @return
	 */
	public static boolean isGoAll() {
		for(boolean visit : visited) if(!visit) return false;	// dfs에서 방문한 도시들을 확인한다.
		return true;	// 확인 했을 때 모두 방문으로 체크되어 있다면 모든 도시 들린 것이다.
	}

	/**
	 * 경우의 수를 뽑을 dfs 메소드
	 * @param curCity
	 * @param curCost
	 */
	public static void dfs(int curCity, long curCost) {
		if (isGoAll()) {	// 모든 곳을 다 돌았다면
			if(arr[curCity][0] != 0) {	// 마지막 도시에서 원래 도시로 갈 수 있는 길이 있는가 확인
				ans = Long.min(curCost + arr[curCity][0], ans);	// 있다면 ans값을 갱신
			}
			return;
		}
		
		// 0번 정점은 이미 선택한 정점으로 1번부터 돌면된다.
		for(int nextCity = 1; nextCity < N; nextCity++) {
			// 이미 방문한 도시는 들리지 않는다 + 갈 수 있는 길이 없는 곳이면 들리지 않는다.
			if(!visited[nextCity] && arr[curCity][nextCity] != 0) {
				visited[nextCity] = true;	// 방문 처리
				dfs(nextCity, curCost + arr[curCity][nextCity]);	// 방문한 곳의 값을 올려주고, 현재 있는 도시 보내주기
				visited[nextCity] = false;	// 상태 원복
			}
		}
	}

	/**
	 * 입력값 세팅 및 초기화.
	 * 초기화 후 dfs 돌리기
	 * @throws IOException
	 */
	public static void run() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N];
		visited[0] = true;	// 0번 정점부터 순회 시작
		dfs(0, 0);	// 0번 정점, 내 위치 = 0원
	}

	public static void main(String[] args) throws IOException {
		run();
		System.out.println(ans);
	}
}