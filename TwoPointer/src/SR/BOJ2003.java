package SR;

import java.io.*;
import java.util.*;

public class BOJ2003 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static List<Integer> arr = new ArrayList<>();
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            arr.add(Integer.parseInt(st.nextToken()));
        }

        int sum = 0, start = 0, end = 0, ans = 0;
        while(true){
            if(sum >= M) sum -= arr.get(start++);
            else if(end == N) break;
            else sum += arr.get(end++);

            if(sum == M) ans++;
        }

        System.out.println(ans);
    }
}