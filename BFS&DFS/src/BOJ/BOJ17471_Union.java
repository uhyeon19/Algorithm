package BOJ;

import java.io.*;
import java.util.*;

public class BOJ17471_Union {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, ans = Integer.MAX_VALUE, parents[], person[], copyParents[];
	static List<Integer> aList = new ArrayList<>();
	static List<Integer> bList;
	static List<Integer>[] link;

	@SuppressWarnings("unchecked")
	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		parents = new int[N + 1];
		person = new int[N + 1];
		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
			person[i] = Integer.parseInt(st.nextToken());
			link[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int k = 0; k < n; k++) {
				link[i].add(Integer.parseInt(st.nextToken()));
			}
		}
	}

	public static int findSet(int a) {
		if (a == copyParents[a]) return a;
		return copyParents[a] = findSet(copyParents[a]);
	}

	public static void union(int a, int b) { 
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot != bRoot) copyParents[bRoot] = aRoot;
	}

	public static void doUnion(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			int v = list.get(i);
			for(int k = 0; k < link[v].size(); k++) {
				if(list.contains(link[v].get(k))) {
					union(v, link[v].get(k));
				}
			}
		}
	}

	public static void solve() {
		doUnion(aList);
		doUnion(bList);
		HashSet<Integer> set = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			set.add(findSet(i));
		}
		if(set.size() != 2) return;
		int a = 0;
		int b = 0;
		for(int i : aList) a += person[i];
		for(int i : bList) b += person[i];
		ans = Integer.min(ans, Math.abs(a - b));
	}

	public static void comb(int cnt, int index) {
		if (cnt == 0) {
			bList = new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				if (!aList.contains(i))
					bList.add(i);
			}
			copyParents = parents.clone();
			solve();
			return;
		}
		for (int i = index; i <= N; i++) {
			aList.add(i);
			comb(cnt - 1, i + 1);
			aList.remove(aList.size() - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		for (int i = 1; i <= N / 2; i++) {
			comb(i, 1);
		}
		bw.write(ans == Integer.MAX_VALUE ? -1 + "\n" : ans + "\n");
		bw.flush();
		bw.close();
	}
}