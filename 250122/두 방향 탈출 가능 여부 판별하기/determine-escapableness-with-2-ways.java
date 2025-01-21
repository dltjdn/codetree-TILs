import java.util.*;
public class Main {
    public static int n,m;
    public static int[][] grid;
    public static int[][] visited;

    public static void dfs(int r, int c){
        int[] dr = new int[]{1,0};
        int[] dc = new int[]{0,1};

        visited[r][c] = 1;

        for(int i=0; i<2; i++){
            int newR = r + dr[i];
            int newC = c + dc[i];
            if(canGo(newR, newC)){
                dfs(newR, newC);
            }
        }
    }

    public static boolean canGo(int r, int c){
        if(!inRange(r,c)) return false;
        if(visited[r][c] == 1 || grid[r][c] == 0) return false;

        return true;
    }

    public static boolean inRange(int r, int c){
        return r>=0 && r<n && c>=0 && c<m;
    }

    public static void main(String[] args) {
        // Please write your code here.
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

        dfs(0,0);

        System.out.println(visited[n-1][m-1]);

    }
}