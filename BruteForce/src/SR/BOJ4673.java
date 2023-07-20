package SR;

public class BOJ4673 {
	public static void main(String[] args) {
		boolean self[] = new boolean[10001];

		for (int i = 1; i <= 10000; i++) {
			int n = d(i);
			if(n <= 10000)
				self[n] = true;
		}
		
		for(int i = 1; i <= 10000; i++) {
			if(self[i] != true)
				System.out.println(i);
		}
	}

	public static int d(int n) {
		int next = n;
		while (n != 0) {
			next += (n % 10);
			n /= 10;
		}
		return next;
	}
}
