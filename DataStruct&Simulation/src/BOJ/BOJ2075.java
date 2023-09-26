package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2075 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                pq.offer(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i = 0; i < N - 1; i++){
            pq.poll();
        }
        System.out.println(pq.poll());
    }
}
