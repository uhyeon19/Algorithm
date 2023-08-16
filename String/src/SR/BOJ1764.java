package SR;

import java.io.*;
import java.util.*;

public class BOJ1764 {
	static Set<String> heard = new HashSet<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static List<String> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int hCnt = Integer.parseInt(st.nextToken());
		int wCnt = Integer.parseInt(st.nextToken());
		for(int i = 0; i < hCnt; i++) {
			heard.add(br.readLine());
		}
		
		for(int i = 0; i < wCnt; i++) {
			String str = br.readLine();
			if(heard.contains(str)) {
				list.add(str);
			}
		}
		
		Collections.sort(list);
		sb.append(list.size() + "\n");
		for(int i = 0; i < list.size(); i++) {
			sb.append(list.get(i) + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
