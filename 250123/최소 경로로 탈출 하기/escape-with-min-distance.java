import java.util.*;
public class Main {
    public static int n,m;
    public static Queue<Point> q = new ArrayDeque<>();
    public static int[][] grid;
    public static boolean[][] visited;
    public static int[][] step;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        visited = new boolean[n][m];
        step = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                grid[i][j] = sc.nextInt(); // 뱀 있 0
            }
        }

        for(int i=0; i<n; i++){
            Arrays.fill(step[i], (int)1e9);
        }

        q.add(new Point(0,0));
        visited[0][0] = true;
        step[0][0] = 0;
        bfs();

        if(step[n-1][m-1] == (int)1e9) System.out.println(-1);
        else System.out.println(step[n-1][m-1]);
    }

    public static boolean canGo(int r, int c){
        if(!inRange(r,c)) return false;
        if(visited[r][c] || grid[r][c] == 0) return false;
        return true;
    }

    public static boolean inRange(int r, int c){
        return r>=0 && r<n && c>=0 && c<m;
    }

    public static void bfs(){
        while(!q.isEmpty()){
            Point cur = q.remove();

            int[] dr = new int[]{1, 0, -1, 0};
            int[] dc = new int[]{0, 1, 0, -1};

            for(int i=0; i<4; i++){
                int nextR = cur.r + dr[i];
                int nextC = cur.c + dc[i];

                if(canGo(nextR, nextC)){
                    q.add(new Point(nextR, nextC));
                    visited[nextR][nextC] = true;
                    step[nextR][nextC] = step[cur.r][cur.c] + 1;
                }
            }
            
        }
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