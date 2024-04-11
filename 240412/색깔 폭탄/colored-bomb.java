import java.util.*;
import java.io.*;
public class Main {

	static int n;
	static int m;
	static int[][] map;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] line = bf.readLine().split(" ");
		n = Integer.parseInt(line[0]); // 겪자 크기
		m = Integer.parseInt(line[1]); //폭탄종류 (빨강 이외)
		map = new int[n+1][n+1];
		int bombCnt = 0;
		int point = 0;
		
		for(int i=1; i<=n; i++) {
			String[] row = bf.readLine().split(" ");
			for(int j=1; j<=n; j++) {
				map[i][j] = Integer.parseInt(row[j-1]);
				if(!row[j-1].equals("-1")) bombCnt++;
			}
			
		}
		
		// 돌(-1), 빨강(0), 1,2,3...m 
		
		outer: while(bombCnt > 0) {
			
			
			// 1. 현재 겪자에서 크기가 가장 큰 폭탄 묶음 찾기
			// 크기 가장 큰 거 두 개 이상이면 우선순위에 따라 선택
			Vertex best = new Vertex(0, n*n, 1, n);
			
			Set<Point> select = new HashSet<>();
			
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(map[i][j] == -1 || map[i][j] == 0 || map[i][j] == -2) continue;
					
					Set<Point> bombs = bfs(i,j);
					
					if(bombs.size() < 2) continue;
					
				
					Vertex cur = getKijun(bombs);
						
					if(cur.compareTo(best) < 0) {
						best = cur;
						select = bombs;
					}
						
			
				}
					
			}
			
//			for(Point s : select) {
//				System.out.println("!!!!!! " + s.r + " "+ s.c);
//			}
			
			
			//if(best.bombSize == 0) break outer;

			
			// 2. 선택된 폭탄 제거 -> -2
			// 점수 얻, bombCnt 줄이
			if(select.isEmpty()) break outer;
			for(Point s: select) {
				map[s.r][s.c] = -2;
			}
			
			point += (select.size() * select.size());
			bombCnt -= select.size();
			
//			System.out.println("2이후 ======");
//			for(int i=1; i<=n; i++) {
//				for(int j =1; j<=n; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			
			
			// 3. 중력 작용 ( 돌에 걸림 )
			gravity();
			
//			System.out.println("3이후 ======");
//			for(int i=1; i<=n; i++) {
//				for(int j =1; j<=n; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			// 4. 시계방향 270도 회전
			int[][] rotate = new int[n+1][n+1];
			
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					int ox = i - 1, oy = j - 1;
					
					int rx = n - oy - 1, ry = ox;
					
					rotate[rx+1][ry+1] = map[i][j];		
				}
			}
			
			map = rotate;
			
//			System.out.println("4 이후======");
//			for(int i=1; i<=n; i++) {
//				for(int j =1; j<=n; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			// 5. 중력작용
			gravity();
			
//			System.out.println("5 이후======");
//			for(int i=1; i<=n; i++) {
//				for(int j =1; j<=n; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			
//			System.out.println(point);
			
		}
		
		System.out.println(point);
	
	
	}

	private static void gravity() {
		List<Integer> stoneRows = new ArrayList<>();
		for(int c=1; c<=n; c++) { // 1열 -> -> n열
			
			// stone에 돈 행 저
			for(int r=1; r<=n; r++) {
				if(map[r][c] == -1) { 
					stoneRows.add(r);
				}
			}
			

			if(stoneRows.size() == 0) { // 돌 없을 때
				for(int r=n-1; r>=1; r--) {
					if(map[r][c] != -2) { 
						while(true) {
							if(r >= n || map[r+1][c] >= 0) break;
							map[r+1][c] = map[r][c];
							map[r][c] = -2;
							r++;
						}
					}
				}
			}else {
				// 가장 아래 돌 ~ 바닥 이동
				int last1 = n+1;
				int start1 = stoneRows.get(stoneRows.size()-1)+1;
				
				for(int r=last1-2; r>=start1; r--) {
					if(map[r][c] != -2) { 
						int curR = r;
						while(true) {
							if(curR >= last1-1 || map[curR+1][c] >= 0) break;
							
							map[curR+1][c] = map[curR][c];
							map[curR][c] = -2;
							curR++;
						}
					}
				}
				
				
				// 천장 ~ 가장 아래 돌 이동
				for(int i=stoneRows.size()-1; i>=0; i--) {
					int last = stoneRows.get(i); // 가장 아래
					int start = (i==0)? 1 : stoneRows.get(i-1)+1;
					
					for(int r=last-2; r>=start; r--) {
						if(map[r][c] != -2) { 
							int curR = r;
							while(true) {
								if(curR >= last-2 || map[curR+1][c] >= 0) break;
								
								map[curR+1][c] = map[curR][c];
								map[curR][c] = -2;
								curR++;
							}
						}
					}
				}

			}	
			
		}
	}
	
	public static Vertex getKijun(Set<Point> bombs) {
		int redCnt = 0;
		Point best = new Point(0, n);
		for(Point cur : bombs) {
			if(map[cur.r][cur.c] == 0) {
				redCnt++;
			}else { // 행 가장 큰, 열 가장 작은 -> 기준
				if(cur.compareTo(best) < 0) {
					best = cur;
				}
				
			}
		}
		
		return new Vertex(bombs.size(), redCnt, best.r, best.c);
		
	}
	
	private static Set<Point> bfs(int startR, int startC) {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		// 기준점 행, 렬, 빨간폭탄 
		Queue<Point> queue = new ArrayDeque<>();
		Set<Point> visited = new HashSet<>();
		
		queue.add(new Point(startR, startC));
		visited.add(new Point(startR, startC));
		int color = map[startR][startC];
		
		while(!queue.isEmpty()) {
			Point cur = queue.remove();
			
			for(int i=0; i<4; i++) {
				Point next = new Point(cur.r + dx[i], cur.c + dy[i]);
				
				if(next.inRange() && !visited.contains(next)
						&& (map[next.r][next.c] == color || map[next.r][next.c] == 0) ) {
					queue.add(next);
					visited.add(next);
				}
			}
		}
		
		return visited;
	}
	

	
	
	static class Vertex implements Comparable<Vertex>{
		int bombSize;
		int redCnt;
		int r;
		int c;
		
		public Vertex(int bombSize, int redCnt, int r, int c) {
			this.bombSize = bombSize;
			this.redCnt = redCnt;
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int compareTo(Vertex v) {
			if(this.bombSize != v.bombSize) {
				return Integer.compare(v.bombSize, this.bombSize);
			}else if(this.redCnt != v.redCnt) {
				return Integer.compare(this.redCnt, v.redCnt);
			}else if(this.r != this.c) {
				return Integer.compare(v.r, this.c);
			}else {
				return Integer.compare(this.c, v.c);
			}
		}
		
	}
	
		
	static class Point implements Comparable<Point> {
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
		
		public boolean inRange() {
			return this.r >=1 && this.r<=n && this.c>=1 && this.c<=n;
		}
		
		@Override
		public int compareTo(Point p) {
			if(this.r != this.c) {
				return Integer.compare(p.r, this.c);
			}else {
				return Integer.compare(this.c, p.c);
			}
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