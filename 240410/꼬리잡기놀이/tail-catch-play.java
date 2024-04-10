import java.util.*;
import java.io.*;
public class Main {

	static int n;
	static int m;
	static int k;
	static int[][] map;
	static List<Point>[] v;
	static boolean[][] visited;
	static int[][] boardIdx;
	static int[] dr = new int[]{-1, 0, 1, 0};
	static int[] dc = new int[]{0, -1, 0, 1};
	static int[] tails;
	static int point;
	
	
	// 0 -> 
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] line  = bf.readLine().split(" ");
		n = Integer.parseInt(line[0]); // 격자크기
		m = Integer.parseInt(line[1]); // 팀 개수
		k = Integer.parseInt(line[2]); // 라운드 수
		map = new int[n+1][n+1];
		v = new ArrayList[m+1]; 
		visited = new boolean[n+1][n+1];
		boardIdx = new int[n+1][n+1];
		tails = new int[m+1];
		point = 0;
		
		
		for(int i=1; i<=m; i++) {
			v[i] = new ArrayList<>();
		}
		
		int idx = 1;
		for(int i=1; i<=n; i++) {
			String[] row = bf.readLine().split(" ");
			for(int j=1; j<=n; j++) {
				map[i][j] = Integer.parseInt(row[j-1]);	
				
				if(row[j-1].equals("1") ){
					v[idx++].add(new Point(i,j)); // 머리 
				}
				// 1-머리 2-중간 3-꼬리 4-나머지
			}
		}
		
		for(int i=1; i<=m; i++) {
			dfs(v[i].get(0).r , v[i].get(0).c, i); // 꼬리 배열 만들
		}
		
		
		for(int t=1; t<=k; t++) {
			System.out.println(t + "라운드======");
			
			// 1. 머리사람 따라서 한칸 이동
			moveAll();
		
			
//			System.out.println("----v----");
//			for(int j=1; j<=1; j++) {
//				for(int k =0; k < v[j].size(); k++) {
//					System.out.println(v[j].get(k).r+ " " + v[j].get(k).c);
//				}
//				System.out.println();
//
//			}
			
			System.out.println("-----map----");
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			
			// 2. 공 던지기 + 점수 얻기 + 머리 꼬리 바꾸기
			throwBall(t);
			

//			System.out.println("---v2----");
//			for(int j=1; j<=1; j++) {
//				
//				for(int k =0; k < v[j].size(); k++) {
//					System.out.println(v[j].get(k).r+ " " + v[j].get(k).c);
//				}
//				System.out.println();
//
//			}
			
			System.out.println("---map2----");
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			
			
			System.out.println(point);

		}
		
		System.out.println(point);

		
	}
	
	public static void throwBall(int t) {
		int temp = t % (4*n+1);
		
		
		int minTeam = -1;
		int minOrder = -1;
		
		
		if(temp <= n) { // -> 행이temp
			int minCol = Integer.MAX_VALUE;
			
			//System.out.println("?? " + temp);
			
			for(int i=1; i<=m; i++) {
				for(int j = 0; j<tails[i]; j++) {
					Point p = v[i].get(j);
					if(p.r == temp && p.c < minCol) {
						minCol = p.c;
						minTeam = i;
						minOrder = j;			
					}
					
				}
				
				
			}
			
			//System.out.println("minCol: " + minCol + " minTeam : " + minTeam + " minOrder : " + minOrder);
			
		}else if(temp <= 2*n ){ 
			
			int temp2 = temp % (n+1) + 1;
			
			int maxRow = Integer.MIN_VALUE;
			
			for(int i=1; i<=m; i++) {
				for(int j = 0; j<tails[i]; j++) {
					Point p = v[i].get(j);
					if(p.c == temp2 && p.r > maxRow) {
						maxRow = p.r;
						minTeam = i;
						minOrder = j;			
					}	
				}
			}			
		}else if(temp <= 3*n ){	
			int temp2 = temp % (2*n+1) + 1;
			int temp3 = n - temp2 + 1;
			int maxCol = Integer.MIN_VALUE;
			
			for(int i=1; i<=m; i++) {
				for(int j = 0; j<tails[i]; j++){
					Point p = v[i].get(j);
					if(p.r == temp3 && p.c > maxCol) {
						maxCol = p.c;
						minTeam = i;
						minOrder = j;			
					}
					
				}
			}	
		}else {
			int temp2 = temp % (3*n+1) + 1;
			int temp3 = n - temp2 + 1;
			int minRow = Integer.MAX_VALUE;
			
			for(int i=1; i<=m; i++) {
				for(int j = 0; j<tails[i]; j++) {
					Point p = v[i].get(j);
					if(p.c == temp3 && p.r < minRow) {
						minRow = p.r;
						minTeam = i;
						minOrder = j;			
					}
				}
			}
	
		}
		
		
		// 만나는 사람 1명이라도 있다면 -> 포인트획득 + 해당 팀 머리 꼬리 교환
		if(minTeam != -1) {
			point += (minOrder+1) * (minOrder+1);
		
			
			Point tail = v[minTeam].get(tails[minTeam]-1);
			Point head = v[minTeam].get(0);
			
			map[tail.r][tail.c] = 1;
			map[head.r][head.c] = 3;
			
			
			int cnt= 0;
			int endIndex = tails[minTeam]-1;
			List<Point> list = new ArrayList<>();
			while(list.size() < v[minTeam].size()) {
		
				Point next = v[minTeam].get(endIndex);
		
				list.add(next);
				
				if(endIndex == 0) {
					endIndex = v[minTeam].size()-1;
				}else {
					endIndex--;
				}

			}
			v[minTeam] = list;

		}
	
	}

	private static void moveAll() {
		for(int j=1; j<=m; j++) {
			// 각 팀에 대해 레일을 한 칸씩 뒤로 이
			Point tmp = v[j].get(v[j].size()-1);
			//Point tmp = v[j].get(tail[j]-1);
			
			for(int k = v[j].size()-1; k>=1; k--) {
				v[j].set(k, v[j].get(k-1));
			}
			v[j].set(0, tmp);
		}
		
		
		
		for(int j=1; j<=m ; j++) {
			for(int k=0; k<v[j].size(); k++) {
				Point p = v[j].get(k);
				
				if(k == 0) map[p.r][p.c] = 1;
				else if(k < tails[j]-1) map[p.r][p.c] = 2;
				else if(k == tails[j]-1) map[p.r][p.c] = 3;
				else map[p.r][p.c] = 4;
			}
		}
	}
	
	public static void dfs(int r, int c, int idx) {
		visited[r][c] = true;
		boardIdx[r][c] = idx;
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(!inRange(nc,nr) || map[nr][nc] == 0 || visited[nr][nc] ) continue;
			
			if(v[idx].size() == 1 && map[nr][nc] != 2 ) continue;
			
			v[idx].add(new Point(nr,nc));
			if(map[nr][nc] == 3) tails[idx] = v[idx].size(); // 꼬리
			dfs(nr, nc, idx);
		}
		
	}
	
	static public boolean inRange(int r, int c) {
		return r>=1 && r<=n && c>=1 && c<=n;
	}
	
	static class Point {
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}
	
}