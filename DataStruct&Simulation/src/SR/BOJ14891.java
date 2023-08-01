package SR;

import java.util.*;
import java.io.*;

public class BOJ14891 {
	static List<List<Integer>> wheels = new ArrayList<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int K, index, d;
	static int[] dArr = new int[4];
	static boolean[] isTurn;
	
	public static void makeDirectionArr() {
		for(int i = 0; i < 4; i++) {
			if(index % 2 == i % 2) {
				dArr[i] = d;
			} else {
				dArr[i] = d * -1;
			}
		}
	}
	
	public static void makeIsTurnRight() {
		int tmpIndex = index;
		while(true) {
			if(tmpIndex + 1 >= 4) {
				break;
			} else {
				int nIndex = tmpIndex + 1;
				if(wheels.get(tmpIndex).get(2) != wheels.get(nIndex).get(6)) {
					isTurn[nIndex] = true;
					tmpIndex = nIndex;
				} else {
					break;
				}
			}
		}
	}
	
	public static void makeIsTurnLeft() {
		int tmpIndex = index;
		while(true) {
			if(tmpIndex - 1 < 0) {
				break;
			} else {
				int nIndex = tmpIndex - 1;
				if(wheels.get(tmpIndex).get(6) != wheels.get(nIndex).get(2)) {
					isTurn[nIndex] = true;
					tmpIndex = nIndex;
				} else {
					break;
				}
			}
		}
	}
	
	public static void turning() {
		for(int i = 0; i < 4; i++) {
			if(isTurn[i]) {
				int tmpMagnet = 0;
				if(dArr[i] == 1) {
					tmpMagnet = wheels.get(i).get(7);
					wheels.get(i).remove(7);
					wheels.get(i).add(0, tmpMagnet);
				} else {
					tmpMagnet = wheels.get(i).get(0);
					wheels.get(i).remove(0);
					wheels.get(i).add(tmpMagnet);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 4; i++) {
			String str = br.readLine();
			List<Integer> wheel = new ArrayList<>(); 
			for(int k = 0; k < str.length(); k++) {
				wheel.add(str.charAt(k) - '0');
			}
			wheels.add(wheel);
		}
		
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			index = Integer.parseInt(st.nextToken()) - 1;
			d = Integer.parseInt(st.nextToken());
			isTurn = new boolean[4];
			makeDirectionArr();
			isTurn[index] = true;
			makeIsTurnRight();
			makeIsTurnLeft();
			turning();
		}
		
		int sum = 0;
		for(int i = 0; i < 4; i++) {
			if(wheels.get(i).get(0) == 1) sum += Math.pow(2, i);
		}
		System.out.println(sum);
	}
}