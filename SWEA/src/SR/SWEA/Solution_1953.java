package SR.SWEA;

import java.io.*;
import java.util.*;

public class Solution_1953 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int arr[][], T, N, M, curR, curC, L, ans;
	static boolean visited[][];
	static Map<Integer, Delta[]> deltaMap = new HashMap<>();
	static List<List<Integer>> indexList = new ArrayList<>();

	static class Delta {
		int x, y, curD;

		public Delta(int x, int y, int curD) {
			this.x = x;
			this.y = y;
			this.curD = curD;
		}
	}

	public static boolean isIn(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M)
			return false;
		return true;
	}
	
	public static void addVisit() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < M; k++) {
				if(visited[i][k]) ans++;
			}
		}
	}

	public static void solve() {
		int count = 1;
		Queue<Delta> q = new LinkedList<>();
		q.offer(new Delta(curR, curC, -1));	// 큐에 담기는 값 중에서는 curD가 필요하진 않아서 그냥 -1로 넣었다..
		visited[curR][curC] = true;
		while(!q.isEmpty() && L != 1) {
			int size = q.size();
			while(--size >= 0) {
				Delta d = q.poll();
				int x = d.x;
				int y = d.y;
				Delta dArr[] = deltaMap.get(arr[x][y]);
				for(int i = 0; i < dArr.length; i++) {
					int nx = x + dArr[i].x;
					int ny = y + dArr[i].y;
					// 다음 위치에 있는 파이프가 바라보고 있는 위치들 안에, 내가 바라보고 있는 곳의 반대방향이 있다면 갈 수 있는 곳이라는 뜻!!
					if(isIn(nx, ny) && arr[nx][ny] != 0 && !visited[nx][ny] && indexList.get(arr[nx][ny]).contains(dArr[i].curD)) {						
						q.offer(new Delta(nx, ny, -1));
						visited[nx][ny] = true;
					}
				}
			}
			count++;
			if(count == L) break;
		}
		addVisit();
	}
	
	/**
	 * deltaMap에 담기는 값 = delta x, y값.
	 * delta가 위를 탐색하는 방향이라면 그 방향에 있는 값이 밑을 바라볼 수 있는지 확인한다.
	 * 즉, nx ny값이 내 현재 위치의 위라면 현재 내가 있는 위치에서 내가 바라보고 있는 방향은 위일테니, 위에 칸에 있는 파이프가 밑을 바라보고 있는지 체킹할 것!
	 * 
	 * indexList는 각 파이프의 번호별로 향할 수 있는 곳이다.
	 */
	public static void setMap() {
		// 상 0 우 1 하 2 좌 3
		// 상 -> 2, 우 -> 3, 하 -> 0, 좌 -> 1
		Delta one[] = { new Delta(-1, 0, 2), new Delta(0, 1, 3), new Delta(1, 0, 0), new Delta(0, -1, 1) };
		deltaMap.put(1, one);
		Delta two[] = { new Delta(-1, 0, 2), new Delta(1, 0, 0) };
		deltaMap.put(2, two);
		Delta three[] = { new Delta(0, 1, 3), new Delta(0, -1, 1) };
		deltaMap.put(3, three);
		Delta four[] = { new Delta(-1, 0, 2), new Delta(0, 1, 3) };
		deltaMap.put(4, four);
		Delta five[] = { new Delta(0, 1, 3), new Delta(1, 0, 0) };
		deltaMap.put(5, five);
		Delta six[] = { new Delta(1, 0, 0), new Delta(0, -1, 1) };
		deltaMap.put(6, six);
		Delta seven[] = { new Delta(-1, 0, 2), new Delta(0, -1, 1) };
		deltaMap.put(7, seven);

		indexList.add(null);
		indexList.add(Arrays.asList(0, 1, 2, 3));
		indexList.add(Arrays.asList(0, 2));
		indexList.add(Arrays.asList(1, 3));
		indexList.add(Arrays.asList(0, 1));
		indexList.add(Arrays.asList(1, 2));
		indexList.add(Arrays.asList(2, 3));
		indexList.add(Arrays.asList(0, 3));	
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		curR = Integer.parseInt(st.nextToken());
		curC = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		ans = 0;
		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		setMap();
		for (int t = 1; t <= T; t++) {
			init();
			solve();
			sb.append("#" + t + " " + ans + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}