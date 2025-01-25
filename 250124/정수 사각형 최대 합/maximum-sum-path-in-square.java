import java.util.*;
public class Main {
    public static int n;
    public static int[][] grid;
    public static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        dp = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                grid[i][j] = sc.nextInt();
            }
        }

        init();

        for(int i=1; i<n; i++){
            for(int j=1; j<n; j++){
                dp[i][j] = Math.max(dp[i-1][j]+grid[i][j], dp[i][j-1]+grid[i][j]);
            }
        }

       
        System.out.println(dp[n-1][n-1]);

    }

    public static void init(){
        dp[0][0] = grid[0][0];

        for(int i=1; i<n; i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        for(int j=1; j<n; j++){
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

    }
}