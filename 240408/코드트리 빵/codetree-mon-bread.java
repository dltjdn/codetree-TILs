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
	    Point[] starts = new Point[m+1];
	    List<Point> baseCamps = new ArrayList<>();
	 
	    
	    boolean[] isReach = new boolean[m+1];
	    int[][] map = new int[n+1][n+1];
	   
		
	    int baseCampLen = 0;
		for(int i=1; i<=n; i++) {
			String[] row = bf.readLine().split(" ");
			for(int j=1; j<=n; j++) {
				if(row[j-1].equals("1")) {
					baseCamps.add(new Point(i,j));
					map[i][j] = 1;
				}
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
					
					Point b= starts[i];
					Point m = markets[i];
					
					Queue<Point> queue = new ArrayDeque<>();
					Map<Point, Boolean> visited = new HashMap<>();
					int[][] step = new int[n+1][n+1];
					
					queue.add(new Point(m.r, m.c)); // 편의점부터출
					visited.put(new Point(m.r,m.c), true);
					step[m.r][m.c] = 0;
					
					//System.out.println("11111");
					while(!queue.isEmpty()) {
						Point cur = queue.remove();
						//System.out.println("222222");
						
						for(int dir=0; dir<4; dir++) {
							int nextR = cur.r + dx[dir];
							int nextC = cur.c + dy[dir];
							Point next = new Point(nextR, nextC);
							
							if(next.inRange() && !visited.containsKey(next) && map[nextR][nextC] != -1) {
								queue.add(next);
								visited.put(next,true);
								step[next.r][next.c] = step[cur.r][cur.c]+1;
							}

						}

					}

					
					
					int minX = -1;
					int minY = -1;
					int minDist = Integer.MAX_VALUE;
					for(int dir=0; dir<4; dir++) {
						int nextR = b.r + dx[dir];
						int nextC = b.c + dy[dir];
						Point next = new Point(nextR, nextC);
						//System.out.println("3333");
						if(next.inRange() && visited.containsKey(next) && step[nextR][nextC] < minDist) {
							minDist = step[nextR][nextC];
							minX = nextR;
							minY = nextC;
						}
					}
					
					
					starts[i] = new Point(minX, minY);

					
				}
				
				// for(int i=1; i<Math.min(time,m+1); i++) {
				// 	System.out.println("r: "+ starts[i].r + " c: "+ starts[i].c);
				// }
			
				// 2. 편의점에 도달  +  해당 칸 이동 불가능
				for(int i=1; i<Math.min(time,m+1); i++) {
					if(isReach[i]) continue;
					if(starts[i].r == markets[i].r && starts[i].c == markets[i].c) {
						map[starts[i].r][starts[i].c] = -1;
						isReach[i] = true;
						reachCnt++;
						
					}
				}
				
				if(reachCnt == m) break; //모두 편의점 도달하면 종
				//System.out.println("Reach : "+reachCnt);
	
			}
		
			
			// 3. t번 사람 가까 있 베이스캠프 배정  + 해당 칸 이동불가능	
			if(time <= m) {
				
				Point m = markets[time];
				
				Queue<Point> queue = new ArrayDeque<>();
				Map<Point, Boolean> visited = new HashMap<>();
				int[][] step = new int[n+1][n+1];
				
				queue.add(new Point(m.r, m.c)); // 편의점부터출
				visited.put(new Point(m.r,m.c), true);
				step[m.r][m.c] = 0;
				
				while(!queue.isEmpty()) {
					Point cur = queue.remove();
					
					for(int dir=0; dir<4; dir++) {
						int nextR = cur.r + dx[dir];
						int nextC = cur.c + dy[dir];
						Point next = new Point(nextR, nextC);
						
						if(next.inRange() && !visited.containsKey(next) && map[nextR][nextC] != -1) {
							queue.add(next);
							visited.put(next,true);
							step[next.r][next.c] = step[cur.r][cur.c]+1;
						}

					}

				}

				
				int minDist = Integer.MAX_VALUE;
				int minX= -1, minY = -1;
				for(Point b : baseCamps){
					
					if(map[b.r][b.c] == -1) continue;
					
					if(visited.containsKey(b) && step[b.r][b.c] < minDist) {
						minDist = step[b.r][b.c];
						minX = b.r;
						minY = b.c;
					}

	
				}
				//System.out.println("??"+ minX + " "+minY);
	
				starts[time] = new Point(minX, minY);
				map[minX][minY] = -1;
				
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