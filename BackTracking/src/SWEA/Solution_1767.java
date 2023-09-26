package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1767 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, N, arr[][], connectCnt, processCnt, ansProcess, ansLine;	// �׽�Ʈ���̽� ��, �迭 ũ��, �迭, ����� ���μ��� ��, ���μ��� �� ��, ���� ���μ��� ��, ���� ���� ��
	static List<Process> pList;	// ���μ��� ��ġ ���� ���� ����Ʈ
	static int[] dx = { -1, 0, 1, 0 };	// �� �� �� ��
	static int[] dy = { 0, 1, 0, -1 };

	// ���μ��� ��ġ ���� �����ϱ� ���ϰ� ����� Ŭ����
	static class Process {
		int x, y;

		public Process(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// �ʱⰪ ����
	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		pList = new ArrayList<>();
		connectCnt = 0;
		processCnt = 0;
		ansLine = 0;
		ansProcess = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
				if (arr[i][k] == 1) {	// ���μ����� ���
					pList.add(new Process(i, k));	// ���μ��� ����Ʈ�� �װ�
					processCnt++;	// ���μ��� �� ���� ����
				}
			}
		}
	}

	// �迭 ���� �ִ��� Ȯ�� �ϴ� �޼ҵ� => �迭 ������ ������ ���� �Ǿ��ٴ� ������ ���
	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}

	// DFS
	public static void dfs(int cnt) {
		if (processCnt - cnt + connectCnt < ansProcess) {	// ����ġ��(���μ��� �� ���� - �˻��� ���μ��� ���� + ����� ���μ��� ���� < ������� ���� ���� ����Ǿ��� ���μ���)
			return;
		}
		if (cnt == processCnt) {	// ��� ���μ��� �� ������ ��
			int line = 0;	// ����� ���� �� �� ����
			for(int i = 0; i < N; i++) {
				for(int k = 0; k < N; k++) {
					if(arr[i][k] == 2) line++;	// ������ 2�� ��������
				}
			}
			if(connectCnt == ansProcess) {	// ����� ���� ���ݱ��� ����Ǿ��� ���μ��� ���� ���ٸ�
				ansLine = Integer.min(ansLine, line);	// ���� ������ �� ���� �ɷ� �ؾ��Ѵ�.
			} else if(connectCnt > ansProcess) {	// ����� ���� ���� ����Ǿ��� ���Ҵٰ� ������ ���μ��� ������ ũ�ٸ�
				ansProcess = connectCnt;	// ��ü �۾�
				ansLine = line;
			}
			return;
		}

		for (int d = 0; d < 4; d++) {	// 4�� Ž��
			int x = pList.get(cnt).x;	// ���� ���μ��� ��ġ
			int y = pList.get(cnt).y;
			int c = 0;	// ���� ������ ���ư��� ���� ���� �̸� �����ϱ� ���� ���� c => ���� �ϳ��ϳ� ����Ǵ� ������ ����
			int count = 1;	// ���� ĭ ã�� ���� ����
			boolean check = false;	// �ٸ� �����̶� �����ļ� ����ų�, ���μ����� �����ļ� ���� ��� üũ
			while (true) {
				int nx = x + dx[d] * count;	// ���� xĭ
				int ny = y + dy[d] * count++;	// ���� yĭ
				if (!isIn(nx, ny)) {	// ���� ĭ�� ��ǥ�� �迭 ���� ���̶�� ������ �帥��
					connectCnt++;	// ����Ǿ��ٴ� ��!!
					dfs(cnt + 1);	// dfs �ٷ� ���ֱ�!!(���� ������ �״�� �ΰ� �ؾ��Ѵ�!)
					connectCnt--;	// dfs ������ ������ ���� ī��Ʈ ���� ����
					break;	// ���߱�
				}
				if (isIn(nx, ny) && arr[nx][ny] == 0) {	// ������ ���� �� �ִ� ���¶��!
					c++;	// ���� ���� ����
					arr[nx][ny] += 2;	// ���� ����
				} else if (isIn(nx, ny) && arr[nx][ny] != 0) {	// ���� ���� �� ���� ����!
					check = true;	// ���� üũ true
					break;	// �ٷ� ���߱�
				}
			}
			for (int i = 1; i <= c; i++) {	// ���¿��� ��
				arr[x + dx[d] * i][y + dy[d] * i] -= 2;
			}
			if (check) {	// ���� ������ �� ��찡 �ƴ϶� dfs ���� �� �������ϱ� ���� �� �� �������� dfs ������.
				dfs(cnt + 1);
			}
			
			// ��Ʈ��ŷ
			// ���� �Ƴ�, �� �Ƴ�
			// �� �ΰ��� ���� dfs ���� ����.
		}
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			dfs(0);
			bw.write("#" + t + " " + ansLine + "\n");
		}
		bw.flush();
		bw.close();
	}
}