
package SR;

import java.io.*;
import java.util.*;

public class Solution_1218 {
	static int n;
	static String str;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		for (int t = 0; t < 10; t++) {
			n = Integer.parseInt(br.readLine());
			str = br.readLine();
			Stack<Character> s = new Stack<>();
			boolean check = true;
			sb.append('#').append(t + 1).append(' ');
			for (int i = 0; i < n; i++) {
				char tmp = str.charAt(i);
				if (tmp == '(' || tmp == '[' || tmp == '{' || tmp == '<') {
					s.push(tmp);
				} else {
					if (s.isEmpty()) {
						check = false;
						break;
					} else {
						if (tmp == ')') {
							if (s.pop() != '(') {
								sb.append('0').append('\n');
								check = false;
								break;
							}
						} else if (tmp == ']') {
							if (s.pop() != '[') {
								sb.append('0').append('\n');
								check = false;
								break;
							}
						} else if (tmp == '>') {
							if (s.pop() != '<') {
								sb.append('0').append('\n');
								check = false;
								break;
							}
						} else if (tmp == '}') {
							if (s.pop() != '{') {
								sb.append('0').append('\n');
								check = false;
								break;
							}
						}
					}
				}
			}
			if (check)
				sb.append('1').append('\n');
		}
		System.out.print(sb.toString());
	}
}
