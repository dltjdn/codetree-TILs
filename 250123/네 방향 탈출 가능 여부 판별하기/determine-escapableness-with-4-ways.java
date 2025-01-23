import java.util.*;
public class Main {
    public static int n,m;
    public static int[][] grid;
    public static int[][] visited;
    public static Queue<Point> q = new ArrayDeque<>();
    public static void main(String[] args) {
        // Please write your code here.\
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        visited = new int[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                grid[i][j] = sc.nextInt();
            }
        }

        q.add(new Point(0,0));
        visited[0][0] = 1;

        bfs();

        System.out.println(visited[n-1][m-1]);
    }

    public static boolean inRange(int r, int c){
        return r>=0 && r<n && c>=0 && c<m;
    }

    public static boolean canGo(int r, int c){
        if(!inRange(r,c)) return false;
        if(visited[r][c] == 1 || grid[r][c] == 0) return false;

        return true;
    }

    public static void bfs(){
        while(!q.isEmpty()){
            Point cur = q.remove();
            int r = cur.r, c = cur.c;
            
            int[] dr = new int[]{0, 1,  0, -1};
            int[] dc = new int[]{1, 0, -1,  0};
            
            for(int i=0; i<4; i++){
                int nextR = r + dr[i];
                int nextC = c + dc[i];

                if(canGo(nextR, nextC)){
                    q.add(new Point(nextR, nextC));
                    visited[nextR][nextC] = 1;
                }
            }
        }
    }
}

class Point{
    int r, c;
    public Point(int r, int c){
        this.r=r;
        this.c=c;
    }
}