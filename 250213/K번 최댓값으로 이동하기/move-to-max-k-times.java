import java.util.*;
public class Main {
    public static int n,k;
    public static int[][] grid;
    public static int curR,curC;
    public static boolean[][] visited;
    public static Queue<Point> q = new ArrayDeque<>();
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        grid = new int[n][n];
        visited = new boolean[n][n];


        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                grid[i][j] = sc.nextInt();
            }
        }

        curR = sc.nextInt()-1;
        curC = sc.nextInt()-1;

        while(k-->0){
            boolean flag = move();

            if(!flag) break;
        }

        System.out.println((curR+1) + " " + (curC+1));
        
    }

    public static void bfs(){
        int[] dr = {-1,0,1,0};
        int[] dc = {0,-1,0,1};

        q.add(new Point(curR, curC));
        visited[curR][curC] = true;
        int targetNum = grid[curR][curC];

        while(!q.isEmpty()){
            Point cur = q.remove();

            for(int d=0; d<4; d++){
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(canGo(targetNum, nr, nc)){
                    q.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }

    public static boolean needUpdate(Point bestP, Point newP){
        if(bestP.r == -1 && bestP.c == -1) return true;

        if(grid[newP.r][newP.c] != grid[bestP.r][bestP.c]){
            return grid[newP.r][newP.c] > grid[bestP.r][bestP.c];
        }else if(bestP.r != newP.r){
            return newP.r < bestP.r;
        }else{
            return newP.c < bestP.c;
        }
    }

    public static boolean canGo(int targetNum, int nr, int nc){
        if(!inRange(nr,nc) || visited[nr][nc]) return false;
        if(targetNum <= grid[nr][nc]) return false;
        return true;
    }

    public static boolean inRange(int r, int c){
        return r>=0 && r<n && c>=0 && c<n;
    }

    public static boolean move(){
        // 1. 초기화
        for(int i=0; i<n; i++){
            Arrays.fill(visited[i], false);
        }

        // 2. 이동
        bfs();

        // 3. 최선 값
        Point best = new Point(-1,-1);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j] || (i == curR && j == curC)) continue;

                Point newP = new Point(i,j);
                if(needUpdate(best, newP)){
                    best = newP;
                }
            }
        }

        if(best.r == -1 && best.c == -1) return false;

        curR = best.r;
        curC = best.c;
        return true;

    }
}

class Point{
    int r;
    int c;

    public Point(int r, int c){
        this.r=r;
        this.c=c;
    }
}