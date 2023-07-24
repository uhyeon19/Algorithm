package SR;

import java.io.*;
import java.util.*;

class Lecture {
	int start;
	int end;
	
	public Lecture(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

// https://steady-coding.tistory.com/253
public class BOJ11000 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Lecture[] lectures = new Lecture[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// https://ifuwanna.tistory.com/232
		// Comparable/Comparator
		// ex)
		//	Arrays.sort(OriginalArr, Comparator.reverseOrder())
		// 	Arrays.sort(OriginalArr, Collections.reverseOrder())
		// 위와 같이 compare을 만들어 사용 가능(이미 있는 Comparator, Collections내의 함수 사용 가능)
		// 람다식???
		// : 화살표 함수!!!
		
//		!!!예시!!!!
//		//Arrays.sort(arr);
//        Arrays.sort(arr,(x,y)->y-x);
//        Arrays.stream(arr).forEach(x-> System.out.print(x));
        
		// 시작 시간을 기준으로 오름차순 정렬,
        // 시작 시간이 같다면, 종료 시간을 기준으로 오름차순 정렬.
		// https://danny5.tistory.com/74
		// B형을 따기 위해서라면, Class 만들기, Comparable, Comparator 등을 잘 익히기!!!
		// 공부 하자!
		Arrays.sort(lectures, (l1, l2) -> l1.start == l2.start ? l1.end - l2.end : l1.start - l2.start);
	
        /** !!!Stack!!!
         * 후입선출 데이터 구조
         * https://coding-factory.tistory.com/601                   
         * 
         * Stack<Integer> stack = new Stack<>();                    
         * => 일반 스택                                                
         * 
         * stack.push(1);                                           
         * => 스텍에 1 값 추가                            
         * 
         * stack.pop();
         * => 스텍에 값 제거
         * 
         * stack.clear();
         * => 스텍의 전체 값 제거(초기화)     
         *     
         * stack.peek();
         * => 스텍에서 빼내지는 않지만 제일 상단 값 출력                  
         *                                                                 
         * stack.size();      // stack의 크기 출력 : 2
         * stack.empty();     // stack이 비어있는제 check (비어있다면 true)
         * stack.contains(1) // stack에 1이 있는지 check (있다면 true)      
        */
		
		
		/*
		 * !!!Queue!!!
		 * 선입선출 데이터 구조
         * https://velog.io/@db_jam/%EC%9E%90%EB%B0%94-%ED%81%90Queue-%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EC%A0%95%EB%A6%AC
		 *
         * PriorityQueue<Integer> pq = new PriorityQueue<>();
         *	=> 우선순위 큐
		 *
         * Queue<Integer> q = new LinkedList<>();
         *	=> 일반 큐
		 *
         * q.offer(1);
         *	=> 큐에 1값 추가
         * q.poll();
         *	=> 큐 선입선출 빼내서 출력
         * q.peek();
         *	=> 큐에서 빼내지는 않지만 제일 처음 값 출력
         *
		*/

		// 우선순위 큐에는 종료 시간만 들어가며, 오름차순으로 자동 정렬된다.
		// 우선순위 큐?
		// https://velog.io/@gillog/Java-Priority-Queue%EC%9A%B0%EC%84%A0-%EC%88%9C%EC%9C%84-%ED%81%90
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0].end);
        for (int i = 1; i < N; i++) {
            // 우선순위 큐에서 가장 작은 종료 시간과
            // 현재 lectures[i]의 시작 시간을 비교.
            if (pq.peek() <= lectures[i].start) {
                pq.poll();
            }
            pq.offer(lectures[i].end);
        }

        // 현재 우선순위 큐에 남아있는 요소의 개수가 필요한 최소한의 강의실 개수이다.
        bw.write(pq.size() + "\n");
        bw.flush();
        bw.close();
        br.close();
	}
}
