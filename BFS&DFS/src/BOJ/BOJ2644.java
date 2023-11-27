package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2644 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int N, M, A, B, ans = -1;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static int[]dx = { -1, 0, 1, 0 };
	static int[]dy = { 0, 1, 0, -1 };
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		init();
        DFS(list, visited, A, B, 0);
        System.out.println(ans);
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
		int x, y;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			list.get(x).add(y);
			list.get(y).add(x);
		}
	}
	
	public static void DFS(ArrayList<ArrayList<Integer>> list, boolean[] visited, int cur, int end, int cnt) {
        visited[cur] = true;
 
        for (int i : list.get(cur)) {
            if (!visited[i]) {
                if (i == end) {
                    ans = cnt + 1;
                    return;
                }
 
                DFS(list, visited, i, end, cnt + 1);
            }
        }
    }
}
