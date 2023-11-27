package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1929 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st = null;
    static int start, end;
    static boolean eratosthenes[];

    public static void main(String[] args) throws IOException {
        init();
        isPrime();
        getPrime();
        System.out.println(sb.toString());
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        eratosthenes = new boolean[end + 1];
    }

    static void isPrime() {
        eratosthenes[0] = eratosthenes[1] = true;
        for(int i = 2; i * i <= end; i++) {
            if(eratosthenes[i]) {
                continue;
            }
            for(int k = i + i; k <= end; k += i) {
                eratosthenes[k] = true;
            }
        }
    }

    static void getPrime() {
        for(int i = start; i <= end; i++) {
            if(!eratosthenes[i]) {
                sb.append(i + "\n");
            }
        }
    }
}
