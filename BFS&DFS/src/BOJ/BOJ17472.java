package BOJ;

import java.io.*;
import java.util.*;

// 과정 1. 섬 영역 나누기 (BFS)
// 과정 2. 다리 연결하는 경우 뽑기 (BFS)
// 과정 3. 가장 짧은 다리로 섬 잇기 (크루스칼)
public class BOJ17472 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static boolean visited[][];
	static int N, M, arr[][], landCnt, parents[];
	static int []dx = { -1, 0, 1, 0 };
	static int []dy = { 0, 1, 0, -1 };
	static Queue<Point> q;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	static class Point {
		int x, y, depth;

		public Point(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
	
	// Union-Find, 최소스패닝트리(크루스칼)을 위한 Node
	static class Node implements Comparable<Node> {
		int to;
		int from;
		int dist;
		
		public Node(int to, int from, int dist) {
			this.to = to;
			this.from = from;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);	// 거리 짧은 거 꺼내와야함
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		landCnt = runSetLand();
		runMakeBridge();
		System.out.println(getShortDist());
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < M; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	/**
	 * 1-1. 섬의 영역을 잡기 메소드
	 * 섬의 영역을 잡기 위한 idxNo 생성
	 * idxNo - 1 = landCnt
	 */
	static int runSetLand() {
		visited = new boolean[N][M];
		int idxNo = 1;
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < M; k++) {
				if(arr[i][k] > 0 && !visited[i][k]) {
					setLand(new Point(i, k, 0), idxNo++);
				}
			}
		}
		return idxNo - 1;
	}
	
	/**
	 * 1-2. 섬의 영역을 잡기 메소드
	 * BFS 알고리즘 사용
	 * @param start
	 * @param idxNo
	 */
	static void setLand(Point start, int idxNo) {
		q = new ArrayDeque<>();
		q.offer(start);
		visited[start.x][start.y] = true;
		arr[start.x][start.y] = idxNo;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if(!isIn(nx, ny)) continue;
				if(visited[nx][ny] || arr[nx][ny] == 0) continue;
				q.offer(new Point(nx, ny, p.depth + 1));
				visited[nx][ny] = true;
				arr[nx][ny] = idxNo;
			}
		}
	}
	
	/**
	 * 2-1. 다리 놓기를 위한 메소드
	 */
	static void runMakeBridge() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < M; k++) {
				if(arr[i][k] != 0) {
					makeBridge(new Point(i, k, 0), arr[i][k]);
				}
			}
		}
	}

	/**
	 * 2-2. 다리 놓기를 위한 메소드
	 * BFS 알고리즘 사용
	 * Union-Find를 위한 node 생성
	 * @param point
	 * @param i
	 */
	static void makeBridge(Point start, int idxNo) {
		q = new ArrayDeque<>();
		visited = new boolean[N][M];
		for(int d = 0; d < 4; d++) {
			q.offer(start);
			visited[start.x][start.y] = true;
			while(!q.isEmpty()) {
				Point p = q.poll();
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if(!isIn(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(arr[nx][ny] != idxNo) {	// 다른 섬이거나 바다거나
					if(arr[nx][ny] != 0) {	// 다른 섬이라면
						// 인덱스가 우리는 0번부터로 잡아줄 것이기 때문에 -1 한다.
						int from = idxNo - 1;
						int to = arr[nx][ny] - 1;
						// 다리의 총 길이 = depth
						int bridgeLen = p.depth;
						if(bridgeLen > 1) {	// 다리의 길이는 2 이상이어야한다(조건)
							pq.offer(new Node(from, to, bridgeLen));
							break;
						}
					} else {	// 바다라면 계속 다리 잇기, 다리의 길이는 늘어난다.
						visited[nx][ny] = true;
						q.offer(new Point(nx, ny, p.depth + 1));
					}
				}
			}
		}
		q.clear();
	}

	/**
	 * 3-1. 모두 이어져있는지 확인할 Union-Find의 makeSet 메소드
	 */
	static void makeSet() {
		parents = new int[landCnt];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
	}

	/**
	 * 3-2. 모두 이어져있는지 확인할 Union-Find의 Union 메소드
	 */
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA != parentB) {
			parents[parentB] = parentA;
		}
	}
	
	/**
	 * 3-3. 모두 이어져있는지 확인할 Union-Find의 Find 메소드
	 */
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	/**
	 * 3-4. 최소 스패닝 트리 - 크루스칼 알고리즘
	 * @return
	 */
	static int getShortDist() {
		makeSet();	// parents세팅
		int answerDist = 0;	// 정답
		while(!pq.isEmpty()) {
			Node n = pq.poll();	// dist 작은 거 가져옴
			// 4. union을 통해 합쳐졌기 때문에 다시 연결을 시도해도, dist 큰 것이 들어와도 같은 위치를 바라보고 있기 때문에 걸러짐
			if(find(n.to) == find(n.from)) continue;	// 1. 같은 섬 이동인 경우
			union(n.to, n.from);	// 2. 같은 섬 아닌 경우 union을 통해 연결
			answerDist += n.dist;	// 3. 이어진 곳의 dist 짧은 곳 더함
		}
		
		// 위의 과정을 통해 union을 다 했다면 모두 같은 곳을 바라보고 있을 것이다.
		int num = find(0);	// 첫번째가 바라보고 있는 곳
		for (int i = 1; i < parents.length; i++) {
			if (num != find(i)) {	// 하지만, 바라보는 곳이 다른 게 있다면 연결되지 않은 섬이 있다는 뜻!
				// 모든 섬 연결 불가능 경우로 판정.
				return -1;
			}
		}
		
		return answerDist;
	}
	
	static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < M;
	}
}