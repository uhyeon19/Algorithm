package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2146 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, no = 2;
    static int[][] arr;
    static int answer = Integer.MAX_VALUE;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Point {
        int x, y, depth;
        Point(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    static public void main(String[] args) throws IOException {
        init();
        setIslandNum();
        setBridge();
        System.out.println(answer);
    }

    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int k = 0; k < N; k++) {
                arr[i][k] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void setIslandNum() {
        isVisited = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            for(int k = 0; k < N; k++) {
                if(isVisited[i][k] || arr[i][k] == 0) continue;
                bfsIslandNum(new Point(i, k, 0));
                no++;
            }
        }
    }

    static void setBridge() {
        for(int i = 0; i < N; i++) {
            for(int k = 0; k < N; k++) {
                if(arr[i][k] < 2) continue;
                bfsBridge(new Point(i, k, 0), arr[i][k]);
            }
        }
    }

    static void bfsIslandNum(Point start) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(start);
        isVisited[start.x][start.y] = true;
        arr[start.x][start.y] = no;

        while(!q.isEmpty()) {
            Point p = q.poll();
            int nx, ny;
            for(int d = 0; d < 4; d++) {
                nx = p.x + dx[d];
                ny = p.y + dy[d];
                if(isIn(nx, ny) || isVisited[nx][ny] || arr[nx][ny] == 0) continue;
                arr[nx][ny] = no;
                isVisited[nx][ny] = true;
                q.offer(new Point(nx, ny, p.depth + 1));
            }
        }
    }

    static void bfsBridge(Point start, int number) {
        isVisited = new boolean[N][N];
        Queue<Point> q = new ArrayDeque<>();
        q.offer(start);
        isVisited[start.x][start.y] = true;

        while(!q.isEmpty()) {
            Point p = q.poll();
            if(arr[p.x][p.y] != 0 && arr[p.x][p.y] != number) answer = Integer.min(answer, p.depth - 1);
            if(p.depth > answer) break;
            int nx, ny;
            for(int d = 0; d < 4; d++) {
                nx = p.x + dx[d];
                ny = p.y + dy[d];
                if(isIn(nx, ny) || isVisited[nx][ny] || arr[nx][ny] == number) continue;
                isVisited[nx][ny] = true;
                q.offer(new Point(nx, ny, p.depth + 1));
            }
        }
    }

    static boolean isIn(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N;
    }
}
