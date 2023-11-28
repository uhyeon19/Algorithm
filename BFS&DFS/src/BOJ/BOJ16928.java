package BOJ;

import java.io.*;
import java.util.*;

public class BOJ16928 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static int N, M, ans, arr[];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(bfs());
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[101];
        visited = new boolean[101];
        for(int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[u] = v;
        }
    }

    static int bfs() {
        int ans = 1;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        visited[1] = true;

        while(!q.isEmpty()) {
            int size = q.size();
            while(--size >= 0) {
                int cur = q.poll();
                for(int i = 1; i <= 6; i++) {
                    int nx = cur + i;
                    if(nx == 100) return ans;
                    if(nx > 100) continue;
                    if(arr[nx] != 0 && !visited[arr[nx]]) {
                        visited[arr[nx]] = true;
                        q.offer(arr[nx]);
                    } else if(arr[nx] == 0 && !visited[nx]) {
                        visited[nx] = true;
                        q.offer(nx);
                    }
                }
            }
            ans++;
        }
        return 0;
    }
}
