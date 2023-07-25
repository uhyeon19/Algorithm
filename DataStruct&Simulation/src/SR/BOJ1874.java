package SR;

import java.util.*;

public class BOJ1874 {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int start = 1;			// 처음 1부터 n까지 넣어야하니 1로 시작
		boolean check = true;	// 틀렸을 시에의 flag값
		
		for(int i = 0; i < n; i++) {
			int input = sc.nextInt();
			
			for(; start <= input; start++) {	// 시작값보다 input값이 크다면 input값까지 push!
				stack.push(start);
				sb.append('+').append('\n');	// push일 때는 +
			}
			if(stack.pop() == input) {			
				// 처음에는 push로 input값까지 넣었으니 아마 pop이 될 것임
				// input값이 start보다 작았다면? pop()했을 때의 값이 input과 같다면 원하는 수열을 만들어갈 수 있다는 뜻!
				sb.append('-').append('\n');	// pop일 때는 -
			} else {
				check = false;	// input값이 현재 pop할 값과 다르다면 다른 수열이 만들어진다는 뜻! check = false로 바꾸기
			}
		}
		
		System.out.println(check == true ? sb : "NO");		
	}
}
