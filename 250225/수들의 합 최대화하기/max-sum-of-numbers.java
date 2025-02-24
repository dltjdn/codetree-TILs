import java.util.*;
public class Main {
    public static int n;
    public static int[][] grid;
    public static boolean[] visited;
    public static int ans = -1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        backtrack(0, 0);

        System.out.println(ans);
    }

    public static void backtrack(int r, int sum){
        if(r == n){
            ans = Math.max(ans, sum);
            return;
        }

        for(int c=0; c<n; c++){
            if(visited[c]) continue;
            visited[c] = true;
            sum += grid[r][c];

            backtrack(r+1, sum);
            
            sum -= grid[r][c];
            visited[c] = false;
        }
    }
}