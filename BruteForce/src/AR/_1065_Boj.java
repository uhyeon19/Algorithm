package AR;

import java.util.Scanner;

public class _1065_Boj {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int cnt = 0;
		if (n < 100) {
			cnt = n;
		} else if (n == 1000) {
			cnt = 144;
		} else {
			cnt = 99;

			for (int i = 100; i < n + 1; i++) {

				char[] tmp = String.valueOf(i).toCharArray();

				int diff1 = tmp[0] - tmp[1];
				int diff2 = tmp[1] - tmp[2];

				if (diff1 == diff2) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}