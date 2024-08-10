package BOJ;

import java.io.*;
import java.util.*;

public class BOJ16637 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, answer = Integer.MIN_VALUE;
    static ArrayList<Integer> no = new ArrayList<>();
    static ArrayList<Character> op = new ArrayList<>();

    static public void main(String[] args) throws IOException {
        init();
        backTracking(0, no.get(0));
        System.out.println(answer);
    }

    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        for(int i = 0; i < N; i++) {
            if(i % 2 == 0) no.add(str.charAt(i) - '0');
            else op.add(str.charAt(i));
        }
    }

    static void backTracking(int idx, int sum) {
        if(idx == op.size()) {
            answer = Integer.max(answer, sum);
            return;
        }

        // 괄호 치기
        backTracking(idx + 1, calculate(sum, no.get(idx + 1), op.get(idx)));
        // 괄호 안 치기
        if(idx + 2 <= no.size() - 1) backTracking(idx + 2, calculate(sum, calculate(no.get(idx + 1), no.get(idx + 2), op.get(idx + 1)), op.get(idx)));
    }

    static int calculate(int first, int second, char op) {
        if(op == '-') return first - second;
        else if(op == '+') return first + second;
        else return first * second;
    }
}
