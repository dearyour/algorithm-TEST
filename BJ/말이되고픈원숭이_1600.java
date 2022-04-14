package BJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ���̵ǰ��¿�����_1600 {

// �������� 3ĭ  ->������1ȸ 
// ������ ������ 1ĭ  ->������1ȸ
//��������Ƚ���� ����ġ (ĭ�� ����� )
// ����(����) -> ��(����)  ���������� �ּ� Ƚ��  
//��������Ƚ�� k��
// r,c �湮
	// �� ������ ����Ѱ�� 
	// ������� �ʴ°�� 
	// �����ϱ����ؼ� �湮 visit�� 3�������� �����Ѵ� . 
	
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] jump = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } }; // �����ʸ��� ����
																														// �ð���� ��������
	static int key, col, row;
	static int[][] map;
	static boolean[][][] visit;
	
	static public class Monkey {
		int x;
		int y;
		int k;  
		int cnt;   
		
		// x��ǥ, y��ǥ, ��ó�� �����ϼ��ִ� Ƚ��, �̵�  Ƚ��

		public Monkey(int k, int cnt, int x, int y) {
			this.k = k; 
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Queue<Monkey> queue = new LinkedList<>();

		key = sc.nextInt(); // �� ó�� ������ �� �ִ� Ƚ��
		row = sc.nextInt();
		col = sc.nextInt();

		map = new int[row][col];
		visit = new boolean[key + 1][row][col]; // key+1�� ������ key�� 2�϶� k=0,k=1,k=2 �ѹ����Ⱦ� key=0 �����ϴϱ�
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = sc.nextInt();  

			}
		} //�� �Է�

		visit[0][0][0] = true; // �湮ǥ��
		queue.add(new Monkey(0, 0, 0, 0)); // k�� 0�϶� ���� ����

		while (!queue.isEmpty()) {
			Monkey monkey = queue.poll();
			// �߰� ó��
			if (monkey.x == row-1 && monkey.y == col-1) { // ������ ���� �������� �� ��, 
				System.out.println(monkey.cnt);
				return;

			}

			for (int d = 0; d < 4; d++) { // 4�� ã�� 
				int nr = monkey.x + dr[d];
				int nc = monkey.y + dc[d];
				if (nr < 0 || nc < 0 || nr >= row || nc >= col)
					continue; // ��� 
				// ���ΰ��
				if (map[nr][nc] == 1)
					continue;  // �� 

				if (visit[monkey.k][nr][nc])
					continue; // �湮������

				// �鸰���� ���ų�, ���� �ƴѰ�쿡��
				visit[monkey.k][nr][nc] = true ; // �湮ó��
				queue.add(new Monkey(monkey.k, monkey.cnt+1, nr, nc)); // 
			}
			// 4�� ã�� ���� 
			// monkey.k �� �־��� Key�� ���ٸ� ���̻� ��ó�� ���� ���ϰ� ������ ó���� �ٰԵȴ�
			if ( monkey.k == key) continue;

			for (int d = 0; d < 8; d++) {
				int nr = monkey.x + jump[d][0];
				int nc = monkey.y + jump[d][1];
				
				if (nr < 0 || nc < 0 || nr >= row || nc >= col)
					continue; //���
				
				if (map[nr][nc] == 1)
					continue; // ���ΰ��
				
				if (visit[monkey.k+1][nr][nc])
					continue; // �湮������ �н�
				//����, ���ƴϰ�, �湮���Ѱ�
				// visit 3���� ���� ������ ���� ������ �������� ���� ����
				// ������ ��ó�� �ٶپ���� k=3�̶�  ���� �����ϳ����� ��ó�� �پ���� k=2�̶� ���� 
				visit[monkey.k+1][nr][nc] = true ; // �湮ó��
				queue.add(new Monkey(monkey.k+1, monkey.cnt+1, nr, nc)); 
				//��ó�� �ٸ� k�� �ϳ� ���°Ŵϱ� k+1 ���ش� 

			}

		}
		
	}
}

/*
1
4 4
0 0 0 0
1 0 0 0
0 0 1 0
0 1 0 0 
*/