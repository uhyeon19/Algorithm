package SWEA;

import java.io.*;
import java.util.*;

// !!! �ʱ� ���� !!!
// ��Ʈ��ŷ? �׸���??
// �׸���! -> ��Ģ�� ��¦ ã�ƺ���
// https://ghs4593.tistory.com/12
// 2�� 1�� ������ �����ϴ�.
// 2�� 1�� ���̸� �ּ�ȭ �Ѵ�.
// 1) 2�� 1 �� 2�� ���ٸ� day�� 2�� ���� * 2�� �ȴ�.
// 2) 2�� 1 �� 1�� ���ٸ� day�� 1�� ���� * 2 - 1�� �ȴ�.
// 3) 2�� 1�� ������ ���ٸ� �����ư��鼭 ���� �ָ� �Ǳ� ������ 1�� ���� + 2�� ������ �ȴ�.
public class Solution_14510 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, N, arr[], max;

	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		max = 0; // ���� ū ���� ����
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Integer.max(max, arr[i]);
		}
	}

	public static int getDay() {
		// ������ �ڶ�� �� ���̿��� �ʿ��� 1, 2�� �� �� ���ϱ�
		int even = 0, odd = 0;
		for (int i = 0; i < N; i++) {
			int diff = max - arr[i]; // ū ������ ���� ������ ����
			if (diff == 0) continue; // �̹� ���� ū ����
			even += diff / 2; // 2�� ����
			odd += diff % 2; // 1�� ���� -> 1��, 0��
		}

		// 2 -> 1�� ����
		if (even > odd) { // 2�� ������ 1�� �������� ������ ���� ����
			while (Math.abs(even - odd) > 1) { // 2�� 1�� ������ �ִ��� �����ش�.
				even--;
				odd += 2;
			}
		}

		int result = 0; // ��� ��
		if (odd > even) { // 1�� ���� ���
			result = odd * 2 - 1;
		} else if (even > odd) { // 2�� ���� ���
			result = even * 2;
		} else { // 1�� 2�� ���ڰ� ���� ���
			result = odd + even;
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			bw.write("#" + t + " " + getDay() + "\n");
		}
		bw.flush();
		bw.close();
	}
}
