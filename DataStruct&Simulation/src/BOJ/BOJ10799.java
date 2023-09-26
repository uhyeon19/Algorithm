package BOJ;

import java.io.*;
import java.util.*;

public class BOJ10799 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int ans = 0;
	static Stack<Character> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				stack.push(str.charAt(i));
			} else {
				stack.pop();
				
				if(str.charAt(i - 1) == '(') {
					ans += stack.size();
				} else {
					ans++;
				}
			}
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}
