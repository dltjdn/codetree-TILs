import java.util.*;
import java.io.*;
public class Main {

	static int n;
	static int m;
	static int k;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1 };
	static Player[] players;
	static Gun[][] map;
	static int[] guns;
	
	// 0 -> 
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] line  = bf.readLine().split(" ");
		n = Integer.parseInt(line[0]); // 격자크기 
		m = Integer.parseInt(line[1]); // 플레이어 
		k = Integer.parseInt(line[2]); // 라운드 수
		map = new Gun[n+1][n+1];
		
		players = new Player[m+1];
		guns = new int[m+1];
		int[] points = new int[m+1];
		int[] startStat = new int[m+1];
		
		
		for(int i=1; i<=n; i++) {
			String[] row  = bf.readLine().split(" ");
			for(int j=1; j<=n; j++) {
				List<Integer> guns = new ArrayList<>();
				guns.add(Integer.parseInt(row[j-1]));
				map[i][j] = new Gun(guns);
			}
		}
		
		for(int i=1; i<=m; i++) {
			String[] row  = bf.readLine().split(" ");
			int x = Integer.parseInt(row[0]);
			int y = Integer.parseInt(row[1]);
			int d = Integer.parseInt(row[2]);
			int s = Integer.parseInt(row[3]);
			
			players[i] = new Player(new Point(x,y),d);
			startStat[i] = s;
		}
		
		for(int t=1; t<=k; t++) { // k번라운드만큼 진행
			//System.out.println(t+ " 라운드 =============================");
			
			for(int i=1; i<=m; i++) {//플레이어수만큼 이
				boolean isCrash = false;
				
				// 플레이어 이
				int nextR = players[i].p.r + dx[players[i].dir];
				int nextC = players[i].p.c + dy[players[i].dir];
				Point next = new Point(nextR, nextC);
				//System.out.println("!!"+nextR+" "+ nextC);
				
				if(!next.inRange()) { // 범위벗어나면 방향 반대로 
					if(players[i].dir < 2) {
						players[i].dir = players[i].dir + 2;
					}else {
						players[i].dir = players[i].dir - 2;
					}
					nextR = players[i].p.r + dx[players[i].dir];
					nextC = players[i].p.c + dy[players[i].dir];
				}
		
				
				players[i].p.r = nextR;
				players[i].p.c = nextC;
				//System.out.println("!!"+players[i].p.r+" "+ players[i].p.c);
				
				

				// 이동한 방향에 플레이어있는지??
				int idx = -1;
				for(int j=1; j<=m; j++) {
					if(i != j && players[j].p.r == players[i].p.r  && players[j].p.c == players[i].p.c) {
						isCrash = true;
						idx = j;
						break;
					}
				}
			
				int win = -1; // idx우승 1,i우승 2
				int lose = -1;
				if(isCrash) {//이동한 방향에 플레이어 있는 경우
					//승리자 구하
					if (guns[idx] + startStat[idx] == guns[i] + startStat[i]) {
						if(startStat[idx] < startStat[i]) {
							win = i;lose = idx;
						}
						else {
							win = idx; lose =i;
						}
					}else if(guns[idx] + startStat[idx] < guns[i] + startStat[i]){
						win = i; lose = idx;
					}else {
						win = idx; lose = i;
					}
					
					
					// 1. 이긴 사람 점수 획
					points[win] += (guns[win] + startStat[win]) - (guns[lose] + startStat[lose]);
					
					// 2. 진 사람 이동 및 총회
					// 총 내려놓기
					map[players[lose].p.r][players[lose].p.c].guns.add(guns[lose]);
					guns[lose] = 0;
					
					// 이동
					int nextRR = players[lose].p.r + dx[players[lose].dir];
					int nextCC = players[lose].p.c + dy[players[lose].dir];
					Point nextt = new Point(nextRR, nextCC);
						
					while(!nextt.inRange() || isCrashed(nextt)) { // 방향 틀어서 이해야
						if(players[lose].dir == 3) players[lose].dir = 0;
						else players[lose].dir = players[lose].dir + 1;
						
						int nextRRR = players[lose].p.r + dx[players[lose].dir];
						int nextCCC =  players[lose].p.c + dy[players[lose].dir];
						nextt = new Point(nextRRR, nextCCC);	
					}
						
					players[lose].p.r = nextt.r;
					players[lose].p.c = nextt.c;
						
					// 이동한 자리에서 총 획득
					getMaxGuns(lose);	
					
						
					// 3.이긴 사람 총 획득
					getMaxGuns(win);
					

				}else {// 이동한 방향에 플레이어 없는 

					getMaxGuns(i);

				}
				
				
//				for(int l=1; l<=m; l++) {
//					System.out.println(i +"번 이동 r: " + players[l].p.r + " c: "+ players[l].p.c + " gun: "+guns[l] + " point: " + points[l]);
//				}
				
		
				
			}
			
		}
		
		for(int i=1; i<=m; i++) {
			System.out.print(points[i]+" ");
		}
		
		
		
	}


	private static void getMaxGuns(int i) {
		List<Integer> gunList = map[players[i].p.r][players[i].p.c].guns;
		if(gunList.size() == 0) return;
		Collections.sort(gunList, Collections.reverseOrder());
		
		if(guns[i] < gunList.get(0)) {
			int temp = guns[i];		
			guns[i] = gunList.remove(0);
			gunList.add(temp);
		}
		
	}


	private static boolean isCrashed(Point next) {
		for(int j=1; j<=m; j++) {
			if(players[j].p.r == next.r  && players[j].p.c == next.c) {
				return true;
			}
		}
		return false;
	}
	
	static class Gun{
		List<Integer> guns;
		
		public Gun(List<Integer> guns) {
			this.guns = guns;
		}
	}
	
	
	static class Point{
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r =r;
			this.c=c;
		}
		
		public boolean inRange() {
			return this.r >=1 && this.r<=n && this.c>=1 && this.c<=n;
		}
	}
	
	
	static class Player{
		Point p;
		int dir; // 방향
	
		public Player(Point p, int dir) {
			this.p = p;
			this.dir=dir;
		}
	}
	
	
}