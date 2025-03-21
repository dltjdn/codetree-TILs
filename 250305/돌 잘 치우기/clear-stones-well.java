import java.util.*;
public class Main {
    public static int n,k,m;
    public static int[][] grid;
    public static List<Point> starts = new ArrayList<>();
    public static List<Point> stones = new ArrayList<>();
    public static Queue<Point> q = new ArrayDeque<>();
    public static boolean[][] visited;
    public static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if(grid[i][j] == 1) stones.add(new Point(i,j));
            }
        }

        for (int i = 0; i < k; i++) {
            starts.add(new Point(sc.nextInt()-1, sc.nextInt()-1));
        }
        
        backtrack(0, 0); // m개의 치울 돌 선택

        System.out.println(ans);
        
    }

    public static void backtrack(int s, int depth){
        if(depth == m){
            for(int i=0; i<n; i++) Arrays.fill(visited[i], false);

            for(Point start : starts){ // * 모든 start 큐에 넣고 탐색 시작도 가능
                bfs(start);
            }

            int cnt = 0 ;
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(visited[i][j]) cnt++;
                }
            }

            ans = Math.max(ans, cnt);

        }


        for(int i=s; i<stones.size(); i++){
            grid[stones.get(i).r][stones.get(i).c] = 0;
            backtrack(i+1, depth+1);
            grid[stones.get(i).r][stones.get(i).c] = 1;
        }

    }

    public static void bfs(Point s){
        q.add(s);
        visited[s.r][s.c] = true;

        while(!q.isEmpty()){
            Point cur = q.remove();

            int[] dr = {-1,0,1,0};
            int[] dc = {0,-1,0,1};
            for(int d=0; d<4; d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(inRange(nr,nc) && !visited[nr][nc] && grid[nr][nc] == 0){
                    q.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }

    public static boolean inRange(int r, int c){
        return r>=0 && r<n && c>=0 && c<n;
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