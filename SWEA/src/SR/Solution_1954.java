package SR;

import java.io.*;

public class Solution_1954 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[] dx = {0, 1, 0, -1};	// ?, λΆ?, ?, ?¨
	static int[] dy = {1, 0, -1, 0};
	static int TC, N;	// ??€?Έ μΌ??΄?€ ?, N?¬κΈ°μ λ°°μ΄
	static int[][] arr;	// ?¬?½?΄λ₯? λ§λ€ λ°°μ΄
	static boolean[][] visit;	// ?΄λ―? μ§???¨ κΈΈμΈμ§? ??Έ
	
	public static boolean isIn(int nx, int ny) {	// λ°°μ΄ λ°μ λ²μ΄?? κ²½μ°λ₯? λ§μμ£ΌκΈ° ??¨
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
			int d = 0, x = 0, y = 0;	// μ²μ ?? λ°©ν₯?? 0 -> ?μͺ?, μ²μ ?? ?μΉ? 0, 0
			
			sb.append("#").append(i + 1).append('\n');		// ??€?ΈμΌ??΄?€ λ²νΈ
			int cnt = 1;		// 1λ²λ??° ?¬κΈ? ?€?°λ©? ?½?
			while(true) {
				arr[x][y] = cnt;	// ??¬ ?μΉμ cnt ?½?
				visit[x][y] = true;	// ??¬ ?μΉμ ?½???Ό? visit? trueλ‘?!
				int nx = x + dx[d];	// ?€? xμ’ν
				int ny = y + dy[d];	// ?€? yμ’ν
				if(isIn(nx, ny) && !visit[nx][ny]) {	// λ²μ μ²΄ν¬, λ°©λ¬Έ μ²΄ν¬
					x = nx;		// λ²μ ??΄λ©? λ°©λ¬Έ?μ§? ??? κ³³μ΄?Όλ©? ?€? μ’νλ‘? ?΄?
					y = ny;
					cnt++;		// ?΄?? κ³³μ ?£? cnt ??Ή
				} else {
					if(cnt == N * N) break;	// cntκ°? ?΄λ―? N * NκΉμ? ??? ?€ ?½? ? λ©μΆ€
					d = (d + 1) % 4;	// λ²μκ°? ??κ±°λ λ°©λ¬Έ? κ³³μ΄?Όλ©? λ¨Έλ¦¬ ?λ¦¬κΈ°
				}
			}
			
			for(int k = 0; k < N; k++) {
				for(int j = 0; j < N; j++) {
					sb.append(arr[k][j]).append(' ');	// μΆλ ₯ ?? μ§?μΌμ stringbuilder? ?½?
				}
				sb.append('\n');
			}
		}
		
		System.out.println(sb.toString());	// μΆλ ₯
		
	}
}
