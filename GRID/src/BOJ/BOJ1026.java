package BOJ;

import java.util.*;
import java.io.*;

public class BOJ1026 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int S, aArr[], bArr[];

    public static void main(String[] args) throws IOException {
        S = Integer.parseInt(br.readLine());
        aArr = new int[S];
        bArr = new int[S];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < S; i++) aArr[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < S; i++) bArr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(aArr);
        Arrays.sort(bArr);
        int ans = 0;
        for(int i = 0; i < S; i++){
            ans += aArr[i] * bArr[S - i - 1];
        }
        System.out.println(ans);
    }
}