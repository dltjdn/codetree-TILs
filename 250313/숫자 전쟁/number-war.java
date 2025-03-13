import java.util.*;
public class Main {
    public static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n+1];
        int[] b = new int[n+1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            b[i] = sc.nextInt();
        }

        dp = new int[n+1][n+1]; // a카드 i,b카드 i까지 진행했을 때 b의 최대점수
        for(int i=0; i<=n; i++) Arrays.fill(dp[i], -(int)1e9);
        dp[0][0] = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(dp[i][j] == -(int)1e9) continue;

                if(a[i+1] < b[j+1]){
                    dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);
                }

                if(a[i+1] > b[j+1]){
                    dp[i][j+1] = Math.max(dp[i][j+1], dp[i][j] + b[j+1]);
                }

               //if(a[i+1] == b[j+1]){
                    dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j]);
                //}

            }
        }

        int ans = Integer.MIN_VALUE;
        for(int i=0; i<=n; i++){
            ans = Math.max(ans, dp[n][i]);
            ans = Math.max(ans, dp[i][n]);
        }
        System.out.println(ans);
        
    }
}