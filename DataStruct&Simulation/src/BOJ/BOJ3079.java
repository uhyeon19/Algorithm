package BOJ;

import java.io.*;
import java.util.*;

public class BOJ3079 {
    static int N, M;
    static int[] time;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static public void main(String[] args) throws IOException {
        init();
        System.out.print(answer());
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        time = new int[N];
        for(int i = 0; i < N; i++) {
            time[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(time);
    }

    static long answer() {
        // 왼쪽 0, 오른쪽 가장 큰 시간의 검색대에서 N명이 모두 검사받는 경우
        long left = 0, right = time[N - 1] * (long)M, answer = 0;
        // 이분탐색
        while(left <= right) {
            // 중앙값, 완료 시 시간
            long mid = (left + right) / 2, complete = 0;
            // 현재의 중앙값 / t를 모두 더한다면 검사 가능한 인원 수가 나온다.
            for(int i = 0; i < N; i++) {
                complete += mid / time[i];
                // complete가 M보다 크다면 멈춰줄 것: 아닐 시 overflow가 발생 -> 결과가 다르게 나옴
                if(complete > M) break;
            }
            // 검사 가능한 인원 수가 총 인원 수 보다 작다면 검사 불가능: left를 늘림(시간 늘림)
            if(complete < M) left = mid + 1;
            else {
                // 검사 가능한 인원 수가 총 인원 수보다 크다면 검사 가능: right를 줄이고 더 나은 값이 있나 확인
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}
