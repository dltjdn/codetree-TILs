import java.util.*;
import java.io.*;

public class Main {

	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] line  = bf.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		int k = Integer.parseInt(line[2]);
		int totalMove= 0;
		
		int[][] map = new int[n+1][n+1];
		int[][] nextMap = new int [n+1][n+1]; // 회전
		List<Point> attends = new ArrayList<>();
		
		for(int i=1; i<=n; i++) {
			String[] row  = bf.readLine().split(" ");
			for(int j=1; j<=n ; j++) {
				map[i][j] = Integer.parseInt(row[j-1]);
			}	
		}
		
		for(int i=1; i<=m; i++) {
			String[] row  = bf.readLine().split(" ");
			int r = Integer.parseInt(row[0]);
			int c = Integer.parseInt(row[1]);
			attends.add(new Point(r,c));
		}
		
		String[] row  = bf.readLine().split(" ");
		int exitR = Integer.parseInt(row[0]);
		int exitC = Integer.parseInt(row[1]);
		Point exit = new Point(exitR, exitC);

		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1}; // 상하 먼
		
		
		for(int t=1; t<=k; t++) {
			if(attends.size() == 0) break;
			

			// 1.움직이ㅣ기
			List<Point> removeAttends = new ArrayList<>();
			
			for(Point attend: attends) {
					
				int minDir = -1;
				int minDist = exit.getDist(attend);
				
				
				for(int i=0; i<4; i++) { //출구와 가까워지는 이동방향 정하
					int nextR = attend.r + dx[i];
					int nextC = attend.c + dy[i];
					
					Point next = new Point(nextR, nextC);
			
					if(!next.inRange()|| map[nextR][nextC] > 0 ) continue;
						
					int dist = exit.getDist(next);
					
				
					if(dist < minDist) {
						//System.out.println("nextR "+ next.r + "nextC " + next.c);
						minDist = dist;
						minDir = i;
					}
				}
				
				if(minDir != -1) { // 이
			
					attend.r = attend.r + dx[minDir]; 
					attend.c = attend.c + dy[minDir]; 
			
					totalMove++;
					//System.out.println("attend: "+ attend.r+" " + attend.c);
					//System.out.println("exit: "+ exit.r+" " + exit.c);
					if(attend.r == exit.r && attend.c == exit.c) {
						removeAttends.add(attend);
					}
				}
					
			}
			
			for(Point removeAttend : removeAttends) {
				//System.out.println("remve " + removeAttend  );
				attends.remove(removeAttend);
			}
			
			for(Point attend: attends) {
				//System.out.println(t+"초 이동 후 : " + attend.r + attend.c);
				
			}
			//System.out.println(t+"초  출구  : " +exit.r + exit.c);
					
			
			// 2. 가장 작은 정사각형 찾기
			int squareSize = 0;
			int sr = 0;
			int sc = 0;
			outer: for(int d=2; d<=n; d++) { // 한변의 길이
				for(int x1=1; x1<=n-d+1; x1++) {
					for(int y1=1; y1<=n-d+1; y1++) {
						// 출구포함 하는지
						int x2 = x1 + d - 1;
						int y2 = y1 + d - 1;
						
						if(exit.r < x1 || exit.r > x2 || exit.c < y1 || exit.c > y2) {
							continue;
						}
						
						// 그 영역 안에 사람 있는지
						for(Point attend : attends) {
							if(attend.r >= x1 && attend.r <= x2 && attend.c >= y1  && attend.c <= y2) {
								squareSize = d;
								sr = x1;
								sc = y1;
								//System.out.println("attend " + attend.r + attend.c);
								break outer;
							}
						}
					}
				}
				
			}
			
			//System.out.println(t+"초 제일작은사각 " + squareSize + " "+ sr + " " + sc);
			
			// 3. 정사각형 내부의 벽을 회전시킨다
			for(int i=sr; i<sr+squareSize ; i++) {
				for(int j=sc; j<sc+squareSize; j++) {
					if(map[i][j] > 0 ) map[i][j]--;
				}
			}
			
			for(int i=sr; i<sr+squareSize ; i++) {
				for(int j=sc; j<sc+squareSize; j++) {
					int ox = i - sr;
					int oy = j- sc;
					
					int rx = oy;
					int ry = squareSize - ox - 1;
					
					nextMap[rx+sr][ry+sc] = map[i][j];
				}
			}
			
			for(int i=sr; i<sr+squareSize ; i++) {
				for(int j=sc; j<sc+squareSize; j++) {
					map[i][j] = nextMap[i][j];
				}
			}
			
			
			
			// 4. 모든 참가자 회전
			
			for(Point attend: attends) {
				int r= attend.r;
				int c = attend.c;
				
				
				if(sr > r || sr+squareSize <= r || sc > c || sc+squareSize <= c) continue;
				
				int ox = r-sr;
				int oy = c-sc;
				
				int rx = oy;
				int ry = squareSize - ox -1;
				
				
				attend.r = rx + sr;
				attend.c = ry + sc;
					
				
			}
			
//			for(Point attend: attends) {
//				System.out.println(t+"초 회전 후 : " + attend.r + attend.c);
//				
//			}
			
			
			

			
			// 5. 모든 출구회전	
			int er = exit.r;
			int ec = exit.c;
			if(sr > er || sr+squareSize <= er || sc > ec || sc+squareSize <= ec) continue;
			
			int ox = er - sr;
			int oy = ec - sc;
			
			int rx = oy;
			int ry = squareSize - ox -1;
			
			exit.r = rx + sr;
			exit.c = ry + sc;
			

			
		
			
		}
		
		
		System.out.println(totalMove);
		System.out.println(exit.r + " " + exit.c);
		
		

	}
	
	static class Point{
		int r;
		int c;
		
		public Point(int r,int c) {
			this.r = r;
			this.c = c;
		}
		
		public int getDist(Point p) {
			return Math.abs(this.r - p.r) + Math.abs(this.c - p.c);
		}
		
		public boolean inRange() {
			return this.r >=1 && this.r <=n && this.c>=1 && this.c<=n;
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof Point) {
				Point p = (Point) o;
				return this.r == p.r && this.c == p.c;
			}
			return false;
			
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(r,c);
		}
	}
	
	

}