package SR;

import java.io.*;
import java.util.*;
 
public class BOJ1197 {
    static Edge[] edgeList;
    static int V, E, parents[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
     
    static class Edge implements Comparable<Edge> {
        int from, to, weight;
 
        public Edge(int from, int to, int weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
 
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
     
    static void makeSet() {
        parents = new int[V + 1];
        for(int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }
     
    static int findSet(int a) {
        if(parents[a] == a) return a;
        return parents[a] = findSet(parents[a]);    // path 압축
    }
     
    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if(aRoot == bRoot) return false;    // 싸이클 발생 의미로 해석!
        // 치우친 형태가 될 수 있기 때문에 나중에는 랭크(깊이) 관리를 해주는게 좋지만 지금은 일단 이렇게 사용
        parents[bRoot] = aRoot;
        return true;
    }
     
    public static void main(String[] args) throws IOException {
    	st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
         
        edgeList = new Edge[E];
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(from, to, weight);           
        }
         
        // 간선리스트를 가중치 기준 오름차순 정렬
        Arrays.sort(edgeList);
         
        // V개의 정점으로 make-set 작업
        makeSet();
         
        long result = 0;    // MST 비용 누적
        int count = 0;  // 연결된 간선 개수
        for(Edge edge : edgeList) {
            if(union(edge.from, edge.to)) {
                result += edge.weight;
                if(++count == V - 1) {
                    break;
                }
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}