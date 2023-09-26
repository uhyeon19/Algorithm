package BOJ;

import java.util.*;
import java.io.*;

public class BOJ2493 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, height[];
	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		height = new int[N];
		st = new StringTokenizer(br.readLine());
		int cnt = 0;
		while (st.hasMoreTokens()) {
			height[cnt] = Integer.parseInt(st.nextToken());
			while (!stack.isEmpty() && height[stack.peek()] < height[cnt]) {
				stack.pop();
			}
			if (!stack.isEmpty())
				sb.append(stack.peek() + 1).append(' ');
			else
				sb.append(0).append(' ');
			stack.push(cnt++);
		}

		System.out.println(sb.toString());
	}
}