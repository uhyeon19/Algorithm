package SR;

import java.io.*;
import java.util.*;

public class Solution_2112 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int arr[][], copyArr[][], T, D, W, K, num[], count;	// 테스트케이스, 필름 두께x, 가로크기y, 합격 기준
	static boolean isSelected[], flag;
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[D][W];
		copyArr = new int[D][W];
		isSelected = new boolean[D];
		flag = false;
		count = 0;
		for(int i = 0; i < D; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < W; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static boolean isPossible() {
		boolean isPossible[] = new boolean[W];
		for(int i = 0; i < W; i++) {
			int cur = copyArr[0][i];
			int cnt = 1;
			for(int k = 1; k < D; k++) {
				if(cur == copyArr[k][i]) {
					cnt++;
				} else {
					cur = copyArr[k][i];
					cnt = 1;
				}
				if(cnt == K) {
					isPossible[i] = true;
					break;
				}
			}
			if(!isPossible[i]) return false;
		}
		return true;
	}
	
	public static void comb(int cnt, int count) {
		if(flag) return;
		if(cnt == count) {
			int tmpCnt = 0;
			for(int i = 0; i < D; i++) {
				if(isSelected[i]) {
					for(int k = 0; k < W; k++) {						
						copyArr[i][k] = num[tmpCnt];
					}
					tmpCnt++;
				}
			}
			
			flag = isPossible();
			return;
		}
		
		for(int i = 0; i < 2; i++) {
			num[cnt] = i;
			comb(cnt + 1, count);
		}
	}
	
	public static void powerSet(int cnt) {
		if(flag) return;
		if (cnt == D) {
			count = 0;
			for(int i = 0; i < D; i++) {				
				if(isSelected[i]) {
					count++;
					if(count > K - 1) return;
				}
				copyArr[i] = arr[i].clone();
			}
			num = new int[count];
			comb(0, count);
			return;
		}
		
		isSelected[cnt] = false;
		powerSet(cnt + 1);
		isSelected[cnt] = true;
		powerSet(cnt + 1);
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			init();
			if(K == 1)
				bw.write("#" + t + " 0\n");
			else {
				powerSet(0);
				if(flag) bw.write("#" + t + " " + count + "\n");
				else bw.write("#" + t + " " + K + "\n");
			}
		}
		bw.flush();
		bw.close();
	}
}
