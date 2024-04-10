import java.util.*;
import java.io.*;
public class Main {

	static int m;
	static int t;
	static Monster[] monsters;
	static Point packman;
	static int[] isDead;
	static int monstersLength;
	static int[][] deadMap;
	
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] line = bf.readLine().split(" ");
		m = Integer.parseInt(line[0]); // 몬스터 마리수
		t = Integer.parseInt(line[1]); // 턴 수
		monstersLength = m;
		monsters = new Monster[1000001];
		isDead = new int[1000001]; // 죽은 턴 기록 : 4 > turn  
		Arrays.fill(isDead, -1); // -1이면 죽지 않은 것!
		deadMap = new int[5][5];
		
		// 4*4격자
		String[] line2 = bf.readLine().split(" ");
		packman = new Point(Integer.parseInt(line2[0]), Integer.parseInt(line2[1]));
		
		for(int i=1; i<=m; i++) {
			String[] line3 = bf.readLine().split(" ");
			Point p = new Point(Integer.parseInt(line3[0]), Integer.parseInt(line3[1]));
			monsters[i] = new Monster(p, Integer.parseInt(line3[2])-1);
			
		}
		

		
		// 몬스터의 초기위치 == 팩맨의 초기위치 가
		for(int turn=1; turn<=t; turn++) {
			//System.out.println(turn+ "번 ===================");
			// 1. 몬스터 복제 시도
			List<Monster> newMonsters = new ArrayList<>();
			for(int i=1; i<=monstersLength ; i++) {
				if(isDead[i] != -1) continue;
				Monster cur = monsters[i];
				newMonsters.add(new Monster(new Point(cur.p.r, cur.p.c), cur.d));
			}
			
			
			// 2. 몬스터 이동
			moveMonsters(turn);
			
			
			// 3. 팩맨 이동
			movePackman(turn);
			
//			for(int i=1; i<=monstersLength; i++) {
//				System.out.println("중 r: "+ monsters[i].p.r  + " c: " +monsters[i].p.c + " d: "+monsters[i].d + " 죽었는지 : "+ isDead[i]);
//			}

			
			
			// 4. 몬스터 시체 소멸
			// 시체 될 때 turn 저장하면자동
			/* 2턴 의미 !!!! 확인!!!!!!
			 * 
			 */
			
			// 5. 몬스터 복제 완
			// monstersLength 업데이트 해주
			for(Monster newMonster : newMonsters) {
				monsters[++monstersLength] = newMonster;
			}
			
//			for(int i=1; i<=monstersLength; i++) {
//				System.out.println("끝 r: "+ monsters[i].p.r  + " c: " +monsters[i].p.c + " d: "+monsters[i].d + " 죽었는지 : "+ isDead[i]);
//			}

			
		}
		
		//살아남은 몬스터 
		int cnt=0;
		for(int i=1; i<=monstersLength; i++) {
			//System.out.println("isDead: " + isDead[i]);
			if(isDead[i] == -1) cnt++;
		}
		
		System.out.println(cnt);
	
	}
	
	private static void movePackman(int turn) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1}; 
		
		int curR = packman.r;
		int curC = packman.c;
		//System.out.println("cur : " + curR + " " + curC);
		
		// 4가지 방향 세 칸 이
		
		// 지나갔던 곳 다시 갈 수 있음
		
		int maxCnt = Integer.MIN_VALUE;
		List<Point> results = new ArrayList<>();
		
		List<Point> directions = new ArrayList<>();
		Map<Point, Boolean> visited = new HashMap<>();
		
		//visited.put(packman, true);
		
		for(int d1=0; d1<4; d1++) {
			//System.out.println("~~~~!!!! + " + curR + " "+ curC);
			Point next1 = new Point(curR + dx[d1], curC + dy[d1]);
			if(!next1.inRange()) continue;
			directions.add(next1);
			//visited.put(next1, true);
			
			for(int d2=0; d2<4; d2++) {
				Point next2 = new Point(next1.r + dx[d2], next1.c + dy[d2]);
				if(!next2.inRange()) continue;
				directions.add(next2);
				//visited.put(next2, true);
				
				for(int d3=0; d3<4; d3++) {
					Point next3 = new Point(next2.r + dx[d3], next2.c + dy[d3]);
					if(!next3.inRange()) continue;
					directions.add(next3);
					//visited.put(next3, true);
					
					int cnt = 0;
					boolean[] isTempDead = new boolean[1000001];
					for(Point direction : directions) {
						for(int j=1; j<= monstersLength; j++) {
							if(isDead[j] == -1 && (monsters[j].p.r == direction.r && monsters[j].p.c == direction.c) && !isTempDead[j]) {
								cnt++;
								isTempDead[j] = true;
							}
						}
					}
				
					if(cnt > maxCnt) {
						//for(Point d: directions) System.out.println("@@@ r: "+ d.r + " c: "+d.c + " cnt : "+ cnt);
						maxCnt = cnt;
						results = new ArrayList<>(directions);
					}
					//System.out.println();

					directions.remove(directions.size()-1);
					//visited.put(next1, false);
				}
				directions.remove(directions.size()-1);
				//visited.put(next1, false);
			}
			directions.remove(directions.size()-1);
			//visited.put(next1, false);

		}
		
		
		for(Point result : results) {
			//System.out.println("하하하 : " + result.r + result.c);
			for(int j=1; j<= monstersLength; j++) {
				if(isDead[j] == -1 && (monsters[j].p.r == result.r && monsters[j].p.c == result.c)) {
					//System.out.println(j+ "번 몬스터 죽은 얘들 r: "+ monsters[j].p.r + " c: "+monsters[j].p.c );
					isDead[j] = turn + 2; 
					deadMap[result.r][result.c] = turn + 2;
				}
			}
		}
		
		packman.r = results.get(2).r;
		packman.c = results.get(2).c;
		
	}
		

	private static void moveMonsters(int turn) {
		int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dy = {0, -1, -1, -1,0, 1, 1, 1}; // 상 부터 반시계
		
		for(int i=1; i<= monstersLength ; i++) {
			Monster cur = monsters[i];
			int cnt = 0;
			while(cnt < 8) { //8번 반
				int nextR = cur.p.r + dx[cur.d];
				int nextC = cur.p.c + dy[cur.d];
				Point next = new Point(nextR, nextC);
				
				if(next.inRange() && !(next.r == packman.r && next.c == packman.c)
						&& deadMap[next.r][next.c] <= turn) { //2에 죽음 isDead= 4 4>3  4>4	
					break;
				}

				if(cur.d == 7) cur.d = 0;
				else cur.d++;	// 방향 45도 회전

				cnt++;
			}
			
			if(cnt != 8) {
				
				cur.p.r += dx[cur.d];
				cur.p.c += dy[cur.d];
			}
			
			
		}
	}
	
	static class Monster{
		Point p;
		int d;
		
		public Monster(Point p, int d) {
			this.p = p;
			this.d=d;
		}
	}
		
	static class Point {
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
		
		public boolean inRange() {
			return this.r >=1 && this.r<=4 && this.c>=1 && this.c<=4;
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