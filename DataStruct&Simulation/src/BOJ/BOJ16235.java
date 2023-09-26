package BOJ;

import java.io.*;
import java.util.*;

public class BOJ16235 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 시간 최적화를 위해 bw를 써보자!
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, M, K, A[][], nutrient[][];
	static Deque<Tree> tree = new LinkedList<>();
	static Queue<Tree> dieTree;
	static Queue<Tree> addTree;
	static int[] dx = { 1, -1, 0, 0, 1, 1, -1, -1 };
	static int[] dy = { 0, 0, -1, 1, 1, -1, 1, -1 };

	static class Tree {
		int x, y, age;

		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}

	public static boolean isIn(int nx, int ny) {
		// 1, 1부터 시작했고 배열 사이즈가 N + 1이니 범위를 벗어나는 곳은 N보다 큰 경우이다.
		if (nx < 1 || ny < 1 || nx > N || ny > N)
			return false;
		return true;
	}

	// 봄
	// 조건: 입력으로 주어지는 나무의 위치는 모두 다르다 -> 나이 체크는 '가을'에 번식하고나서 해주면 된다.
	public static void spring() {
		dieTree = new LinkedList<>();
		for (int i = 0; i < tree.size();) {
			Tree t = tree.poll();
			if (nutrient[t.x][t.y] >= t.age) { // 나무 위치의 양분이 t.age보다 크거나 같으면
				nutrient[t.x][t.y] -= t.age; // 양분을 나이만큼 빼주기
				t.age++; // 나이 먹음
				tree.offer(t); // 나이 먹은 나무 다시 넣어주기
				i++; // tree.poll()을 하여 줄어든 tree.size에 tree.offer로 다시 size를 늘렸다. 이를 위해 i++를 해서 다음 친구를 찾아줘야한다.
			} else {
				dieTree.offer(t); // 양분이 모자르면 나무가 죽는다.
			}
		}
	}

	// 여름
	// 봄에 죽은 나무들의 위치에 양분이 추가된다. age / 2만큼!
	public static void summer() {
		for (Tree t : dieTree) {
			nutrient[t.x][t.y] += t.age / 2;
		}
	}

	// 가을
	// 5의 배수 나무라면 번식을 한다.
	public static void fall() {
		addTree = new LinkedList<>();
		for (Tree t : tree) {
			if (t.age % 5 == 0) {
				addTree.offer(t);
			}
		}
		
		// 번식 가능한 나무가 있다면
		while (!addTree.isEmpty()) {
			Tree t = addTree.poll();
			// 8방으로 뻗어나간다.
			for (int d = 0; d < 8; d++) {
				int nx = t.x + dx[d];
				int ny = t.y + dy[d];
				if (isIn(nx, ny)) {	// 8방이 내 땅 안에 있다면
					tree.addFirst(new Tree(nx, ny, 1));	
					// 새로 만들어지는 나무는 1살로 가장 어리다. 처음에 넣어주기
				}
			}
		}
	}
	
	// 겨울
	// 양분을 추가한다. 각 nutrient칸에 추가되는 양분의 양 = A[r][c]
	public static void winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				nutrient[i][j] += A[i][j];
			}
		}
	}
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N + 1][N + 1];
		nutrient = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				nutrient[i][j] = 5;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			tree.offer(new Tree(x, y, age));
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		for (int i = 0; i < K; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		bw.write(tree.size() + "\n");	// 살아남은 나무는 tree의 사이즈
		bw.flush();
		bw.close();
	}
}
