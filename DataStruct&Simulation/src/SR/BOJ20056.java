package SR;

import java.util.*;
import java.io.*;

public class BOJ20056 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	static int N, M, K; // 격자 크기, 파이어볼 개수, 이동 수
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static List<Fireball> originList;
	static Queue<Fireball>[][] grid;

	static class Fireball {
		int x, y, m, d, s;

		public Fireball(int x, int y, int m, int d, int s) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}

	public static void move() {
		for (Fireball f : originList) {
			f.x = (N + f.x + dx[f.d] * (f.s % N)) % N;
			f.y = (N + f.y + dy[f.d] * (f.s % N)) % N;

			grid[f.x][f.y].offer(f);
		}
	}

	public static void combineAndDivie() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j].size() >= 2) {
					int m_sum = 0, s_sum = 0;
					int cnt_sum = grid[i][j].size();
					boolean odd = true, even = true;

					while (!grid[i][j].isEmpty()) {
						Fireball f = grid[i][j].poll();
						m_sum += f.m;
						s_sum += f.s;

						if (f.d % 2 == 0) {
							odd = false;
						} else {
							even = false;
						}
						originList.remove(f);
					}

					int nm = m_sum / 5;
					if (nm == 0)
						continue;

					int ns = s_sum / cnt_sum;
					if (odd | even) { // 홀수 or 짝수
						for (int k = 0; k < 8; k += 2) { // 방향 0, 2 ,4, 6
							originList.add(new Fireball(i, j, nm, k, ns));
						}
					} else {
						for (int k = 1; k < 8; k += 2) { // 1, 3, 5, 7
							originList.add(new Fireball(i, j, nm, k, ns));
						}
					}
				} else {
					grid[i][j].clear();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		originList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			originList.add(new Fireball(x, y, m, d, s));
		}
		grid = new Queue[N][N];
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				grid[i][k] = new ArrayDeque<>();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		for (int i = 0; i < K; i++) {
			move();
			combineAndDivie();
		}
		int answer = 0;
		for (Fireball f : originList) {
			answer += f.m;
		}
		System.out.println(answer);
	}
}