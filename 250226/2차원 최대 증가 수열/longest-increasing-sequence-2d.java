import java.util.*;
public class Main {
    public static int n,m;
    public static int[][] grid;
    public static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();
       

        for(int i=0; i<n; i++) Arrays.fill(dp[i], Integer.MIN_VALUE);
        dp[0][0] = 1; // i, j칸일 때 최대 수 

        for(int i=1; i<n; i++){
            for(int j=1; j<m; j++){
                for(int k=1; k<=i; k++){
                    for(int l=1; l<=j; l++){
                        if(grid[i][j] > grid[i-k][j-l]){
                            dp[i][j] = Math.max(dp[i-k][j-l]+1, dp[i][j]);
                        }
                        
                    }
                }
            
            }
        }

        int ans = -1;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                ans = Math.max(dp[i][j], ans);
            }
        }
        System.out.println(ans);
        


    }
}