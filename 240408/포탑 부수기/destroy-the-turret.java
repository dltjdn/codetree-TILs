import java.util.*;
import java.io.*;
public class Main {

	static int n;
	static int m;
	static int k;
	static int[][] map;
	static int[][] time;
	static boolean[][] isAttack;
	
	// 0 -> 
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] line  = bf.readLine().split(" ");
		n = Integer.parseInt(line[0]); // 격자크기 
		m = Integer.parseInt(line[1]); // 
		k = Integer.parseInt(line[2]); // 라운드 수
		map = new int[n+1][m+1];
		time = new int[n+1][m+1];
		
		
		for(int i=1; i<=n; i++) {
			String[] row  = bf.readLine().split(" ");
			for(int j=1; j<=m; j++) {
				map[i][j] = Integer.parseInt(row[j-1]);
			}	
		}
		
		
		for(int t=1; t<=k; t++) { //k번의 턴
			isAttack = new boolean[n+1][m+1];
			// 부서지지 않은 포탑 1개되면 즉시 종료
			
			// 1. 공격자 선정
			int attackR = 1;
			int attackC = 1;
			int attackPoint  = 5000;
			int attackTime = 1;
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=m; j++) {
					if(map[i][j] ==0 ) continue;
					Vertex best = new Vertex(attackPoint, attackTime, attackR, attackC);
					Vertex cur = new Vertex(map[i][j], time[i][j], i,j);
					
					if(cur.compareTo(best)<0) {
						attackR = cur.r;
						attackC = cur.c;
						attackPoint = cur.attackPoint;
						attackTime = cur.time;	
					}
				}
			}
		
			map[attackR][attackC] = map[attackR][attackC] + n + m;
			isAttack[attackR][attackC] = true;
			
			//System.out.println("공격자: "+ attackR + " "+ attackC + " "+ map[attackR][attackC]);
			
			
			// 2. 공격자의 공격
			// 2-1. 공격대상 정하기
			int victimR = n;
			int victimC = m;
			int victimPoint = 0;
			int victimTime = k;
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=m; j++) {
					if(i==attackR && j==attackC) continue;
					Vertex best = new Vertex(victimPoint, victimTime, victimR, victimC);
					Vertex cur = new Vertex(map[i][j], time[i][j], i, j);
					
					if(best.compareTo(cur)<0) {
						victimR = cur.r;
						victimC = cur.c;
						victimPoint = cur.attackPoint;
						victimTime = cur.time;	
					}
				}
			}
			//System.out.println("공격대상: " + victimR + " "+ victimC + " "+map[victimR][victimC]);
			
	
			if(!bfs( attackR, attackC, map[attackR][attackC], victimR, victimC)){ // 2-2.레이저 공격
				// 2-3.포탄 공격
				map[victimR][victimC] -= map[attackR][attackC];
				if(map[victimR][victimC] < 0 ) map[victimR][victimC] = 0;
				isAttack[victimR][victimC] = true;
				
				int[] dx = {0, 1, 0, -1, 1, 1, -1, -1};
				int[] dy = {1, 0, -1, 0, 1, -1, 1, -1};
				
				for(int i=0; i<8; i++) {
					int nextR = victimR + dx[i];
					int nextC = victimC + dy[i];
					
					if(nextR==n+1) nextR = 1;
					else if(nextR == 0) nextR = n;
					
					if(nextC==m+1) nextC = 1;
					else if(nextC == 0) nextC = m;
					
					Point next = new Point(nextR,nextC);
	
					if(next.inRange() && map[nextR][nextC] != 0){	
						map[next.r][next.c] -= map[attackR][attackC]/2;
						if(map[next.r][next.c] < 0 ) map[next.r][next.c] = 0;
						isAttack[next.r][next.c] = true;
					}

				}

			}
			
			// 4. 포탑 정비
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=m; j++) {	
					if( map[i][j] != 0 && !isAttack[i][j] ) {
						map[i][j] += 1;
					}
				}
			}
			
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {	
				if( map[i][j] != 0) {
					max = Math.max(max, map[i][j]);
				}
			}
		}
		System.out.println(max);
		
		
	}
	
	static boolean bfs(int attackR, int attackC, int attackPoint, int victimR, int victimC) {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n+1][m+1];
		
		int[][] backX = new int[n+1][m+1];
		int[][] backY = new int[n+1][m+1];
	
		
		queue.add(new Point(attackR, attackC));
		visited[attackR][attackC] = true;
	
		while(!queue.isEmpty()) {
			Point cur = queue.remove();
			
			if(cur.r == victimR && cur.c == victimC) break;
			
			for(int i=0; i<4; i++) {
				int nextR = cur.r + dx[i];
				int nextC = cur.c + dy[i];
				
				if(nextR==n+1) nextR = 1;
				else if(nextR == 0) nextR = n;
				
				if(nextC==m+1) nextC = 1;
				else if(nextC == 0) nextC = m;
				
				Point next = new Point(nextR,nextC);
				
				if(!visited[nextR][nextC] && map[nextR][nextC] != 0){	
					queue.add(next);
					visited[next.r][next.c] = true;
					
					// 이전 위치 기록
					backX[next.r][next.c] = cur.r;
					backY[next.r][next.c] = cur.c;
					//System.out.println("x : " + next.r+ " y : " + next.c  + " -> " + cur.r);
					//System.out.println("x : " + next.r+ " y : " + next.c  +  " -> " + cur.c); 
					//System.out.println("111: " + backX[next.r][next.c] + " " + backY[next.r][next.c]);

				}
			}
			
		}
		
		 
		if(!visited[victimR][victimC]) return false;
		
		map[victimR][victimC] -= attackPoint;
		if(map[victimR][victimC] < 0 ) map[victimR][victimC] = 0;
		isAttack[victimR][victimC] = true;
		
		int sr = backX[victimR][victimC];
		int sc = backY[victimR][victimC];
		//System.out.println("sr : " + sr + " sc: "+ sc);
		
		while(!(sr == attackR && sc == attackC)){
			
			map[sr][sc] -= attackPoint/2;
			if(map[sr][sc] < 0 ) map[sr][sc] = 0;
			isAttack[sr][sc] = true;
			
			
			int backR = backX[sr][sc];
			int backC = backY[sr][sc];

			sr = backR;
			sc = backC;

		}
	
		
//		for(int i=1; i<=n; i++) {
//			for(int j=1; j<=m; j++) {	
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		return true;
		
	}
	
	
	static class Point{
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
		
		public boolean inRange() {
			return this.r>=1 && this.r<=n && this.c>=1 && this.c<=m;
		}
		
	
	}
	
	
	static class Vertex implements Comparable<Vertex>{
		int attackPoint; //공격력
		int time; // 가장 최근에 공격한 시간
		int r;
		int c;
		
		public Vertex(int attack, int time, int r, int c) {
			this.attackPoint = attack;
			this.time=time;
			this.r=r;
			this.c=c;
		}
		
		@Override
		public int compareTo(Vertex v) {
			if(this.attackPoint != v.attackPoint) {
				return Integer.compare(this.attackPoint, v.attackPoint);
			}else if(this.time != v.time) {
				return Integer.compare(v.time, this.time);
			}else if(this.r+this.c != v.r+v.c) {
				return Integer.compare(v.r+v.c, this.r+this.c);
			}else {
				return Integer.compare(v.c, this.c);
			}
		}
		

	}
	
	
	
}