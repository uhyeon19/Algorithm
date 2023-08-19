package SR;

import java.io.*;

public class BOJ1789 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long N, S;

    public static void main(String[] args) throws IOException {
        S = Long.parseLong(br.readLine());
        long sum = 0;
        for(int i = 1; ; i++) {
            sum += i;
            N = i;
            if(sum > S) {
                N--;
                break;
            }
        }
        System.out.println(N);
    }
}
