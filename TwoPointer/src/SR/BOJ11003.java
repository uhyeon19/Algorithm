package SR;

import java.io.*;
import java.util.*;

// 슬라이딩 윈도우
// https://loosie.tistory.com/324
public class BOJ11003 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static Deque<Point> q = new ArrayDeque<>();
	static int N, L;

	static class Point {
		int num, index;

		public Point(int num, int index) {
			this.num = num;
			this.index = index;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());	// 새로 들어올 값
			
			// 큐가 비어있지 않으며, 이전 값의 num이 현재 들어올 num보다 크다면 -> 새로운 작은 값!!
			while (!q.isEmpty() && q.peekLast().num > num)
				q.pollLast();	// 그 전에 있던 큰 값은 버린다!
			
			// 새로 들어오는 값 추가
			q.offer(new Point(num, i));
			
			// q에서 확인하는 처음 인덱스가 i - L - 1보다 작다면 범위 넘김..!
			if (q.peek().index < i - (L - 1)) {
				q.poll();	// 범위가 아닌 값 빼주기
			}
			
			// 현재 범위에서 가장 작은 값이 있을 것이다.
			bw.write(q.peek().num + " ");
		}
		bw.flush();
		bw.close();
	}
}