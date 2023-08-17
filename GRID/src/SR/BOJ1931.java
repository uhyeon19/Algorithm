package SR;

import java.io.*;
import java.util.*;

public class BOJ1931 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Lecture[] lectures;
	static int N, ans = 0;

	static class Lecture {
		int start, end;

		Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		lectures = new Lecture[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(lectures, (l1, l2) -> l1.end == l2.end ? l1.start - l2.start : l1.end - l2.end);
		int prevEndTime = 0;
		for(int i = 0; i < N; i++) {
			if(prevEndTime <= lectures[i].start) {
				prevEndTime = lectures[i].end;
				ans++;
			}
		}
		System.out.println(ans);
	}
}