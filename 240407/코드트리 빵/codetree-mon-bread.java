import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] line  = bf.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		int time = 1;
		int reachCnt = 0;
	    Point[] markets = new Point[m+1];
	    Point[] baseCamps = new Point[m+1];
	    boolean[] isReach = new boolean[m+1];
	    int[][] map = new int[n+1][n+1];
	   
		
		for(int i=1; i<=n; i++) {
			String[] row = bf.readLine().split(" ");
			for(int j=1; j<=n; j++) {
				if(row[j-1].equals("1")) map[i][j] = 1;
			}
		}
		
		for(int i=1; i<=m; i++) {
			String[] row = bf.readLine().split(" ");
			markets[i] = new Point(Integer.parseInt(row[0]), Integer.parseInt(row[1]));
		
		}
		
		int[] dx = {-1,0,0,1};
		int[] dy = {0,-1,1,0};		
		
		while(true) {
			//System.out.println("======="+time+"======");

			// 1. 격자에 있는 사람 "모두 " 편의점 방향으로 +1
			
			if(time>1) {
				for(int i=1; i<Math.min(time,m+1); i++) { // 격자에 있는 사람모두
					
					if(isReach[i]) continue;//이미 편의점 도달한 사람제
					
					Point b= baseCamps[i];
					Point m = markets[i];
					
					int minDir = -1;
					int minDist = b.getDist(m.r, m.c);
					for(int dir=0; dir<4; dir++) {
						int nextR = b.r + dx[dir];
						int nextC = b.c + dy[dir];
						Point next = new Point(nextR, nextC);
						
						if(!next.inRange() || map[nextR][nextC] == -1) continue;
						
						int dist = next.getDist(m.r,m.c); 
						
						if(dist < minDist) {
							minDir = dir;
							minDist = dist;
						}
						
					}
					
					if(minDir != -1) {
						baseCamps[i].r += dx[minDir];
						baseCamps[i].c += dy[minDir];
					}
					
				}
//				
//				for(int i=1; i<Math.min(time,m+1); i++) {
//					System.out.println("r: "+ baseCamps[i].r + " c: "+ baseCamps[i].c);
//				}
//				
				// 2. 편의점에 도달  +  해당 칸 이동 불가능
				for(int i=1; i<Math.min(time,m+1); i++) {
					if(isReach[i]) continue;
					if(baseCamps[i].r == markets[i].r && baseCamps[i].c == markets[i].c) {
						map[baseCamps[i].r][baseCamps[i].c] = -1;
						isReach[i] = true;
						reachCnt++;
						
					}
				}
				
				if(reachCnt == m) break; //모두 편의점 도달하면 종
				//System.out.println("Reach : "+reachCnt);
				
				
				
			}
		
			
			// 3. t번 사람 가까 있 베이스캠프 배정  + 해당 칸 이동불가능
			
			if(time <= m) {
				int closestDist = n+n;
				int closestR = n;
				int closestC = n;
				
				for(int i=1; i<=n; i++) {
					for(int j=1; j<=n; j++) {
						if(map[i][j] == 0 || map[i][j] == -1) continue; 
						
						Point p = markets[time];
						int dist = p.getDist(i,j);
						
						if(dist < closestDist) {
							closestDist = dist;
							closestR = i;
							closestC = j;
						}

					}
				}
				baseCamps[time] = new Point(closestR, closestC);
				map[closestR][closestC] = -1;
				
			}
		
			
			
			//4. 시간 증가
			time++;
					
		}
			
			
			
			
		System.out.println(time);
				
			
	}
		
		
		
	
	
	
	static class Point{
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
		
		
		public int getDist(int r, int c) {
			return Math.abs(this.r-r) + Math.abs(this.c-c);
		}
		
		public boolean inRange() {
			return this.r >=1 && this.r <= n && this.c >= 1 && this.c <= n;
		}
	}
	
	static class Vertex implements Comparable<Vertex>{
		int d;
		int r;
		int c;
		
		public Vertex(int d, int r, int c) {
			this.d= d;
			this.r=r;
			this.c=c;
		}
		
		@Override
		public int compareTo(Vertex v) {
			if(this.d != v.d) {
				return Integer.compare(this.d, v.d);
				
			}else if(this.r != v.r) {
				return Integer.compare(this.r , v.r);
				
			}else {
				return Integer.compare(this.c, v.c);
			}
		}
		
		
	}
	
}