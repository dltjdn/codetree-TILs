import java.util.*;
public class Main {
    public static int n;
    public static int[] a;
    public static int[][] dp;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = new int[n+1];
        dp = new int[n+1][4]; // dp[i][j] : i번째 계단, 1계단 j번 일때 동전의 최대 개수

        for(int i=1; i<=n; i++){
            a[i] = sc.nextInt();
        }

        init();

        for(int i=1; i<=n; i++){
            for(int j=0; j<=3; j++){
                if(j>=1){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + a[i]);
                }
     
                if(i>=2){
                    dp[i][j] = Math.max(dp[i][j], dp[i-2][j] + a[i]);
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for(int j=0; j<=3; j++){
            ans = Math.max(ans, dp[n][j]);
        }
        System.out.println(ans);
    }

    public static void init(){
        for(int i=0; i<=n; i++){
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        dp[0][0] = 0;
    }
}