import java.util.*;
public class Main {
    public static int n,h,m;
    public static int[][] grid;
    public static int[][] step;
    public static boolean[][] visited;
    public static int[][] ans;
    public static Queue<Point> q = new ArrayDeque<>();

    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); 
        h = sc.nextInt(); // 사람의 수
        m = sc.nextInt(); // 비를 피할 수 있는 공간의 수
        grid = new int[n][n];
        visited = new boolean[n][n];
        step = new int[n][n];
        ans = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                grid[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 2){
                    init();

                    bfs(i,j);
                }
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }
        

    }

    public static void init(){
        for(int i=0; i<n; i++){
            Arrays.fill(visited[i], false);
            Arrays.fill(step[i], 0);
        }

        q = new ArrayDeque<>();
    }

    public static boolean inRange(int r, int c){
        return r>=0 && r<n && c>=0 && c<n;
    }

    public static void bfs(int r, int c){
        int[] dr = {-1,0,1,0};
        int[] dc = {0,1,0,-1};

        q.add(new Point(r,c));
        visited[r][c] = true;
        step[r][c] = 0;

        while(!q.isEmpty()){
            Point cur = q.remove();

            if(grid[cur.r][cur.c] == 3){
                ans[r][c] = step[cur.r][cur.c];
                return;
            }

            for(int d=0; d<4; d++){
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(inRange(nr,nc) && !visited[nr][nc] && grid[nr][nc] != 1){
                    visited[nr][nc] = true;
                    q.add(new Point(nr,nc));
                    step[nr][nc] = step[cur.r][cur.c] + 1; 
                }
            }
            
        }

        ans[r][c] = -1;
       
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