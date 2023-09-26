package BOJ;

import java.util.Scanner;

public class BOJ1436 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int i = 666;
		int cnt = 0;
		String str = "";
		
		while(true) {
			str = String.valueOf(i);
			if(str.contains("666")) {
				cnt++;
			}
			if(n == cnt) break;
			i++;
		}
		System.out.println(i);
	}
}
