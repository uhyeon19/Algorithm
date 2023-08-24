package SR;

import java.io.*;
import java.util.*;

public class BOJ15961 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, d, k, c, arr[], ans = 0, visited[];

	/**
	 * !!슬라이딩윈도우!!
	 * 투포인터랑 비슷한데 크기가 정해져 있음
	 * k개만큼으로 정해져 있으며 한 칸씩 옆으로 이동하는 모양
	 * 이를 토대로 처음 초기값을 잡고, 앞에 친구는 빼주고 뒤에 친구를 하나 넣는 슬라이딩 윈도우 실행
	 */
	public static void slidingWindow() {
		// 초기 세팅
		int curEat = 0;
		for (int i = 0; i < k; i++) {
			if (visited[arr[i]] == 0)
				curEat++;
			visited[arr[i]]++;
		}
		// 0번 인덱스부터 k까지 처음 범위값을 잡아서 몇 종류의 초밥을 먹었는지 계산
		
		// 우리에게는 쿠폰이 하나 있다! 이를 계산하면서 갈 것이다.
		ans = curEat;
		
		//!!!!!!!!!!!!!!! 아무 생각 없이 i < N 조건까지로 돌렸는데 생각해보니 1부터 시작하면 1개의 경우의 수는 고려하지 않는다... !!!!!!!!!!!!!!!!!
		for (int i = 1; i <= N; i++) {	// 제일 처음 값은 빼면서 갈 것이니 1부터 시작
			if (ans <= curEat) {	// curEat은 현재 만들어진 조합에서의 초밥 종류 개수 (개수가 크거나 같아야 쿠폰 계산해서 훨씬 더 높아지는지 보는게 이득)
				if (visited[c] == 0)	// 쿠폰으로 받은 종류의 초밥을 먹지 않았다면 1개 늘려줄 것이다. => 즉, 현재 조합에서 뽑아낸 초밥 종류 개수 + 1이던가
					ans = curEat + 1;
				else	// 아니라면 현재 조합에서의 초밥 종류 개수 => 즉, 현재 조합에서 뽑아낸 초밥 종류 개수이던가. 어쨋든 이전 값이랑 같거나 더 크니까~!
					ans = curEat;
			}
			visited[arr[i - 1]]--;	// 제일 앞의 범위 친구 하나 빼주기
			if (visited[arr[i - 1]] == 0)	// 그 친구가 0이면 종류에서 빠짐 아니면 그대로! ({7, 7, 2, 3}의 경우라면 7이 하나 빠져도 종류의 개수에는 변화가 없다. 이를 체크하기 위함)
				curEat--;
			if (visited[arr[(i + k - 1) % N]] == 0)	// 다음 초밥 접시의 위치는 (i + k - 1) % N이다! => i가 N 이전까지 가는데 k만큼의 범위를 더하다보면 N을 넘어가니까 이를 % N을 줘서 계산!
				curEat++;	// 다음 초밥 접시가 먹지 않았던 종류라면 종류 추가
			visited[arr[(i + k - 1) % N]]++;	// 먹었던 종류던 먹지 않았던 종류던 조합 자체에는 들어오는 거니까 ++
		}
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[N];
		visited = new int[d + 1];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		slidingWindow();
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}
