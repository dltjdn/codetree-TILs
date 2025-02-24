import java.util.Scanner;

public class Main {
    public static int n;
    public static int[][] grid;
    public static boolean[][] visited;
    public static int curSize;
    public static int maxSize = Integer.MIN_VALUE;
    public static int cnt = 0; //터지게 되는 블록의 수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

      
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j]){
                    curSize = 1;
                    
                    dfs(i, j, grid[i][j]);
                    
                    if(curSize >= 4) cnt++;
                    maxSize = Math.max(maxSize, curSize);
                }
            }
        }

        System.out.println(cnt +" " +maxSize);
    }

    public static void dfs(int r, int c, int num){
        int[] dr = {-1,0,1,0};
        int[] dc = {0,1,0,-1};
        
        visited[r][c] = true;

        for(int d=0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(inRange(nr,nc) && !visited[nr][nc] && grid[nr][nc] == num){
                curSize++;
                dfs(nr, nc, num);
            }
        }

       
    }

    public static boolean inRange(int r, int c){
        return r>=0 && r<n && c>=0 && c<n;
    }
}