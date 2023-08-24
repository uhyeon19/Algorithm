package SR;

import java.util.*;
import java.io.*;

public class Solution_1233 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		for (int tc = 0; tc < 10; tc++) {
			sb.append('#').append(tc + 1).append(' ');
			int size = Integer.parseInt(br.readLine());
			boolean check = false;

			char[] arr = new char[size + 1];
			for (int i = 1; i <= size; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				arr[i] = st.nextToken().charAt(0);
			}

			if (size % 2 == 0) {
				sb.append(0).append('\n');
				continue;
			}

			for (int i = size; i >= 3; i -= 2) {
				if (arr[i] == '-' || arr[i] == '+' || arr[i] == '/' || arr[i] == '*' || 
					arr[i - 1] == '-' || arr[i - 1] == '+' || arr[i - 1] == '/' || arr[i - 1] == '*') {
					sb.append(0).append('\n');
					check = true;
					break;
				}

				if (arr[(i - 1) / 2] == '-' || arr[(i - 1) / 2] == '+' || arr[(i - 1) / 2] == '/'
						|| arr[(i - 1) / 2] == '*') {
					arr[(i - 1) / 2] = '1';
				} else {
					sb.append(0).append('\n');
					check = true;
					break;
				}

			}
			if (!check)
				sb.append(1).append('\n');
		}
		System.out.print(sb.toString());
	}

}
