package BOJ;

import java.io.*;
import java.util.*;

class Document {
	int index = 0;
	int prior = 0;
	
	public Document(int index, int prior) {
		this.index = index;
		this.prior = prior;
	}
}

public class BOJ1966 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		ArrayList<Integer> ans = new ArrayList<>();
		
		
		for(int i = 0; i < TC; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			Queue<Document> documents = new LinkedList<>();
			int []sortPrior = new int[M];
			
			st =  new StringTokenizer(br.readLine());
			int cnt = 0;
			while(st.hasMoreTokens()) {
				int tmpPrior = Integer.parseInt(st.nextToken());
				documents.offer(new Document(cnt, tmpPrior));
				sortPrior[cnt++] = tmpPrior;
			}
			Arrays.sort(sortPrior);
			
			Queue<Document> sortDocument = new LinkedList<>();
			for(int k = M - 1; k >= 0;) {
				if(sortPrior[k] != documents.peek().prior) {
					Document tmp = documents.poll();
					documents.offer(tmp);
				} else {
					sortDocument.offer(documents.poll());
					k--;
				}
			}
			
			for(int k = 0; k < M; k++) {
				if(index == sortDocument.poll().index) {
					ans.add(k + 1);
					break;
				}
			}
		}
		
		for(int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i));
		}
	}
}