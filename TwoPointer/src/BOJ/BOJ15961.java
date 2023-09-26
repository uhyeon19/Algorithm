package BOJ;

import java.io.*;
import java.util.*;

public class BOJ15961 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, d, k, c, arr[], ans = 0, visited[];

	/**
	 * !!�����̵�������!!
	 * �������Ͷ� ����ѵ� ũ�Ⱑ ������ ����
	 * k����ŭ���� ������ ������ �� ĭ�� ������ �̵��ϴ� ���
	 * �̸� ���� ó�� �ʱⰪ�� ���, �տ� ģ���� ���ְ� �ڿ� ģ���� �ϳ� �ִ� �����̵� ������ ����
	 */
	public static void slidingWindow() {
		// �ʱ� ����
		int curEat = 0;
		for (int i = 0; i < k; i++) {
			if (visited[arr[i]] == 0)
				curEat++;
			visited[arr[i]]++;
		}
		// 0�� �ε������� k���� ó�� �������� ��Ƽ� �� ������ �ʹ��� �Ծ����� ���
		
		// �츮���Դ� ������ �ϳ� �ִ�! �̸� ����ϸ鼭 �� ���̴�.
		ans = curEat;
		
		//!!!!!!!!!!!!!!! �ƹ� ���� ���� i < N ���Ǳ����� ���ȴµ� �����غ��� 1���� �����ϸ� 1���� ����� ���� ������� �ʴ´�... !!!!!!!!!!!!!!!!!
		for (int i = 1; i <= N; i++) {	// ���� ó�� ���� ���鼭 �� ���̴� 1���� ����
			if (ans <= curEat) {	// curEat�� ���� ������� ���տ����� �ʹ� ���� ���� (������ ũ�ų� ���ƾ� ���� ����ؼ� �ξ� �� ���������� ���°� �̵�)
				if (visited[c] == 0)	// �������� ���� ������ �ʹ��� ���� �ʾҴٸ� 1�� �÷��� ���̴�. => ��, ���� ���տ��� �̾Ƴ� �ʹ� ���� ���� + 1�̴���
					ans = curEat + 1;
				else	// �ƴ϶�� ���� ���տ����� �ʹ� ���� ���� => ��, ���� ���տ��� �̾Ƴ� �ʹ� ���� �����̴���. ��¶�� ���� ���̶� ���ų� �� ũ�ϱ�~!
					ans = curEat;
			}
			visited[arr[i - 1]]--;	// ���� ���� ���� ģ�� �ϳ� ���ֱ�
			if (visited[arr[i - 1]] == 0)	// �� ģ���� 0�̸� �������� ���� �ƴϸ� �״��! ({7, 7, 2, 3}�� ����� 7�� �ϳ� ������ ������ �������� ��ȭ�� ����. �̸� üũ�ϱ� ����)
				curEat--;
			if (visited[arr[(i + k - 1) % N]] == 0)	// ���� �ʹ� ������ ��ġ�� (i + k - 1) % N�̴�! => i�� N �������� ���µ� k��ŭ�� ������ ���ϴٺ��� N�� �Ѿ�ϱ� �̸� % N�� �༭ ���!
				curEat++;	// ���� �ʹ� ���ð� ���� �ʾҴ� ������� ���� �߰�
			visited[arr[(i + k - 1) % N]]++;	// �Ծ��� ������ ���� �ʾҴ� ������ ���� ��ü���� ������ �Ŵϱ� ++
		}
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[N];
		visited = new int[d + 1];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		slidingWindow();
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}
