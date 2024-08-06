package BOJ;

import java.io.*;
import java.util.*;

public class BOJ9466 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuffer sb = new StringBuffer();
    static int T, N, answer = 0;
    static int[] arr;
    static boolean[] isVisited;
    static boolean[] done;

    static public void main(String[] args) throws IOException {
        init();
        System.out.print(sb.toString());
    }

    static void init() throws IOException {
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            arr = new int[N + 1];
            isVisited = new boolean[N + 1];
            done = new boolean[N + 1];
            answer = 0;
            for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
            for(int i = 1; i <= N; i++) {
                if(!done[i]) dfs(i);
            }
            sb.append(N - answer + "\n");
        }
    }

    static void dfs(int idx) {
        if(done[idx]) return;
        // 이미 방문했던 곳이라면 무조건 1명의 구성원이 추가된다
        if(isVisited[idx]) {
            done[idx] = true;
            answer++;
        }
        isVisited[idx] = true;
        dfs(arr[idx]);
        done[idx] = true;
        isVisited[idx] = false;
    }
}
