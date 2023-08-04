package SR;

import java.util.*;

public class BOJ2164 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i <= n; i++) {
			q.offer(i);
		}

		if(q.size() != 1) {
			for(int i = 1; i <= n; i++) {
				q.poll();
				if(q.size() == 1) break;
				int tmp = q.poll();
				q.offer(tmp);
			}
			System.out.println(q.peek());
		} else {
			System.out.println("1");
		}
	}
}
