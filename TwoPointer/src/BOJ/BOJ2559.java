package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2559 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int[] sumArr;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        prefixSum();
//        slidingWindow();
//        twoPointer();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        sumArr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sumArr[i] = sumArr[i - 1] + arr[i];
        }
    }

    static void prefixSum() {
        int max = Integer.MIN_VALUE;
        for (int i = K; i <= N; i++) {
            int rangeSum = sumArr[i] - sumArr[i - K];
            max = Integer.max(max, rangeSum);
        }
        System.out.println(max);
    }

    static void slidingWindow() {
        int i = 1, sum = 0;
        while (i <= K) sum += arr[i++];
        int max = sum;
        while (i <= N) {
            sum += arr[i] - arr[i - K];
            max = Integer.max(max, sum);
            i++;
        }
        System.out.println(max);
    }

    static void twoPointer() {
        int sum = 0;
        for (int i = 1; i <= K; i++) sum += arr[i];
        int max = sum;
        for (int right = K + 1, left = 1; right <= N; right++, left++) {
            sum += arr[right] - arr[left];
            max = Integer.max(max, sum);
        }
        System.out.println(max);
    }
}
