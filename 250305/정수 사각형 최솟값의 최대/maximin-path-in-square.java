import java.util.*;
public class Main {
    public static int[][] dp;
    public static int n;
    public static int[][] matrix;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        matrix = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        init();

        for(int i=1; i<n; i++){
            for(int j=1; j<n; j++){
                dp[i][j] = Math.max(dp[i][j], Math.min(matrix[i][j], Math.max(dp[i-1][j], dp[i][j-1])));
            }
        }

        System.out.println(dp[n-1][n-1]);
    }

    public static void init(){
        for(int i=0; i<n; i++) Arrays.fill(dp[i], -(int)1e9);

        dp[0][0] = matrix[0][0];
        
        for(int i=1; i<n; i++){
            dp[i][0] = Math.max(dp[i][0], Math.min(dp[i-1][0], matrix[i][0]));
        }

        for(int j=1; j<n; j++){
            dp[0][j] = Math.max(dp[0][j], Math.min(dp[0][j-1], matrix[0][j]));
        }
    }
}