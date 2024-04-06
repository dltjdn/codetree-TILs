import java.io.*;
import java.util.*;

public class Main {
	static int N;
	
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        int P = Integer.parseInt(line[2]);
        int C = Integer.parseInt(line[3]);
        int D = Integer.parseInt(line[4]);
        
        int[][] map = new int[N+1][N+1];
        
        
        
        int[] stun = new int[P+1]; // 기절 2,1,0
        boolean[] isDead = new boolean[P+1]; // 죽은지 여부
        int[] point = new int[P+1];


        String[] line2 = bf.readLine().split(" ");
        int rr = Integer.parseInt(line2[0]);
        int rc = Integer.parseInt(line2[1]);
        Point rudolf = new Point(rr,rc);
        map[rr][rc] = -1;

        Map<Integer,Point> santas = new HashMap<>();
        for(int i=1; i<=P ; i++){
            String[] row = bf.readLine().split(" ");
            int num = Integer.parseInt(row[0]);
            int sr = Integer.parseInt(row[1]);
            int sc= Integer.parseInt(row[2]);
            santas.put(num, new Point(sr, sc));
            map[sr][sc] = num;
        }

        for(int t=1; t<=M; t++){
      
            // ====루돌프==================
           int closestX = 10000;
			int closestY = 10000;
			//int closestDist = Integer.MAX_VALUE;
			int closestIdx = 0;

			// 살아있는 산타 중 루돌프에 가장 가까운 산타를 찾습니다.
			for (int i = 1; i <= P; i++) {
				if (isDead[i])continue;

				Point santa = santas.get(i);

				Tuple cur = new Tuple(rudolf.getDistance(santa), santa.r, santa.c);
				Tuple best = new Tuple(rudolf.getDistance(new Point(closestX, closestY)), closestX, closestY);

				if (cur.compareTo(best) < 0) {
					closestX = santa.r;
					closestY = santa.c;
					closestIdx = i;
				}

			}

            // 가장 가까운 산타로 루돌프가 이동
          
            if(closestIdx != 0){
                map[rudolf.r][rudolf.c] = 0;
                int moveR = 0;
                if(rudolf.r < closestX) moveR = 1;
                else if(rudolf.r > closestX) moveR = -1;

                int moveC = 0;
                if(rudolf.c < closestY) moveC = 1;
                else if(rudolf.c > closestY) moveC = -1;
            
                rudolf.r += moveR;
                rudolf.c += moveC;
               
               
                // 이동한 루돌프와 산타가 "충돌"할 경우, 산타를 이동 - C점 획득
                if(rudolf.r == closestX && rudolf.c == closestY){
                    // 충돌했을 때 산타 이동 위치
                    int firstR = closestX + moveR * C;
                    int firstC = closestY + moveC * C;
                    int lastR = firstR;
                    int lastC = firstC;
                    stun[closestIdx] = t+1; // 기절

                    // 이동한 곳에 산타 있을 때 "상호작용"
                    while(inRange(lastR,lastC) && map[lastR][lastC] > 0 ) {
                        lastR += moveR;
                        lastC += moveC;
                    }

                    while(!(firstR == lastR && firstC == lastC)){
                        int beforeR = lastR - moveR;
                        int beforeC = lastC - moveC;
                        
                        if(!inRange(beforeR,beforeC)) break;
                        int idx = map[beforeR][beforeC];

                        if(inRange(lastR, lastC)){
            
                            map[lastR][lastC] = map[beforeR][beforeC];
                            santas.put(idx, new Point(lastR,lastC));
                        }else{
                
                            isDead[idx] = true;
                        } 
                        

                        lastR = beforeR;
                        lastC = beforeC;
                    }
                    
                    if(inRange(firstR, firstC)){
                
                        map[firstR][firstC] = closestIdx;
                        santas.put(closestIdx, new Point(firstR,firstC));
                    }else{
                    
                        isDead[closestIdx] = true;
                    } 
                    
                    

                    // 포인트 획득
                    point[closestIdx] += C;
                    map[rudolf.r][rudolf.c] = -1; 
                }
               
     

            }
        	
        
            // ========== 산타 ===========
            // 각 산타들은 루돌프와 가까운 방향으로 한 칸 이동
            
            int[] dr = {-1,1,0,0};
            int[] dc = {0,0,-1,1};
 
            for(int i=1; i<=P; i++){
                if(isDead[i] || stun[i] >= t ) continue;

                Point santa = santas.get(i);
                int minDist = rudolf.getDistance(santa);
                int moveDir = -1;

                // 4방향 중 가장 가까운 곳 구하기
                for(int j=0; j<4; j++){
                    int nextR = santa.r+dr[j];
                    int nextC = santa.c+dc[j];

                    if(!inRange(nextR, nextC) || map[nextR][nextC] > 0 ) continue;

                    int dist = rudolf.getDistance(new Point(nextR, nextC));

                    if(dist < minDist){
                        moveDir = j;
                        minDist = dist;
                    }
                }

                // 산타 이동
                if(moveDir != -1){
                    map[santa.r][santa.c] = 0;
                    santa.r += dr[moveDir];
                    santa.c += dc[moveDir];


                    // 이동한 산타와 루돌프가 "충돌"할 경우, 산타를 이동 - D점 획득
                    if(santa.r == rudolf.r && santa.c == rudolf.c){
                        int firstR = santa.r - dr[moveDir] * D;
                        int firstC = santa.c - dc[moveDir] * D;
                        int lastR = firstR;
                        int lastC = firstC;
                        stun[i] = t+1; // 기절

                        // 이동한 곳에 산타 있을 때 "상호작용"
                        if (D == 1) {
	                            point[i] += D;
	                    } else {
                            while(inRange(lastR,lastC) && map[lastR][lastC] > 0 ) {
                                    lastR -= dr[moveDir];
                                    lastC -= dc[moveDir];
                            }

                            while(!(firstR == lastR && firstC == lastC)){
                                    int beforeR = lastR + dr[moveDir];
                                    int beforeC = lastC + dc[moveDir];
                                    
                                    if(!inRange(beforeR,beforeC)) break;
                                    int idx = map[beforeR][beforeC];

                                    if(inRange(lastR, lastC)){
                                        map[lastR][lastC] = map[beforeR][beforeC];
                                        santas.put(idx, new Point(lastR,lastC));
                                    }else{
                                        isDead[idx] = true;
                                    } 
                                    

                                    lastR = beforeR;
                                    lastC = beforeC;
                            }

                                // 포인트 획득
                                point[i] += D;
                                
                                if(inRange(firstR, firstC)){
                
                                    map[firstR][firstC] = i;
                                    santas.put(i, new Point(firstR,firstC));
                                }else{
                                
                                        isDead[i] = true;
                                } 
                            
                                    
                        }
                     

                    }else{ // 충돌하지 않은 경우
                        map[santa.r][santa.c] = i;
                        santas.put(i, new Point(santa.r, santa.c));
                    }
                   
                }
                
   

            }

            for(int i=1; i<=P; i++){
                if(!isDead[i]) point[i]++;
            }
            
           

        }

        for(int i=1; i<=P; i++){
            System.out.print(point[i] + " ");
        }        
    }


    static boolean inRange(int x, int y) {
        return 1 <= x && x <= N && 1 <= y && y <= N;
    }


    static class Tuple implements Comparable<Tuple>{
        int d;
        int r;
        int c;

        public Tuple(int d, int r, int c){
            this.d= d;
            this.r=r;
            this.c=c;
        }

        @Override
        public int compareTo(Tuple o){
            if(this.d != o.d){
                return Integer.compare(this.d, o.d);
            }else if(this.r != o.r){
                return Integer.compare(o.r, this.r);
            }else{
                return Integer.compare(o.c,this.c);
            }
        }
    }


    static class Point{
        int r;
        int c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }

        public int getDistance(Point p){
            return (this.r - p.r)* (this.r - p.r) + (this.c - p.c)* (this.c - p.c); 
        }
    }

}