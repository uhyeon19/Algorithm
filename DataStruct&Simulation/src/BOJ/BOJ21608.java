package BOJ;

import java.io.*;
import java.util.*;

public class BOJ21608 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int N;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Student students[], arr[][];
	static PriorityQueue<Candidate> pq;

	static class Student {
		int num, friends[];

		public Student(int num, int[] friends) {
			this.num = num;
			this.friends = friends;
		}
	}

	static class Candidate implements Comparable<Candidate> {
		int x, y, friendCnt, emptyCnt;

		public Candidate(int x, int y, int friendCnt, int emptyCnt) {
			this.x = x;
			this.y = y;
			this.friendCnt = friendCnt;
			this.emptyCnt = emptyCnt;
		}

		@Override
		public int compareTo(Candidate o) {
			if (this.friendCnt != o.friendCnt) {
				return Integer.compare(this.friendCnt, o.friendCnt) * -1;
			} else if(this.emptyCnt != o.emptyCnt) {
				return Integer.compare(this.emptyCnt, o.emptyCnt) * -1;
			} else if(this.x != o.x) {
				return Integer.compare(this.x, o.x);
			} else {
				return Integer.compare(this.y, o.y);
			}
		}
	}
	
	static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}
	
	static void findPoint(Student s) {
		pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(arr[i][k] == null) {
					Candidate c = new Candidate(i, k, 0, 0);
					for(int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = k + dy[d];
						if(!isIn(nx, ny)) continue;
						if(arr[nx][ny] != null) {
							for(int j = 0; j < 4; j++) {
								if(s.friends[j] == arr[nx][ny].num) {
									c.friendCnt++;
									break;
								}
							}
						} else {
							c.emptyCnt++;
						}
					}
					pq.offer(c);
				}
			}
		}
		Candidate c = pq.poll();
		arr[c.x][c.y] = s;
	}
	
	static int addScore() {
		int ans = 0;
		for(int i = 0; i < students.length; i++) {
			findPoint(students[i]);
		}
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				int cnt = -1;
				for(int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = k + dy[d];
					if(!isIn(nx, ny)) continue;
					for(int j = 0; j < 4; j++) {
						if(arr[i][k].friends[j] == arr[nx][ny].num) {
							cnt++;
							break;
						}	
					}
				}
				ans += (int) Math.pow(10, cnt);
			}
		}
		return ans;
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		students = new Student[N * N];
		arr = new Student[N][N];
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int friends[] = new int[4];
			for (int k = 0; k < 4; k++) {
				friends[k] = Integer.parseInt(st.nextToken());
			}
			students[i] = new Student(num, friends);
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(addScore());
	}
}
