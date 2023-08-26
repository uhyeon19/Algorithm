package SR;

import java.io.*;
import java.util.*;

public class BOJ1302 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static Map<String, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		int max = 0;
        for (int i = 0; i < N; i++) {
            String book = br.readLine();
            map.put(book, map.getOrDefault(book, 0) + 1);
            max = Math.max(max, map.get(book));
        }
        
        List<String> bookList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) bookList.add(entry.getKey());
        }
        Collections.sort(bookList);
        System.out.println(bookList.get(0));
	}
}
