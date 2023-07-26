package SR;

import java.io.*;
import java.util.*;

public class BOJ5430 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static StringBuilder ans = new StringBuilder();

    public static String solve() throws IOException {
        char[] p = br.readLine().toCharArray();
        int n = Integer.parseInt(br.readLine());
        String arrStr = br.readLine();

        Deque<Integer> deque = new LinkedList<>();
        for (String s : arrStr.substring(1, arrStr.length() - 1).split(",")) {
            if (!s.equals("")) {
                deque.add(Integer.parseInt(s));
            }
        }

        int direction = 1;
        for(int i = 0; i < p.length; i++) {
            if(p[i] == 'R') {
                direction *= -1;
            } else {
                if(deque.size() == 0) {
                    return "error\n";
                }
                if(direction == 1) {
                    deque.pollFirst();
                } else {
                    deque.pollLast();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(!deque.isEmpty()) {
            if(direction == 1) {
                sb.append(deque.pollFirst());
            } else {
                sb.append(deque.pollLast());
            }
            if(deque.size() != 0) {
                sb.append(',');
            }
        }
        sb.append(']').append('\n');

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());
        for (int i = 0; i < TC; i++) {
            ans.append(solve());
        }

        System.out.println(ans.toString());
    }
}