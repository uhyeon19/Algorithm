package SR;

import java.io.*;
import java.util.*;

public class BOJ13305 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static long eArr[], vArr[], ans = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        eArr = new long[N - 1];
        vArr = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N - 1; i++) eArr[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        vArr[0] = Long.parseLong(st.nextToken());
        for(int i = 1; i < N; i++) {
            Long v = Long.parseLong(st.nextToken());
            if(vArr[i - 1] <= v) vArr[i] = vArr[i - 1];
            else vArr[i] = v;
        }
        for(int i = 0; i < N - 1; i++){
            ans += eArr[i] * vArr[i];
        }
        System.out.println(ans);
    }
}
