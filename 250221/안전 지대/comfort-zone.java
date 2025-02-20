import java.util.*;
public class Main {
    public static int n,m;
    public static int[][] grid;
    public static boolean[][] visited;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        visited = new boolean[n][m];

        int maxH = -1;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                grid[i][j] = sc.nextInt();
                maxH = Math.max(maxH, grid[i][j]);
            }
        }

        int maxCnt = -1;
        int maxK = -1;
        int ans = -1;
        for(int k=1; k<=maxH; k++){
            for(int i=0; i<n; i++){
                Arrays.fill(visited[i], false);
            }

            int cnt = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(!visited[i][j] && grid[i][j] > k){
                        //System.out.println(k+" "+i+" "+j);
                        dfs(i,j,k);
                        cnt++;
                    }
                }
            }
            
            if(cnt > maxCnt){
                maxK = k;
                maxCnt = cnt;
            }
        }

        System.out.println(maxK + " " + maxCnt);
        
    }

    public static void dfs(int r, int c, int k){
        int[] dr = {-1,0,1,0};
        int[] dc = {0,-1,0,1};

        for(int d=0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(inRange(nr,nc) && !visited[nr][nc] && grid[nr][nc] > k){
                visited[nr][nc] = true;
                dfs(nr, nc, k);
            }
        }
    }

    public static boolean inRange(int r, int c){
        return r>=0 && r<n && c>=0 && c<m;
    }
}