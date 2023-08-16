package SR;

import java.util.*;
import java.io.*;

public class BOJ6987 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int arr[][], total;
	static int[] home = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
	static int[] away = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 };

	public static boolean solve(int game) {
		if (game == 15)	// 총 개임의 수는 위의 home과 away의 사이즈인 15이다.
			return true;
		
		// 내가 이기는 수
		// 내가 이기는 수가 상대 지는 수보다 크면 가능한 경우가 된다.
		if(arr[home[game]][0] > 0 && arr[away[game]][2] > 0) {	// 0번 인덱스 = 승리 수, 2번 인덱스 = 패배 수
			arr[home[game]][0]--;	// 내 승리 수 1 줄여주기
			arr[away[game]][2]--;	// 상대 패배 수 1 줄여주기
			if(solve(game + 1)) return true;	// 가능하다!! 다음 찾아줘!
			arr[home[game]][0]++;	// 상태 회복
			arr[away[game]][2]++;
		}
		
		// 상대가 이기는 수
		if(arr[home[game]][2] > 0 && arr[away[game]][0] > 0) {	// 0번 인덱스 = 승리 수, 2번 인덱스 = 패배 수
			arr[home[game]][2]--;	// 내 패배 수 1 줄여주기
			arr[away[game]][0]--;	// 상대 승리 수 1 줄여주기
			if(solve(game + 1)) return true;	// 가능하다!! 다음 찾아줘!
			arr[home[game]][2]++;	// 상태 회복
			arr[away[game]][0]++;
		}
		
		// 무승부의 수
		if(arr[home[game]][1] > 0 && arr[away[game]][1] > 0) {	// 1번 인덱스 = 무승부 수
			arr[home[game]][1]--;	// 내 무승부 수 1 줄여주기
			arr[away[game]][1]--;	// 상대 무승부 수 1 줄여주기
			if(solve(game + 1)) return true;	// 가능하다!! 다음 찾아줘!
			arr[home[game]][1]++;	// 상태 회복
			arr[away[game]][1]++;
		}
		
		return false;	// 15개의 게임을 잘 찾아가서 true로 가면 가능한 조합이지만, 아니라면 false가 떨어진다.
	}

	public static void init() throws IOException {
		arr = new int[6][3];
		st = new StringTokenizer(br.readLine());
		total = 0;
		for (int i = 0; i < 6; i++) {
			for (int k = 0; k < 3; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
				total += arr[i][k];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 4; i++) {
			init();
			if (total != 30) sb.append(0 + " "); 
			else {
				if (solve(0)) sb.append(1 + " ");
				else sb.append(0 + " ");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}