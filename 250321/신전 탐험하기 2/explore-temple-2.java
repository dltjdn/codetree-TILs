import java.util.*;
public class Main {
    public static int n;
    public static int[][] arr;
    public static int[][][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][3];
        dp = new int[3][n][3]; // 시작 열 i, j행, k열 
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
        }

        init();
        
        for(int col=0; col<3; col++){
            for(int i=1; i<n-1; i++){
                dp[col][i][0] = Math.max(dp[col][i-1][1], dp[col][i-1][2]) + arr[i][0];
                dp[col][i][1] = Math.max(dp[col][i-1][0], dp[col][i-1][2]) + arr[i][1];
                dp[col][i][2] = Math.max(dp[col][i-1][0], dp[col][i-1][1]) + arr[i][2];
            }
        }

        for(int col=0; col<3; col++){
            for(int j=0; j<3; j++){
                if(col == j) continue;

                for(int k=0; k<3; k++){
                    if(j == k) continue;

                    dp[col][n-1][j] = Math.max(dp[col][n-1][j], dp[col][n-2][k]+arr[n-1][j]);
                }
            
            }
        }

                // dp[0][n-1][1] = Math.max(dp[0][n-2][0], dp[0][n-2][2]) + arr[n-1][1];
                // dp[0][n-1][2] = Math.max(dp[0][n-2][0], dp[0][n-2][1]) + arr[n-1][2];

                // dp[1][n-1][0] = Math.max(dp[1][n-2][1], dp[1][n-2][2]) + arr[n-1][0];
                // dp[1][n-1][2] = Math.max(dp[1][n-2][0], dp[1][n-2][1]) + arr[n-1][2];

                // dp[2][n-1][0] = Math.max(dp[2][n-2][1], dp[2][n-2][2]) + arr[n-1][0];
                // dp[2][n-1][1] = Math.max(dp[2][n-2][0], dp[2][n-2][2]) + arr[n-1][1];
       
        int ans = Integer.MIN_VALUE;
        for(int col=0; col<3; col++){
            for(int j=0; j<3; j++){
                if(col == j) continue;
                ans = Math.max(ans, dp[col][n-1][j]);
            }
        }
        System.out.println(ans);
    }

    public static void init(){
        for(int col=0; col<3; col++){
            for(int i=0; i<n; i++){
                Arrays.fill(dp[col][i], Integer.MIN_VALUE);
            }
        }

        for(int col=0; col<3; col++){
            dp[col][0][col] = arr[0][col];
        }
    }
}