import java.util.*;
import java.io.*;
public class Main {

	static int n;
	static int m;
	static int[][] map;
	static int[][] forward;
	static int point;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] line = bf.readLine().split(" ");
		n = Integer.parseInt(line[0]); // 격자의 크
		m = Integer.parseInt(line[1]); // 총 라운드 수
		map = new int[n][n];
		forward = new int[n][n];
		
		for(int i=0; i<n; i++) {
			String[] row = bf.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(row[j]);
			}
		}
		
		// 방방 격자 만들
		makeDirMap();
		
//		System.out.println();
//		
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<n; j++) {
//				System.out.print(forward[i][j]+ " ");
//			}
//			System.out.println();
//		}
//		
//		System.out.println();
//	
		
		
		for(int t=0; t<m; t++) {
			String[] row = bf.readLine().split(" ");
			int d = Integer.parseInt(row[0]);
			int p = Integer.parseInt(row[1]);
			

			// 1. 플레이어 공격 + 점수 얻기
			attack(d, p);
		
			// 2. 몬스터 앞으로 이동 (빈 공간 채우기)
			move();
			
//			System.out.println("2번 =============");
//			for(int i=0; i<n; i++) {
//				for(int j=0; j<n; j++) {
//					System.out.print(map[i][j]+ " ");
//				}
//				System.out.println();
//			}
//			System.out.println("2번 Point : " + point);
//			
			
			// while( 3. 같은 종류 4번 이상 삭제(BFS) + 점수 얻기
			//   &  2. 몬스터 앞으로 이동 ) - 4번이상 몬스터 없을때까지
			while(true) {
				if(!bfs()) break;
				
//				System.out.println("3번 전  =============");
//				for(int i=0; i<n; i++) {
//					for(int j=0; j<n; j++) {
//						System.out.print(map[i][j]+ " ");
//					}
//					System.out.println();
//				}
//				System.out.println("3번 Point : " + point);
//				
//		
//				
				move();
				
//				System.out.println("3번 후 =============");
//				for(int i=0; i<n; i++) {
//					for(int j=0; j<n; j++) {
//						System.out.print(map[i][j]+ " ");
//					}
//					System.out.println();
//				}
//				System.out.println("3번 Point : " + point);
////				
				
			}
		
			
			// 4. 차례대로 나열 후 다시 미로에 배치 (주의 - 기존 격자 범위 넘으면 나머지 무시)
			rebatch();
			
//			System.out.println("4번 =============");
//			for(int i=0; i<n; i++) {
//				for(int j=0; j<n; j++) {
//					System.out.print(map[i][j]+ " ");
//				}
//				System.out.println();
//			}
			
		
			//System.out.println(point);
		}
		
		System.out.println(point);
			
	
	}

	private static void rebatch() {
		int[] dr = {0, 1 ,0, -1};
		int[] dc = {-1, 0, 1, 0}; // 좌 하 우 상 
		
		int sr = n/2;
		int sc = n/2;
		List<Integer> temps = new ArrayList<>();
		List<Integer> results = new ArrayList<>();
		
		while(true) {
			if(results.size() > n*n-1) break;
			
			int d1= forward[sr][sc];
			sr += dr[d1];
			sc += dc[d1];
			
		
			
			if(!temps.isEmpty() && map[sr][sc] != temps.get(temps.size()-1)) {
				results.add(temps.size());
				results.add(temps.get(0));
				
				temps = new ArrayList<>();
			}
		
			temps.add(map[sr][sc]);
			
			if( map[sr][sc] == 0) break;
		}
		
		
			
		
		map = new int[n+1][n+1];
		int sr2 = n/2;
		int sc2 = n/2;

		for(int result : results) {
			int d2 = forward[sr2][sc2];
			sr2 += dr[d2];
			sc2 += dc[d2];
			
			if(!new Point(sr2, sc2).inRange()) break;
				
			map[sr2][sc2] = result;
				
		}

	
	}

	private static void attack(int d, int p) {
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0}; // 우 하 좌 상
		
		int sr = n/2;
		int sc = n/2;
		
		for(int i=0; i<p; i++) {
			sr += dx[d];
			sc += dy[d];
			Point next = new Point(sr,sc);
			
			if(next.inRange() && map[next.r][next.c] != 0) {
				point += map[next.r][next.c];
				map[next.r][next.c] = 0;
			}
		}
	}
	
	public static void move() {
		int[] dr = {0, 1 ,0, -1};
		int[] dc = {-1, 0, 1, 0}; // 좌 하 우 상 
		
		int lastR = -1;
		int lastC = -1;

		int tr = n/2;
		int tc = n/2;
		while(tr >= 0 && tr < n && tc >= 0 && tc < n) {
			int d = forward[tr][tc];
			tr += dr[d];
			tc += dc[d];
			
			if(map[tr][tc] == 0) {
				lastR = tr;
				lastC = tc;
				break;
			}
			
		}
		
		if(lastR==-1 && lastC == -1) return; // 빈칸 없을 
		
		int sr = lastR;
		int sc = lastC;
		//System.out.println("start : " + sr + " "+sc);
	
		while(true) {
			int dir = forward[sr][sc];
			sr += dr[dir];
			sc += dc[dir];
			
			if(!(sr >= 0 && sr < n && sc >= 0 && sc < n)) break;
			
			if(map[sr][sc] != 0) {
				
				
				map[lastR][lastC] = map[sr][sc];
				map[sr][sc] = 0;
				
				//System.out.println("last 1  : " + lastR + " " + lastC);
				
				//System.out.println("dr : " +  lastR + dr[forward[lastR][lastC]] + " dc: " +dc[forward[lastR][lastC]] );
				
				int d = forward[lastR][lastC];
				lastR += dr[d];
				lastC += dc[d];
		
				
				//System.out.println("last 2  : " + lastR + " " + lastC);
				
			}
			
		}
		
		
		
		
	}
	
	public static boolean bfs() {
		int[] dr = {0, 1 ,0, -1};
		int[] dc = {-1, 0, 1, 0}; // 좌 하 우 상 
		
		int sr = n/2;
		int sc = n/2;
		List<Point> temps = new ArrayList<>();
		boolean flag= false;
		
		while(true) {
			
			int d1= forward[sr][sc];
			sr += dr[d1];
			sc += dc[d1];
			
			if(!temps.isEmpty() && map[sr][sc] != map[temps.get(temps.size()-1).r][temps.get(temps.size()-1).c]) {
				if(temps.size() >= 4) {
					flag= true;
					for(Point temp : temps) {
						point += map[temp.r][temp.c];
						map[temp.r][temp.c] = 0;
					}
				}
				
				temps = new ArrayList<>();
			}
		
			temps.add(new Point(sr, sc));
			
			if( map[sr][sc] == 0) break;
		}
		
		
		return flag;
		
		
	}
	
	public static void makeDirMap() {
		int[] dr = {0, 1 ,0, -1};
		int[] dc = {-1, 0, 1, 0}; // 좌 하 우 상 
		int sr = n/2;
		int sc = n/2;
		int dir = 0;
		int times = 0;
		
		
		outer: while(true) {
			for(int i=0; i<=times; i++) {
				forward[sr][sc] = dir;
				
				sr += dr[dir];
				sc += dc[dir];
				
				if((sr == 0 && sc == 0)) break outer;
			}
			
			if(dir == 1 || dir == 3) {
				times++;
			}
			
			dir++;
			if(dir==4) dir=0;
			
		}
		

		
	}
	
	static class Point{
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
		
		public boolean inRange() {
			return (this.r>=0 && this.r<n && this.c>=0 && this.c<n);
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof Point) {
				Point p = (Point) o;
				return p.r == this.r && p.c == this.c;	
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(r,c);
		}
	}
		
	
	
}