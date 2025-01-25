import java.util.*;
public class Main {
    public static int n,m;
    public static int[] coins;
    public static int[] dp;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        coins = new int[n];
        dp = new int[m+1];

        for(int i=0; i<n; i++){
            coins[i] = sc.nextInt();
        }

        init();

        for(int i=1; i<=m; i++){ // ** 1부터 시작
            for(int j=0; j<n; j++){
                if(i>=coins[j]){
                    if(dp[i-coins[j]] == Integer.MAX_VALUE) continue;

                    // dp[i] : 지금까지 선택한 동전의 합이 i일때, 가능한 최소 동전 개수
                    dp[i] = Math.min(dp[i-coins[j]]+1, dp[i]);
                }
               
            }
        }

        int ans = dp[m];
        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    public static void init(){
        for(int i=0; i<=m; i++){
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;
    }
}