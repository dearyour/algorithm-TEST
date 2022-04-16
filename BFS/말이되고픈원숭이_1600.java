package BJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 말이되고픈원숭이_1600 {

// 말움직임 3칸  ->움직임1회 
// 원숭이 움직임 1칸  ->움직임1회
//움직임의횟수가 가중치 (칸수 상관무 )
// 시작(왼위) -> 끝(오아)  도착지가는 최소 횟수  
//말움직임횟수 k번
// r,c 방문
	// 말 움직임 사용한경우 
	// 사용하지 않는경우 
	// 구분하기위해서 방문 visit를 3차원으로 관리한다 . 
	
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] jump = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } }; // 오른쪽맨위 시작
																														// 시계방향 말움직임
	static int key, col, row;
	static int[][] map;
	static boolean[][][] visit;
	
	static public class Monkey {
		int x;
		int y;
		int k;  
		int cnt;   
		
		// x좌표, y좌표, 말처럼 움직일수있는 횟수, 이동  횟수

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

		key = sc.nextInt(); // 말 처럼 움직일 수 있는 횟수
		row = sc.nextInt();
		col = sc.nextInt();

		map = new int[row][col];
		visit = new boolean[key + 1][row][col]; // key+1인 이유는 key가 2일땐 k=0,k=1,k=2 한번도안쓸 key=0 존재하니까
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = sc.nextInt();  

			}
		} //맵 입력

		visit[0][0][0] = true; // 방문표시
		queue.add(new Monkey(0, 0, 0, 0)); // k가 0일때 부터 시작

		while (!queue.isEmpty()) {
			Monkey monkey = queue.poll();
			// 추가 처리
			if (monkey.x == row-1 && monkey.y == col-1) { // 목적지 끝에 도달했을 때 끝, 
				System.out.println(monkey.cnt);
				return;

			}

			for (int d = 0; d < 4; d++) { // 4방 찾자 
				int nr = monkey.x + dr[d];
				int nc = monkey.y + dc[d];
				if (nr < 0 || nc < 0 || nr >= row || nc >= col)
					continue; // 경계 
				// 벽인경우
				if (map[nr][nc] == 1)
					continue;  // 벽 

				if (visit[monkey.k][nr][nc])
					continue; // 방문했으면

				// 들린적이 없거나, 벽이 아닌경우에는
				visit[monkey.k][nr][nc] = true ; // 방문처리
				queue.add(new Monkey(monkey.k, monkey.cnt+1, nr, nc)); // 
			}
			// 4방 찾은 다음 
			// monkey.k 가 주어진 Key와 같다면 더이상 말처럼 뛰지 못하고 원숭이 처럼만 뛰게된다
			if ( monkey.k == key) continue;

			for (int d = 0; d < 8; d++) {
				int nr = monkey.x + jump[d][0];
				int nc = monkey.y + jump[d][1];
				
				if (nr < 0 || nc < 0 || nr >= row || nc >= col)
					continue; //경계
				
				if (map[nr][nc] == 1)
					continue; // 벽인경우
				
				if (visit[monkey.k+1][nr][nc])
					continue; // 방문했으면 패스
				//경계안, 벽아니고, 방문안한곳
				// visit 3차원 으로 이전에 취한 원숭이 움직임의 종류 구분
				// 이전에 말처럼 다뛰어버린 k=3이랑  아직 찬스하나남은 말처럼 뛰어버린 k=2이랑 구분 
				visit[monkey.k+1][nr][nc] = true ; // 방문처리
				queue.add(new Monkey(monkey.k+1, monkey.cnt+1, nr, nc)); 
				//말처럼 뛰면 k를 하나 쓰는거니까 k+1 해준다 

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