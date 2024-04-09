import java.util.*;
import java.io.*;
public class Main {

	static int n;
	static int m;
	static int h;
	static int k;
	static int[][] map;
	static Point[] runners;
	static int[] dir;
	static boolean[] isDead;
	static int[][] suleForward;
	static int[][] suleBackward;
	static boolean forward;
	static int[] dr = {-1,0, 1, 0};
	static int[] dc = {0,1, 0, -1}; // 상 우 하 
	static int point;
	
	
	
	// 0 -> 
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] line  = bf.readLine().split(" ");
		n = Integer.parseInt(line[0]); // 격자크기  - 홀수
		m = Integer.parseInt(line[1]); // 도망자수
		h = Integer.parseInt(line[2]); // 나무 수
		k = Integer.parseInt(line[3]); // 턴 수
		map = new int[n+1][n+1];
		runners = new Point[m+1];
		dir = new int[m+1];
		isDead = new boolean[m+1];
		forward = true;
		point = 0;
		
		
		for(int i=1; i<=m; i++) {
			String[] row  = bf.readLine().split(" ");
			
			if(row[2].equals("1")) dir[i] = 1; // 좌우 - 오른쪽부
			else dir[i] = 2; // 상하 - 아래쪽부터

			runners[i] = new Point(Integer.parseInt(row[0]), Integer.parseInt(row[1]));	
			
			
		}
		
		for(int i=1; i<=h; i++) {
			String[] row  = bf.readLine().split(" ");
			int r = Integer.parseInt(row[0]);
			int c = Integer.parseInt(row[1]);
			map[r][c] = 1;		
		}
	

		
		
		// 술래 방향 저장
		makeSuleMap();
		
//		for(int i=1; i<=n; i++) {
//			for(int j=1; j<=n; j++) {
//				System.out.print(+suleForward[i][j] + " ");
//			}
//			System.out.println();
//		}
//		
		// 술래 처음 위치
		Point sule = new Point((n+1)/2, (n+1)/2);
		

		for(int t=1; t<=k; t++) { // k번 턴
			//System.out.println("====" +t + "=========");
			
			// 1. m명의 도망자 이동
			for(int i=1; i<=m ; i++) {
				if(isDead[i] || sule.getDist(new Point(runners[i].r, runners[i].c)) > 3) continue;
				
				int nextR = runners[i].r + dr[dir[i]];
				int nextC = runners[i].c + dc[dir[i]];
				Point next = new Point(nextR, nextC);
				
				if(!next.inRange()) { // 범위 벗어나면 방향꾸기
					switch(dir[i]) {
						case 1 : dir[i] = 3; break;
						case 2 : dir[i] = 0; break;
						case 3 : dir[i] = 1; break;
						case 0 : dir[i] = 2;
					}
				
					nextR = runners[i].r + dr[dir[i]];
					nextC = runners[i].c + dc[dir[i]];
					next = new Point(nextR, nextC);
				}
				
			
				if(next.inRange()) {
					if(nextR == sule.r && nextC == sule.c) continue;
					
					runners[i].r = nextR;
					runners[i].c = nextC;
					
				}		
				
			}
			
//			for(int i=1; i<=m ; i++) {
//				System.out.println(i + "번 도망 " + runners[i].r + " " + runners[i].c);
//			}
			
			//2. 술래이동
			int dir = getSuleDir(sule);
				
			sule.r += dr[dir];
			sule.c += dc[dir];
			sule = new Point(sule.r, sule.c);
				
			checkDirChange(sule.r, sule.c); // 전방향 역방향 결정
			
			//System.out.println("술래 : " + sule.r + " "+ sule.c);
		
			
	
			//3. 도망자 잡기 및 포인트 획득
			
			int changedDir = getSuleDir(sule);
				
			for(int j=1; j<=m ; j++) {
				if(isDead[j]) continue;
			
				for(int i=0; i<3; i++) { // 3칸
					int nextR = sule.r + dr[changedDir] * i;
					int nextC = sule.c + dc[changedDir] * i;
					//System.out.println("++ " + nextR + " "+ nextC);
					
					if(map[nextR][nextC] == 1) continue;

					//System.out.println(j + " runnder : " + runners[j].r +" " + runners[j].c);
			
					if(runners[j].r == nextR && runners[j].c == nextC){
						//System.out.println("###" + nextR + " " + nextC);
						isDead[j] = true;
						point += t;
					}
				}
			
			}
			//System.out.println(t+"번 턴 : " + point);
		}
		System.out.println(point);
	
		
	}
	
	
	private static void checkDirChange(int suleR, int suleC) {
		if(suleR == 1 && suleC == 1) {
			forward = false;
		}else if(suleR ==(n+1)/2 && suleC ==(n+1)/2) {
			forward = true;
		}
	}
	

	private static int getSuleDir(Point sule) {
		if(forward) {
			return suleForward[sule.r][sule.c];
		}else {
			return suleBackward[sule.r][sule.c];
		}
		
	}


	private static void makeSuleMap() {
		suleForward = new int[n+1][n+1];
		suleBackward = new int[n+1][n+1];
		int times = 1;
		int startR =(n+1)/2;
		int startC =(n+1)/2;
		int sd = 0;
	
		
		int sr = startR;
		int sc = startC;
	
		
		while(!(sr == 0 && sc ==1)) {
			for(int i=1; i <= times; i++) {
				
				//System.out.println("sr: "+ sr + " sc: "+ sc + " " + suleForward[3][3]);
				
				suleForward[sr][sc] = sd;
				suleBackward[sr][sc] = (sd > 1)? sd-2 : sd+2;
				
				sr += dr[sd];
				sc += dc[sd];
			}
			
			sd = (sd+1) % 4;
			
			if(sd == 0 || sd == 2 ) { // 바꾼 방향이 상 하 일때
				times++;
			}
			
		}
	}
	
	static class Point {
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		
		public int getDist(Point p) {
			return Math.abs(this.r-p.r) + Math.abs(this.c-p.c);
		}
		
		public boolean inRange() {
			return this.r >=1 && this.r <= n && this.c >= 1 && this.c <= n;
		}
		
		
	}
	
	
	
}