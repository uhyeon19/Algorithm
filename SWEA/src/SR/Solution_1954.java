package SR;

import java.io.*;

public class Solution_1954 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[] dx = {0, 1, 0, -1};	// ?™, ë¶?, ?„œ, ?‚¨
	static int[] dy = {1, 0, -1, 0};
	static int TC, N;	// ?…Œ?Š¤?Š¸ ì¼??´?Š¤ ?ˆ˜, N?¬ê¸°ì˜ ë°°ì—´
	static int[][] arr;	// ?‹¬?Œ½?´ë¥? ë§Œë“¤ ë°°ì—´
	static boolean[][] visit;	// ?´ë¯? ì§??‚˜?˜¨ ê¸¸ì¸ì§? ?™•?¸
	
	public static boolean isIn(int nx, int ny) {	// ë°°ì—´ ë°–ì„ ë²—ì–´?‚˜?Š” ê²½ìš°ë¥? ë§‰ì•„ì£¼ê¸° ?œ„?•¨
		if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for(int i = 0; i < TC; i++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visit = new boolean[N][N];
			int d = 0, x = 0, y = 0;	// ì²˜ìŒ ?‹œ?‘ ë°©í–¥?? 0 -> ?™ìª?, ì²˜ìŒ ?‹œ?‘ ?œ„ì¹? 0, 0
			
			sb.append("#").append(i + 1).append('\n');		// ?…Œ?Š¤?Š¸ì¼??´?Š¤ ë²ˆí˜¸
			int cnt = 1;		// 1ë²ˆë??„° ?¬ê¸? ?‚¤?š°ë©? ?‚½?…
			while(true) {
				arr[x][y] = cnt;	// ?˜„?¬ ?œ„ì¹˜ì— cnt ?‚½?…
				visit[x][y] = true;	// ?˜„?¬ ?œ„ì¹˜ì— ?‚½?…?–ˆ?œ¼?‹ˆ visit?„ trueë¡?!
				int nx = x + dx[d];	// ?‹¤?Œ xì¢Œí‘œ
				int ny = y + dy[d];	// ?‹¤?Œ yì¢Œí‘œ
				if(isIn(nx, ny) && !visit[nx][ny]) {	// ë²”ìœ„ ì²´í¬, ë°©ë¬¸ ì²´í¬
					x = nx;		// ë²”ìœ„ ?•ˆ?´ë©? ë°©ë¬¸?•˜ì§? ?•Š?? ê³³ì´?¼ë©? ?‹¤?Œ ì¢Œí‘œë¡? ?´?™
					y = ny;
					cnt++;		// ?´?™?•œ ê³³ì— ?„£?„ cnt ?ƒ?Š¹
				} else {
					if(cnt == N * N) break;	// cntê°? ?´ë¯? N * Nê¹Œì? ???„œ ?‹¤ ?‚½?… ?›„ ë©ˆì¶¤
					d = (d + 1) % 4;	// ë²”ìœ„ê°? ?•„?‹ˆê±°ë‚˜ ë°©ë¬¸?•œ ê³³ì´?¼ë©? ë¨¸ë¦¬ ?Œë¦¬ê¸°
				}
			}
			
			for(int k = 0; k < N; k++) {
				for(int j = 0; j < N; j++) {
					sb.append(arr[k][j]).append(' ');	// ì¶œë ¥ ?˜•?‹ ì§?ì¼œì„œ stringbuilder?— ?‚½?…
				}
				sb.append('\n');
			}
		}
		
		System.out.println(sb.toString());	// ì¶œë ¥
		
	}
}
