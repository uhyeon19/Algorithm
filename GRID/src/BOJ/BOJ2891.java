package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2891 {
    static int N, S, R;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] list;

    static public void main(String[] args) throws IOException {
        init();
        answer();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        list = new int[N];
        st = new StringTokenizer(br.readLine());
        // list = 1 : 카약 필요, list = 2 : 카약 하나 남음
        for(int i = 0; i < S; i++) list[Integer.parseInt(st.nextToken()) - 1] = 1;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < R; i++) {
            int no = Integer.parseInt(st.nextToken()) - 1;
            if(list[no] == 1) list[no] = 0;
            else list[no] = 2;
        }
    }

    static void answer() {
        int answer = 0;
        for(int i = 0; i < N; i++) {
            if(list[i] == 2) {
                if(i != 0 && list[i - 1] == 1) {
                    list[i] = 0;
                    list[i - 1] = 0;
                    continue;
                }
                if(i + 1 != N && list[i + 1] == 1) {
                    list[i] = 0;
                    list[i + 1] = 0;
                }
            }
        }
        for(int i = 0; i < N; i++) {
            if(list[i] == 1) answer++;
        }
        System.out.print(answer);
    }
}
