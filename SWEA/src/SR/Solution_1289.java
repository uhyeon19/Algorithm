 package SR;

import java.util.*;

public class Solution_1289 {
	static int T;	// testCase ???₯
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	// Scanner κ°μ²΄ ??±
		StringBuilder sb = new StringBuilder();	// StringBuilderλ‘? μΆλ ₯κ°? ???₯
		T = sc.nextInt();						//  testCase ?? ₯
		for(int i = 0; i < T; i++) {
			int ans = 0;						// λ°λ?λ ?? ???₯?  κ³΅κ°
			char before = '0';					// ?΄?  ?? ???₯, μ²μ?? 00~~?΄?κΉ? 0?Όλ‘? μ΄κΈ°?
			String tmp = sc.next();				// ??? κ°? ???₯
			for(int k = 0; k < tmp.length(); k++) {	// ??? κ°? κΈΈμ΄λ§νΌ forλ¬?
				if(before != tmp.charAt(k)) {	// ?΄?  κ°μ΄? λ³΅κ΅¬?  κ°? λΉκ΅ ? ?€λ₯΄λ€λ©??
					before = tmp.charAt(k);		// ?€λ₯? κ°μ before? ?£?? ?
					ans++;						// ans ??΄μ£ΌκΈ°
				}
			}
			sb.append("#").append(i + 1).append(" ").append(ans).append("\n");
			// StringBuilder? κ°? ???₯
		}
		System.out.print(sb.toString());	// κ°? μΆλ ₯
		sc.close();
	}
}
