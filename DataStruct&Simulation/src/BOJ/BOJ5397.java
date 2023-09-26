package BOJ;

import java.io.*;
import java.util.*;

public class BOJ5397 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int T;

	public static String pass(String prePass) {
		StringBuilder sb = new StringBuilder();
		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();
		
		char []cArr = prePass.toCharArray();

		for(char c : cArr) {
			switch (c) {
			case '<':
				if (!left.isEmpty()) {
					right.push(left.pop());
				}
				break;
			case '>':
				if (!right.isEmpty()) {
					left.push(right.pop());
				}
				break;
			case '-':
				if (!left.isEmpty()) {
					left.pop();
				}
				break;
			default:
				left.push(c);
			}
		}
		
		while (!left.isEmpty()) {
            right.push(left.pop());
        }
        while (!right.isEmpty()) {
            sb.append(right.pop());
        }
        sb.append("\n");
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			sb.append(pass(br.readLine()));
		}
		System.out.print(sb.toString());
	}
}
