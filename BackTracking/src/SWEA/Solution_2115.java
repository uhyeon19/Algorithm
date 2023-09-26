package SWEA;

import java.util.*;
import java.io.*;

// 2명의 일꾼의 공간을 구하는데 사용할 수 있는 방안 2개
// 방안 1. 조합
// 방안 2. Sort
public class Solution_2115 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	static int T, N, M, C, arr[][], ans;
	static Honey selectedHoney[];
	static List<Honey> list;
	static boolean[] isSelected;
	
	// 가능한 모든 윈도우 경우의 수를 list에 담는다.
	// 가능한 모든 경우의 수에 따른 max값이 필요하다.
	// 시작 위치, 끝 위치, row(행)값이 필요하다.
	static class Honey {
		int row, start, end;
		int max = 0;
		boolean[] isSelected = new boolean[M];
		
		public Honey(int row, int start, int end) {
			this.row = row;
			this.start = start;
			this.end = end;
			setMax(0, 0);
		}
		
		// max 세팅하는 메소드
		// 기저조건: sum의 크기가 C보다 클 경우
		public void setMax(int sum, int max) {
			if(sum > C) {
				return;
			}
			this.max = Integer.max(max, this.max);
			for(int i = start; i < end; i++) {
				if(!isSelected[i - start]) {
					isSelected[i - start] = true;
					setMax(sum + arr[row][i], max + (int) Math.pow(arr[row][i], 2));
					isSelected[i - start] = false;
				}
			}
		}
	}
	
	// 초기 세팅
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		list = new ArrayList<>();
		selectedHoney = new Honey[2];
		ans = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	// 모든 경우의 수를 리스트에 담기 -> 담아지면서 max값도 함께 세팅됨
	public static void settingHoney() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k <= N - M; k++) {
				list.add(new Honey(i, k, k + M));
			}
		}
		// 방안 1의 조합을 위한 isSelected배열 초기화
		isSelected = new boolean[list.size()];
	}
	
	// 일꾼이 겹치는 위치에 있는지 확인하는 메소드
	// 사실 같은 라인에 있냐가 아닌 start ~ end 사이에 있냐 없냐로 해야하는데 문제에 오류가 있어서 row로 비교.
	public static boolean isSameLine(Honey h1, Honey h2) {
		return h1.row == h2.row;
	}
	
	// 방안 1. 조합으로 2개의 경우의 수를 뽑는 방법
	// 전형적인 조합 코드.
	// 2개 고른 후 같은 위치 선상에 있다면 ans를 확인하고 바꿔줘야한다면 바꿈
	public static void comb(int index, int cnt) {
		if(cnt == 2) {
			if(!isSameLine(selectedHoney[0], selectedHoney[1])) {
				ans = Integer.max(selectedHoney[0].max + selectedHoney[1].max, ans);
			}
			return;
		}
		
		for(int i = index; i < list.size(); i++) {
			if (!isSelected[i]) {
				selectedHoney[cnt] = list.get(i);
				isSelected[i] = true;
				comb(i + 1, cnt + 1);
				isSelected[i] = false;
			}
		}
	}

	// 방안 2. List Sort 후 찾는 방법
	// 제일 큰 값 먼저 고른 후 다음 큰 값을 고름.
	// 다음 큰 값이 제일 큰 값과 같은 위치 선상에 있다면 다음 큰 값을 비교해서 가져오는 방식
	public static void findAns() {
		Collections.sort(list, (o1, o2) -> o2.max - o1.max);
		int firstMax = list.get(0).max;
		int secondMax = Integer.MIN_VALUE;
		for(int i = 1; i < list.size(); i++) {
			if(!isSameLine(list.get(0), list.get(i))) {
				secondMax = list.get(i).max;
				break;
			}
		}
		ans = firstMax + secondMax;
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			init();
			settingHoney();
			comb(0, 0);	// 방안 1
			// findAns();	// 방안 2
			bw.write("#" + t + " " + ans + "\n");
		}
		bw.flush();
		bw.close();
	}
}
