package BOJ;

import java.io.*;
import java.util.*;

public class BOJ14889 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static int min = Integer.MAX_VALUE, N, arr[][];
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        init();
        combination(0, 0);
        System.out.println(min);
    }

    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        isSelected = new boolean[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int k = 0; k < N; k++) {
                arr[i][k] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void combination(int idx, int cnt) {
        if(cnt == N / 2) {
            diff();
            if(min == 0) {
                System.out.println(min);
                System.exit(0);
            }
            return;
        }
        for(int i = idx; i < N; i++) {
            if(!isSelected[i]) {
                isSelected[i] = true;
                combination(i + 1, cnt + 1);
                isSelected[i] = false;
            }
        }
    }

    static void diff() {
        int start = 0;
        int link = 0;
        for(int i = 0; i < N - 1; i++) {
            for(int k = i + 1; k < N; k++) {
                if(isSelected[i] && isSelected[k]) {
                    start += arr[i][k];
                    start += arr[k][i];
                } else if(!isSelected[i] && !isSelected[k]) {
                    link += arr[i][k];
                    link += arr[k][i];
                }
            }
        }
        min = Integer.min(Math.abs(start - link), min);
    }
}
