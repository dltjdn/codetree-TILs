import java.util.*;
public class Main {
    public static int n;
    public static int[][] grid;
    public static int[][] dp;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n+1][n+1];
        dp = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                grid[i][j] = sc.nextInt();
            }
        }

        init();

        for(int i=2; i<=n; i++){
            for(int j=n-1; j>=1; j--){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j+1]) + grid[i][j];
            }
        }

        System.out.println(dp[n][1]);
    }

    public static void init(){
        dp[1][n] = grid[1][n];

        for(int i=2; i<=n; i++){
            dp[i][n] = dp[i-1][n] + grid[i][n];
        }

        for(int j=n-1; j>=1; j--){
            dp[1][j] = dp[1][j+1] + grid[1][j];
        }
    }   
}