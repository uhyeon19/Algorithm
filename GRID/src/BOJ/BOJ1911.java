package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1911 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, L, answer = 0, max = 0;
    static PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> o1.start == o2.start ? o1.end - o2.end : o1.start - o2.start);

    static class Info {
        int start, end;
        Info(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }


    static public void main(String[] args) throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            pq.offer(new Info(first, second));
        }
    }

    static void solve() {
        while(!pq.isEmpty()) {
            Info cur = pq.poll();
            if(cur.end < max) continue;
            int minus = cur.end - Integer.max(max, cur.start);
            int use = (minus / L) + (minus % L == 0 ? 0 : 1);
            answer += use;
            max = cur.end + (L * use - minus);
        }
        System.out.println(answer);
    }
}
