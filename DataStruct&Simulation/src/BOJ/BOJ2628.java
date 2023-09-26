package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2628 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int cnt;
	static List<Integer> xlist = new LinkedList<>();
	static List<Integer> ylist = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		xlist.add(Integer.parseInt(st.nextToken())); // 가로
		ylist.add(Integer.parseInt(st.nextToken())); // 세로

		cnt = Integer.parseInt(br.readLine());
		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int isX = Integer.parseInt(st.nextToken());
			int cutCount = Integer.parseInt(st.nextToken());
			if (isX == 0) { // 가로로 자르니까 세로리스트를 건드려야함
				int index;
				for (index = 0; cutCount - ylist.get(index) > 0; index++) {
					cutCount -= ylist.get(index);
				}
				int temp = ylist.remove(index);
				ylist.add(index, temp - cutCount);
				ylist.add(index, cutCount);
			} else {
				int index;
				for (index = 0; cutCount - xlist.get(index) > 0; index++) {
					cutCount -= xlist.get(index);
				}
				int temp = xlist.remove(index);
				xlist.add(index, temp - cutCount);
				xlist.add(index, cutCount);
			}
		}
		int max = 0;
		for (int i : xlist) {
			for (int j : ylist)
				max = Math.max(i * j, max);
		}
		System.out.println(max);
	}
}
