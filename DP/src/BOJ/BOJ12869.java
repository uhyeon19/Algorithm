package BOJ;

import java.io.*;
import java.util.*;

public class BOJ12869 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, hp[];
	static boolean dp[][][];
	static List<int[]> type = new ArrayList<>();

	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		hp = new int[3];
		dp = new boolean[61][61][61];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			hp[i] = Integer.parseInt(st.nextToken());
		}
	}

	public static void type() {
		if (N == 1) {
			type.add(new int[] { 9, 0, 0 });
		} else if (N == 2) {
			type.add(new int[] { 9, 3, 0 });
			type.add(new int[] { 3, 9, 0 });
		} else if (N == 3) {
			type.add(new int[] { 9, 3, 1 });
			type.add(new int[] { 9, 1, 3 });
			type.add(new int[] { 3, 9, 1 });
			type.add(new int[] { 3, 1, 9 });
			type.add(new int[] { 1, 9, 3 });
			type.add(new int[] { 1, 3, 9 });
		}
	}
	
	public static boolean isFin(int hp[]) {
		for(int i = 0; i < 3; i++) {
			if(hp[i] != 0) return false;
		}
		return true;
	}

	public static int bfs(int hp[]) {
		Queue<int[]> q = new ArrayDeque<>();
		if(isFin(hp)) return 0;
		int firstHp[] = hp;
		q.offer(firstHp);
		int cnt = 1;
		dp[firstHp[0]][firstHp[1]][firstHp[2]] = true;

		int curHp[] = new int[3];
		while (!q.isEmpty()) {
			int size = q.size();
			while(--size >= 0) {				
				curHp = q.poll();
				for (int i = 0; i < type.size(); i++) {
					int nextHp[] = new int[3];
					int damage[] = type.get(i);
					for (int k = 0; k < 3; k++) {
						nextHp[k] = curHp[k] - damage[k] <= 0 ? 0 : curHp[k] - damage[k];
					}
					if(isFin(nextHp)) return cnt;
					if(dp[nextHp[0]][nextHp[1]][nextHp[2]] != true) {
						dp[nextHp[0]][nextHp[1]][nextHp[2]] = true;
						q.offer(nextHp);
					}
				}
			}
			cnt++;
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		init();
		type();
		bw.write(bfs(hp) + "\n");
		bw.flush();
		bw.close();
	}
}