package BOJ;

import java.io.*;
import java.util.*;

/**
 * !!초기 생각!!                                                                 
 * 치킨 위치를 가지고 있는 리스트 혹은 배열 필요                                                
 * 집 위치를 가지고 있는 리스트 혹은 배열 필요                                                 
 * 위치를 저장할 point 클래스 필요                                                      
 * 배열 크기 = N, 남길 치킨집 개수 = M                                                  
 * mCn의 조합이 필요 -> 조합된 위치를 가지고 치킨 거리 구해서 tmpChicken리스트에 담기 -> 조합 메소드 구현       
 * 리스트 정렬 -> 리스트 처음이 치킨 거리의 최솟값일 것 => PriorityQueue로 해결!                     
 * 거리 계산 필요 -> 맨해튼 거리                                                        
 * @author SSAFY
 */
public class BOJ15686 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, ans = Integer.MAX_VALUE;
	static boolean isSelected[];
	static List<Point> house = new ArrayList<>();	// 집의 좌표들
	static List<Point> chicken = new ArrayList<>();	// 치킨집 좌표들
	static Point[] tmpChicken;	// 조합으로 구할 치킨집 좌표를 담고 있는 배열

	/**
	 * 좌표를 표현할 클래스
	 * @author SSAFY
	 */
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * 초기값 세킹을 위한 메소드
	 * @throws IOException
	 */
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < N; k++) {
				int info = Integer.parseInt(st.nextToken());
				if (info == 1) {
					// 들어온 값이 1이면 집의 좌표 추가
					house.add(new Point(i, k));
				} else if (info == 2) {
					// 들어온 값이 2이면 치킨집 좌표 추가
					chicken.add(new Point(i, k));
				}
			}
		}

		// isSelected 초기화
		isSelected = new boolean[chicken.size()];
		// tmpChicken(조합으로 만들어질 치킨집 좌표) 초기화
		tmpChicken = new Point[M];
	}

	/**
	 * 맨해튼 거리 구하는 메소드
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static int Manhattan(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
	
	/**
	 * 전형적인 조합 메소드
	 * @param index
	 * @param cnt
	 */
	public static void comb(int index, int cnt) {
		if(cnt == M) {	// 총 크기의 값은 M개
			int chickDistance = 0;	// 현재 만들어진 치킨집 좌표로 만드는 치킨 거리
			for(int i = 0; i < house.size(); i++) {
				int minChickD = Integer.MAX_VALUE;
				for(int k = 0; k < tmpChicken.length; k++) {
					// 내 집에서 가장 가까운 치킨집 찾기
					minChickD = Integer.min(Manhattan(tmpChicken[k], house.get(i)), minChickD);
				}
				// 가장 가까운 치킨집의 거리를 치킨 거리에 추가해 나아가기
				chickDistance += minChickD;
			}
			ans = Integer.min(ans, chickDistance);
			return;
		}
		for(int i = index; i < chicken.size(); i++) {
			if(!isSelected[i]) {
				tmpChicken[cnt] = chicken.get(i);
				isSelected[i] = true;
				comb(i + 1, cnt + 1);
				isSelected[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		comb(0, 0);
		System.out.println(ans);
	}
}