package BOJ;

import java.io.*;
import java.util.*;

public class BOJ15662 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T, K;
    static Wheel[] wheels;

    static class Wheel {
        int[] status = new int[8];
        Wheel(String str) {
            for(int i = 0; i < str.length(); i++) status[i] = str.charAt(i) - '0';
        }
        void turning(int dir) {
            if(dir == 1) {
                int tmp = status[7];
                for(int i = 7; i > 0; i--) status[i] = status[i - 1];
                status[0] = tmp;
            } else {
                int tmp = status[0];
                for(int i = 1; i < 8; i++) status[i - 1] = status[i];
                status[7] = tmp;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        T = Integer.parseInt(br.readLine());
        wheels = new Wheel[T];
        for(int i = 0; i < T; i++) {
            String str = br.readLine();
            wheels[i] = new Wheel(str);
        }
        K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            simulation(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    static void simulation(int num, int dir) {
        int curDir = dir;
        wheels[num - 1].turning(dir);
        for(int i = num - 2; i >= 0; i--) {
            if(wheels[i + 1].status[6 + curDir] == wheels[i].status[2]) break;
            curDir *= -1;
            wheels[i].turning(curDir);
        }
        curDir = dir;
        for(int i = num; i < T; i++) {
            if(wheels[i - 1].status[2 + curDir] == wheels[i].status[6]) break;
            curDir *= -1;
            wheels[i].turning(curDir);
        }
    }

    static void solve() {
        int answer = 0;
        for(int i = 0; i < T; i++) if(wheels[i].status[0] == 1) answer++;
        System.out.println(answer);
    }
}
