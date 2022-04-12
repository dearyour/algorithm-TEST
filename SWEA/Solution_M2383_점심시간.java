package BJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;


public class Solution_M2383_���ɽð� {
	
	static class Person implements Comparable<Person>{
		int r,c, downCnt, status, time; //����ġ, ����ġ, ���������ִ� ��ܼ�, ������, �Ա����� �����ð� 
		
		public Person(int r ,int c ) {
			super();
			this.r=r;
			this.c=c;
			
		}
		public void init() {
			time = downCnt= 0;
			status=M;
		}
		@Override
		public int compareTo(Person o) {
			return this.time - o.time;// ����Ա����� �ҿ�Ǵ� �ð��� ���� ������
		}
	}
	static final int M=1, W=2, D=3, C=4 ; // Move Wait Down Complete
	static int N,min , cnt ; // �Ѻ��� ����, ��ΰԴ��� �������� �ּҽð�, �����
	static boolean[] selected; // �κ������� �������� ����� ���ù迭 (���õǸ� ���1, �����̾ȵǸ� ���2)
	static ArrayList<Person> pList; //�������Ʈ
	static int[][] sList;	//��ܸ���Ʈ  
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in =  new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine().trim());
		
		
		for (int tc =1; tc<= TC; ++tc) {
			N = Integer.parseInt(in.readLine());
			StringTokenizer st = null;
			
			pList = new ArrayList<Person>();
			sList = new int[2][];
			min = Integer.MAX_VALUE;
			
			for (int i=0 , k=0; i< N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j =0; j <N; j++) {
					int c = Integer.parseInt(st.nextToken());
					if(c==1) pList.add(new Person(i,j)); //����̸� 
					else if(c>1) sList[k++] = new int[] {i,j,c}; // ����̸� 
				}
			};
			cnt = pList.size(); // ��� ��
			selected = new boolean[cnt];
			divide(0); //��ܹ����ϱ�
			System.out.println("#"+tc +" "+min);
			
		}
	}
	// �κ� ������ �̿��ؼ� ��� ���� 
	private static void divide(int index) { // index : ó���� ����� �ε���  
		
		if(index ==cnt ) {
			makeList();
			return; 
		}
		
		// �κ� ���տ� ���� : ��� 1�� ���� 
		selected[index] =true;
		divide(index+1);
		// �κ� ���տ� ������  : ��� 2�� ���� 
		selected[index] =false;
		divide(index+1);
		
	}
	
	private static void makeList() { // selected ���¿� ���� �� ����� �̿��ϴ� ������ ����Ʈ ����
		ArrayList<Person> aList = new ArrayList<Person>();
		ArrayList<Person> bList = new ArrayList<Person>();
		
		for(int i = 0 ; i< cnt; i++) {
			Person p = pList.get(i); //�����ִ� �������Ʈ���� ���������� �Ѹ�������
			p.init(); // time , downCnt, status �� �ʱ�ȭ 
			if(selected[i]) {
				p.time = Math.abs(p.r - sList[0][0]) + Math.abs(p.c - sList[0][1]);
				aList.add(p);
			}else {
				p.time = Math.abs(p.r - sList[1][0]) + Math.abs(p.c - sList[1][1]);
				bList.add(p);
			}
		}
		int res = go(aList, bList); // �� ��� ����Ʈ�� ������� ��� �������µ� �ҿ�Ǵ� �ð�
		if (min>res)  min = res;
	
	}
		
		private static int go (ArrayList<Person> aList, ArrayList<Person> bList) {
			int timeA=0 , timeB=0;
		if(aList.size()>0) timeA =goDown(aList, sList[0][2]);
		if(aList.size()>0) timeB =goDown(aList, sList[1][2]);
		
		return timeA>timeB? timeA: timeB;
	}

	
	private static int goDown(ArrayList<Person> list, int height) {
		Collections.sort(list); //����Ա����� �����ϴµ� �ҿ�Ǵ� �ð��� ���� ������ ����
		
		int time =  list.get(0).time; //ù��° ����� ����Ա� �����ð����� ó�� 
		int size =  list.size();
		int ingCnt = 0, cCnt = 0;
		while(true) {
			//�� �ð����� ������� �ϳ��� ������ ���¸� ó��
			for (int i = 0; i <size; i++) {
				Person p = list.get(i);
				
				if(p.status ==C ) continue;
				
				if(p.time ==time ) { //���� �ð��� ����� ����Ա� �����ð��� ������
					p.status =W;
				}else if(p.status == W && ingCnt<3) {
				p.status=D;
				p.downCnt=1; //��� 1���� �߶�
			++ingCnt;
				}else if(p.status ==D) {
					if(p.downCnt < height) { // �� �������� ���ѻ��
						p.downCnt++;
					}else { // �� ������ ���
						p.status=C;
						++cCnt;;
						--ingCnt;;
					}
				}
			
				}
			if(cCnt==time) break;
			++time;
		}		
		return time; 
	}
}
	
	
