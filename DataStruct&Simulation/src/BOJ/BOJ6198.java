package BOJ;

import java.io.*;
import java.util.*;

public class BOJ6198 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static long answer = 0;
    static Stack<Integer> stack = new Stack<>();

    static public void main(String[] args) throws IOException {
        init();
        System.out.println(answer);
    }

    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            solve(Integer.parseInt(br.readLine()));
        }
    }

    static void solve(int height) {
        while(!stack.isEmpty()) {
            if(stack.peek() <= height) stack.pop();
            else break;
        }
        answer += stack.size();
        stack.push(height);
    }
}
