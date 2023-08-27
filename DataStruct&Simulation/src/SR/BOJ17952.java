package SR;

import java.io.*;
import java.util.*;

public class BOJ17952 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위함
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력을 위함
	static StringBuilder sb = new StringBuilder(); // 출력값을 저장할 스트링빌더
	static StringTokenizer st;	// 업무가 들어올 때 1 A T형식으로 들어오기 때문에 이를 잘라서 저장할 st
	static Stack<Work> stack = new Stack<>();	// 업무가 들어왔을 때 가장 최근의 업무를 바로 시작한다고 했으니, 이를 스택으로 처리할 것이다.
	static int N, ans = 0;	// 분, 점수의 합을 저장할 ans무

	/**
	 * 업무가 들어올 때, 점수와 이를 처리하는데 걸리는 시간이 들어온다.
	 * 이를 클래스로 관리!
	 */
	static class Work{
		int score, minute;	// 점수와 시간(분)

		// 생성자
		public Work(int score, int minute) {
			super();
			this.score = score;
			this.minute = minute;
		}
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());	// 분 수 받아주기
		Work curJob;	// 현재 하고있는 업무를 담아줄 curJob
		for(int i = 0; i < N; i++) {	// 분 수만큼 반복
			st = new StringTokenizer(br.readLine());	// 업무가 들어오면 1 A T, 들어오지 않으면 0
			int isWork = Integer.parseInt(st.nextToken());	// 0, 1 받아주기
			if(isWork == 1) {	// 만약 1이 들어와서 업무가 새로 들어왔다면?
				int score = Integer.parseInt(st.nextToken());	// 점수 받기
				int minute = Integer.parseInt(st.nextToken());	// 시간 받기
				stack.push(new Work(score, minute));	// 스택에 넣어주기
			}
			
			// 현재 할 수 있는 업무들이 있다면!!
			// 가장 새로운 업무를 하며, 새로운 업무 후에는 이전 들어왔던 하던 업무를 한다.
			if(!stack.isEmpty()) {
				curJob = stack.pop();	// 가장 상단에 있는 업무가 지금 해야할 업무!
				int changeMin = curJob.minute - 1;	// 현재 업무에서 분수를 하나 빼 주고
				if(changeMin == 0) {	// 1분을 뺀 업무가 0이라면 현재 업무를 다 한 것이다.
					ans += curJob.score;	// ans에 점수를 더해주고 stack에는 이미 다 한 업무를 넣지 않는다!(이미 다 했으니까..!!)
				} else {	
					// 업무를 1분동안 했는데 끝내지 못 한 경우는 다시 stack에 채워서 해야할 업무로 남는다.
					// 이후에 새로운 업무가 들어와서 push가 된다면 이를 처리할 것이고 아니라면 하던 업무를 꺼내 다시 하는 형식!
					stack.push(new Work(curJob.score, changeMin));
				}
			}
		}
		sb.append(ans + "\n");	// ans를 sb에 담고
		bw.write(sb.toString());	// sb를 출력한다.
		bw.flush();
		bw.close();
	}
}
