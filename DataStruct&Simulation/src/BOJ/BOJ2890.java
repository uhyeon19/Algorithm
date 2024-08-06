package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2890 {
    static int R, C;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> o2.locate - o1.locate);
    static int[] rankList = new int[9];

    static class Info {
        int no, locate;
        Info(int no, int locate) {
            this.no = no;
            this.locate = locate;
        }
    }

    static public void main(String[] args) throws IOException {
        init();
        answer();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int k = 1; k < C; k++) {
                char tmp = str.charAt(k);
                if(tmp != '.' && isNumber(tmp)) {
                    pq.offer(new Info(tmp - '0', k));
                    break;
                }
            }
        }
    }

    static void answer() {
        Info before = null;
        int rank = 0;
        while(!pq.isEmpty()) {
            Info current = pq.poll();
            if(before == null || before.locate != current.locate) rank++;
            rankList[current.no - 1] = rank;
            before = current;
        }
        for(int i = 0; i < 9; i++) {
            System.out.println(rankList[i]);
        }
    }

    static boolean isNumber(char c) {
        return c - '0' >= 1 && c - '0' <= 9;
    }
}
