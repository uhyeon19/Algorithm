package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1194 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static char map[][];
	static int N, M;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Point start;

	static class Point {
		int x, y, depth, key;	// key = bitmaskiing

		public Point(int x, int y, int depth, int key) {
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.key = key;
		}
	}

	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < M;
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int k = 0; k < M; k++) {
				map[i][k] = str.charAt(k);
				if (map[i][k] == '0') {
					start = new Point(i, k, 0, 0);
				}
			}
		}
	}

	public static int bfs(Point start) {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][64];  // 000000 ~ 111111 -> 2^6 = 64개의 경우의 수
        q.offer(start);
        visited[start.x][start.y][0] = true;
 
        while(!q.isEmpty()) {
            Point cur = q.poll();
            if(map[cur.x][cur.y] == '1') return cur.depth;	// 도착한 경우 현재의 depth가 정답
 
            for(int d = 0; d < 4; d++) {	// 4방 탐색
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
 
                if(!isIn(nx, ny)) continue;	// 배열 범위 밖일 경우
                if(visited[nx][ny][cur.key] || map[nx][ny] == '#') continue;	// 이미 왔던 길이거나 벽인 경우
                
                if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') { // 가려는 칸이 열쇠칸
                	// 1. 열쇠의 종류에 따라 1 쉬프트 연산 -> 주운 열쇠의 비트에 1을 표시하게 됨
                	// 즉, b를 먹었다면 000010이 된다
                    int next_key = 1 << (map[nx][ny] - 'a');
                    // 2. 새로운 열쇠의 정보를 현재 가지고 있는 key값과 or연산시 새로운 열쇠 정보를 기존의 key에 더해주는 연산이 됨
                    // 000001 | 000010 = 000011
                    next_key = cur.key | next_key;
                    
                    visited[nx][ny][next_key] = true;	// 방문 처리
                    q.offer(new Point(nx, ny, cur.depth + 1, next_key));	// BFS Queue 배열에 정보 담기
                }
                else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {		// 문을 마주했을 경우
                	// 해당 열쇠에 해당하는 비트가 1인지 확인 
                	// 만난 문의 종류에 따라 쉬프트 연산 시 000001 과 같이 만들어짐.
                	// 해당 비트의 값과 내 현재 key값을 and 연산하면 두 개가 모두 1이여야 1 반환
                	// 즉, 현재 내 키가 000011 이고 현재 문이 000010이라면 & 연산 시 000010이 반환된다.
                	// 2^6까지 중 2^2의 값이 맞는지 확인하면 문이 열린다.
                    if((cur.key & 1 << (map[nx][ny] - 'A')) == (int)Math.pow(2, map[nx][ny] - 'A')) {
                        visited[nx][ny][cur.key] = true;
                        q.offer(new Point(nx, ny, cur.depth + 1, cur.key));
                    }
                } else {	// 빈 공간일 경우
                    visited[nx][ny][cur.key] = true;
                    q.offer(new Point(nx, ny, cur.depth + 1, cur.key));
                }
            }
        }
        return -1;
	}

	public static void main(String[] args) throws IOException {
		init();
		bw.write(bfs(start) + "\n");
		bw.flush();
		bw.close();
	}
}
