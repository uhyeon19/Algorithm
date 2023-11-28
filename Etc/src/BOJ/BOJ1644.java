package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1644 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static boolean isPrime[];
    static List<Integer> primeList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        setIsPrime();
        getPrime();
        System.out.println(twoPointer());
    }

    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        isPrime = new boolean[N + 1];
    }

    /**
     * 에라토스테네스의 체
     * isPrime 만들기 true/false 만들기
     * isPrime이 false라면 소수이다.
     */
    static void setIsPrime() {
        isPrime[0] = isPrime[1] = true;
        for(int i = 2; i * i <= N; i++) {
            if(isPrime[i]) {
                continue;
            }
            for(int k = i + i; k <= N; k += i) {
                isPrime[k] = true;
            }
        }
    }

    static void getPrime() {
        for(int i = 2; i <= N; i++) {
            if(!isPrime[i]) primeList.add(i);
        }
    }

    static int twoPointer() {
        int right = 0, left = 0, sum = 2, ans = 0;
        while(left < primeList.size() && right < primeList.size()) {
            if(sum == N) {
                ans++;
                sum -= primeList.get(left);
                left++;
            } else if(sum > N) {
                sum -= primeList.get(left);
                left++;
            } else {
                right++;
                if(right >= primeList.size()) break;
                sum += primeList.get(right);
            }
        }
        return ans;
    }
}
