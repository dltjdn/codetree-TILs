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

        for(int i=0; i<=m; i++){
            for(int j=0; j<n; j++){
                if(i>=coins[j]){
                    if(dp[i-coins[j]] == Integer.MAX_VALUE) continue;

                    dp[i] = Math.min(dp[i-coins[j]]+1, dp[i]);
                    //System.out.println(i+" "+dp[i]);
                }
               
            }
        }

        if(dp[m] == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(dp[m]);
    }

    public static void init(){
        for(int i=0; i<=m; i++){
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;
    }
}