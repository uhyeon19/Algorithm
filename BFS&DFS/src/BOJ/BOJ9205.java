package BOJ;

import java.util.*;
import java.io.*;

public class BOJ9205 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int TC, N;
	static List<Point> node;
	static List<List<Integer>> graph;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 맨해튼 거리
    public static int Manhattan(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
    
    public static boolean BFS() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
 
        boolean[] visited = new boolean[N + 2];
        visited[0] = true;
 
        while (!q.isEmpty()) {
            int current = q.poll();
 
            if (current == N + 1) {
                return true;
            }
 
            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
 
        return false;
    }
	
	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			node = new ArrayList<>();
			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				node.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			graph = new ArrayList<>();
			for (int i = 0; i < N + 2; i++) {
				graph.add(new ArrayList<>());
			}

			// 맨해튼 거리 1000m이하를 만족하는 두 정점을 찾아 양방향 그래프 설정
			for (int i = 0; i < N + 2; i++) {
				for (int k = i + 1; k < N + 2; k++) {
					if (Manhattan(node.get(i), node.get(k)) <= 1000) {
                        graph.get(i).add(k);
                        graph.get(k).add(i);
                    }
				}
			}
			
			sb.append((BFS() ? "happy" : "sad") + '\n');
		}
		System.out.print(sb.toString());
	}
}
