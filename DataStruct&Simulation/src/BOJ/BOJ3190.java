package BOJ;

import java.io.*;
import java.util.*;

class Snake {
    int x;
    int y;

    Snake(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class BOJ3190 {
    static int N, appleCnt, L;
    static int [][]arr;     // 사과가 있는 곳 1
    static Map<Integer, String> changeDirection = new HashMap<>();
    static int x = 0, y = 0;
    static List<Snake> snake = new ArrayList<>();
    static int []dx = {-1, 0, 1, 0};
    static int []dy = {0, 1, 0, -1};
    // 상 -> 우 -> 하 -> 좌

    public static boolean isFin(int nx, int ny) {
        if(nx < 0 || ny < 0 || nx >= N || ny >= N){
            return true;
        }
        for (Snake s : snake) {
            if (nx == s.x && ny == s.y)
                return true;
        }
        return false;
    }

    public static void solution(){
        int d = 1;
        snake.add(new Snake(0, 0));
        int ans = 0;
        while(true){
            ans++;
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(isFin(nx, ny)) break;

            snake.add(new Snake(nx, ny));
            if(arr[nx][ny] == 1) {
                arr[nx][ny] = 0;
            } else {
                snake.remove(0);
            }

            if(changeDirection.containsKey(ans)){
                if(changeDirection.get(ans).equals("D")) d = d + 1 == 4 ? 0 : d + 1;
                else d = d - 1 == -1 ? 3 : d - 1;
            }

            x = nx;
            y = ny;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        st = new StringTokenizer(br.readLine());
        appleCnt = Integer.parseInt(st.nextToken());
        for(int i = 0; i < appleCnt; i++){
            st =  new StringTokenizer(br.readLine());
            int tmpCol = Integer.parseInt(st.nextToken());
            int tmpRow = Integer.parseInt(st.nextToken());
            arr[tmpCol - 1][tmpRow - 1] = 1;
        }
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        for(int i = 0; i < L; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            changeDirection.put(time, direction);
        }

        solution();
    }
}
