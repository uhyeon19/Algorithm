package BOJ;

import java.io.*;
import java.util.*;

public class BOJ17298_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		Stack<Integer> stack = new Stack<Integer>();

		int N = Integer.parseInt(br.readLine());
		int[] seq = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			/*
			 * ������ ������� �����鼭 ���� ���Ұ� ������ �� �� ���Ұ� ����Ű�� ���Һ��� ū ��� �ش� ������ ������ �� ���� stack�� ���Ҹ�
			 * pop�ϸ鼭 �ش� �ε����� ���� ���� ���ҷ� �ٲ��ش�.
			 */
			while (!stack.isEmpty() && seq[stack.peek()] < seq[i]) {
				seq[stack.pop()] = seq[i];
			}

			stack.push(i);
		}

		/*
		 * ������ ��� ���Ҹ� pop�ϸ鼭 �ش� �ε����� value�� -1�� �ʱ�ȭ�Ѵ�.
		 */
		while (!stack.isEmpty()) {
			seq[stack.pop()] = -1;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(seq[i]).append(' ');
		}

		System.out.println(sb);
	}
}
