package BOJ;

import java.io.*;
import java.util.*;

public class BOJ6593 {
	static int L, R, C, visit[][][];
	static char arr[][][];
	static int[] dl = { 0, 0, 0, 0, 1, -1 };
	static int[] dr = { -1, 0, 1, 0, 0, 0 };
	static int[] dc = { 0, 1, 0, -1, 0, 0 };
	static Queue<Node> q;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class Node {
		int l, r, c;

		Node(int l, int r, int c) {
			this.l = l;
			this.r = r;
			this.c = c;
		}
	}
	
	public static boolean isIn(int nl, int nr, int nc) {
		if (nl < 0 || nr < 0 || nc < 0 || nl >= L || nr >= R || nc >= C) {
			return false;
		}
		return true;
	}
	
	public static void BFS() {
		while(!q.isEmpty()) {
			Node n = q.poll();
			for(int i = 0; i < 6; i++) {
				int nl = n.l + dl[i];
				int nr = n.r + dr[i];
				int nc = n.c + dc[i];
				
				if(isIn(nl, nr, nc) && arr[nl][nr][nc] == 'E') { 
					sb.append("Escaped in ").append(visit[n.l][n.r][n.c]).append(" minute(s).\n");
					return;
				}
				if (isIn(nl, nr, nc) && arr[nl][nr][nc] == '.' && visit[nl][nr][nc] == 0) { 
					visit[nl][nr][nc] = visit[n.l][n.r][n.c] + 1;
					q.offer(new Node(nl, nr, nc));
				}
			}
		}
		sb.append("Trapped!\n");
	}

	public static void main(String[] args) throws IOException {
		while(true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());			
			if(L == 0 && R == 0 && C == 0) break;
			
			q = new LinkedList<>();
			arr = new char[L][R][C];
			visit = new int[L][R][C];
			for(int l = 0; l < L; l++) {
				for(int r = 0; r < R; r++) {
					String str = br.readLine();
					for(int c = 0; c < C; c++) {
						arr[l][r][c] = str.charAt(c);
						if(str.charAt(c) == 'S')	{
							visit[l][r][c] = 1;
							q.offer(new Node(l, r, c));
						}
					}
				}
				br.readLine();
			}
			BFS();
		}
		System.out.println(sb.toString());
	}
}
