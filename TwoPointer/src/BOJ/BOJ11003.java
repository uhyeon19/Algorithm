package BOJ;

import java.io.*;
import java.util.*;

// �����̵� ������
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
			int num = Integer.parseInt(st.nextToken());	// ���� ���� ��
			
			// ť�� ������� ������, ���� ���� num�� ���� ���� num���� ũ�ٸ� -> ���ο� ���� ��!!
			while (!q.isEmpty() && q.peekLast().num > num)
				q.pollLast();	// �� ���� �ִ� ū ���� ������!
			
			// ���� ������ �� �߰�
			q.offer(new Point(num, i));
			
			// q���� Ȯ���ϴ� ó�� �ε����� i - L - 1���� �۴ٸ� ���� �ѱ�..!
			if (q.peek().index < i - (L - 1)) {
				q.poll();	// ������ �ƴ� �� ���ֱ�
			}
			
			// ���� �������� ���� ���� ���� ���� ���̴�.
			bw.write(q.peek().num + " ");
		}
		bw.flush();
		bw.close();
	}
}