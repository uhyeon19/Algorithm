package SR.study;

import java.io.*;
import java.util.*;

public class PrimAlgorithm {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, T;
	static double arr[][], E, ans;
	static Vertex graph[];
	static Point p[];

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Vertex implements Comparable<Vertex> {
		int no;
		double weight;

		public Vertex(int no, double weight) {
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Double.compare(this.weight, o.weight);
		}
	}

	public static void getDistance() {
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				long xDistance = Math.abs(p[i].x - p[k].x);
				long yDistance = Math.abs(p[i].y - p[k].y);
				arr[i][k] = ((xDistance * xDistance + yDistance * yDistance) * E);
			}
		}
	}

	public static void prim() {
		boolean[] visited = new boolean[N];
		double[] minEdge = new double[N];
		PriorityQueue<Vertex> pQueue = new PriorityQueue<>();

		Arrays.fill(minEdge, Double.MAX_VALUE);
		minEdge[0] = 0;
		pQueue.offer(new Vertex(0, minEdge[0]));

		double min = 0;
		int minVertex = 0, cnt = 0;
		while (!pQueue.isEmpty()) {
			Vertex cur = pQueue.poll();
			minVertex = cur.no;
			min = cur.weight;
			if (visited[minVertex]) continue;

			visited[minVertex] = true;
			ans += min;
			if (++cnt == N) break;

			for (int i = 0; i < N; i++) {
				if (!visited[i] && arr[minVertex][i] != 0 && minEdge[i] > arr[minVertex][i]) {
					minEdge[i] = arr[minVertex][i];
					pQueue.offer(new Vertex(i, minEdge[i]));
				}
			}
		}
	}

	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		graph = new Vertex[N];
		p = new Point[N];
		arr = new double[N][N];
		ans = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			p[i] = new Point(Integer.parseInt(st.nextToken()), 0);
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			p[i].y = Integer.parseInt(st.nextToken());
		}

		E = Double.parseDouble(br.readLine());
		getDistance();
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init();
			prim();
			bw.write("#" + tc + " " + Math.round(ans) + "\n");
		}
		bw.flush();
		bw.close();
	}
}
