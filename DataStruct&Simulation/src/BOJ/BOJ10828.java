package BOJ;

import java.io.*;
import java.util.*;

public class BOJ10828 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static Stack<Integer> stack = new Stack<>();
	static int N;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int push = -1;
			if(st.hasMoreTokens()) push = Integer.parseInt(st.nextToken());
			if(cmd.equals("push")) {
				stack.push(push);
			} else if(cmd.equals("top")) {
				if(stack.isEmpty()) sb.append(-1 + "\n");
				else sb.append(stack.peek() + "\n");
			} else if(cmd.equals("size")) {
				sb.append(stack.size() + "\n");
			} else if(cmd.equals("pop")) {
				if(stack.isEmpty()) sb.append(-1 + "\n");
				else sb.append(stack.pop() + "\n");
			} else if(cmd.equals("empty")) {
				if(stack.isEmpty()) sb.append(1 + "\n");
				else sb.append(0 + "\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
