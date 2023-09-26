package BOJ;

import java.util.*;
import java.io.*;

public class BOJ17144 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int arr[][], spread[][], R, C, T, ans = 0;
    static List<Point> airCleaner = new ArrayList<>();
    static Queue<Point> dirty = new LinkedList<>();
    static int []dx = {1, -1, 0, 0};
    static int []dy = {0, 0, 1, -1};

    static boolean[][] visited;
    static int []dxFirst = {0, -1, 0, 1};
    static int []dyFirst = {1, 0, -1, 0};
    static int []dxSecond = {0, 1, 0, -1};
    static int []dySecond = {1, 0, -1, 0};

    static class Point {
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isIn(int nx, int ny) {
        if(nx < 0 || ny < 0 || nx >= R || ny >= C) return false;
        return true;
    }

    public static void cleaning(Point airCleaner, int[] dx, int[] dy){
        Point p = airCleaner;
        int curD = 0;
        int x = p.x + dx[curD];
        int y = p.y + dy[curD];
        Queue<Integer> q = new LinkedList<>();
        q.offer(arr[x][y]);
        arr[x][y] = 0;

        while(true){
            int nx = x + dx[curD];
            int ny = y + dy[curD];
            if(isIn(nx, ny)) {
                if(arr[nx][ny] == -1){
                    q.poll();
                    visited[nx][ny] = true;
                    break;
                }
                q.offer(arr[nx][ny]);
                arr[nx][ny] = q.poll();
                visited[x][y] = true;
                x = nx;
                y = ny;
            } else {
                curD = (curD + 1) % 4;
            }
        }
    }

    public static void spread(){
        spread = new int[R][C];
        spread[airCleaner.get(0).x][airCleaner.get(0).y] = -1;
        spread[airCleaner.get(1).x][airCleaner.get(1).y] = -1;
        while(!dirty.isEmpty()){
            Point p = dirty.poll();
            int cnt = 0;
            for(int d = 0; d < 4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                if(isIn(nx, ny) && arr[nx][ny] != -1) {
                    cnt++;
                    spread[nx][ny] += arr[p.x][p.y] / 5;
                }
            }
            spread[p.x][p.y] += arr[p.x][p.y] - (arr[p.x][p.y] / 5 * cnt);
        }

        for(int i = 0; i < R; i++){
            arr[i] = spread[i].clone();
        }
    }

    public static void dirtyInit(){
        for(int i = 0; i < R; i++){
            for(int k = 0; k < C; k++){
                if(arr[i][k] > 0) {
                    dirty.offer(new Point(i, k));
                }
            }
        }
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int k = 0; k < C; k++){
                arr[i][k] = Integer.parseInt(st.nextToken());
                if(arr[i][k] == -1) {
                    airCleaner.add(new Point(i, k));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        for(int t = 0; t < T; t++){
            dirtyInit();
            spread();
            cleaning(airCleaner.get(0), dxFirst, dyFirst);
            cleaning(airCleaner.get(1), dxSecond, dySecond);
        }

        for(int i = 0; i < R; i++){
            for(int k = 0; k < C; k++){
                if(arr[i][k] > 0) ans += arr[i][k];
            }
        }
        System.out.println(ans);
    }
}
