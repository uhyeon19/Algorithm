package SR;

import java.io.*;
import java.util.*;

public class BOJ14503 {
    static int []dx = {-1, 0, 1, 0};
    static int []dy = {0, 1, 0, -1};
    static int N, M, x, y, d, ans = 0;
    static int [][]arr;

    public static void solve(){
        boolean exe = true;
        while(exe) {
            if (arr[x][y] == 0) {
                ans++;
                arr[x][y] = 2;
            }
            boolean check = false;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && arr[nx][ny] == 0) {
                    check = true;
                    break;
                }
            }

            if(check){
                d = d - 1 < 0 ? 3 : d - 1;
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(nx >= 0 && ny >= 0 && nx < N && ny < M && arr[nx][ny] == 0){
                    x = x + dx[d];
                    y = y + dy[d];
                }
            } else {
                int backStep = (d + 2) % 4;
                x = x + dx[backStep];
                y = y + dy[backStep];
                if(x < 0 || y < 0 || x >= N || y >= M || arr[x][y] == 1) exe = false;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int k = 0; k < M; k++){
                arr[i][k] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
    }
}
