package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1025 {
	public static int solve(int n, int m, char [][]arr) {
		int result = -1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				for(int ni = -n; ni < n; ni++) {	// n의 등차 확인
					for(int mi = -m; mi < m; mi++) {	// m의 등차 확인
						if(ni == 0 && mi == 0) {	//둘 다 0으로 움직이지 않는 경우
							continue;
						}
						
						String str = "";
						int tmpI = i;
						int tmpJ = j;
						while (tmpI >= 0 && tmpI < n && tmpJ >= 0 && tmpJ < m) { 
							// 위치가 0~n, 0~m범위를 설정
                            str += arr[tmpI][tmpJ];
                            if (isSquare(Integer.parseInt(str))){ // 완전 제곱수 판별
                                result = Math.max(result, Integer.parseInt(str));
                            }
                            tmpI += ni;
                            tmpJ += mi;
                        }
					}
				}
			}
		}
		return result;
	}
	
	public static boolean isSquare(int num) {
		return (int)Math.sqrt(num) * (int)Math.sqrt(num) == num; 
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char [][]arr = new char[n][m];
		
		for(int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		System.out.println(solve(n, m, arr));
	}
}