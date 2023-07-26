package SR;

import java.util.*;

public class BOJ1655 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            int input = sc.nextInt();

            if(pq1.size() == pq2.size()) pq2.offer(input);
            else pq1.offer(input);

            if(!pq1.isEmpty() && !pq2.isEmpty()) {
                if(pq1.peek() < pq2.peek()) {
                    int tmp = pq1.poll();
                    pq1.offer(pq2.poll());
                    pq2.offer(tmp);
                }
            }

            sb.append(pq2.peek()).append('\n');
        }
        System.out.println(sb.toString());
    }
}