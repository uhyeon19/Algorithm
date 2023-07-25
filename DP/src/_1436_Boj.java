package algorithm;

import java.util.Scanner;

// 영화감독 숌
// 어떤 수에 6이 적어도 3개 이상 연속으로 들어가는 수
public class _1436_Boj {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int num = 666;
		int cnt = 0;
		// contains

		while (true) {

			if (Integer.toString(num).contains("666")) {
				cnt++;
			}

			if (cnt == N) {
				break;
			}

			num += 1;

		}
		
		System.out.println(num);
	}

}
