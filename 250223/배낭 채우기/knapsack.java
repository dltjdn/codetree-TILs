import java.util.*;
public class Main {
    public static int n,m;
    public static int[] w,v;
    public static int[] dp;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        w = new int[n];
        v = new int[n];
        dp = new int[m+1];

        for(int i=0; i<n; i++){
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        
        // dp[i]: 무게가 i일 때 최대 가치
        for(int i=0; i<n; i++){
            for(int j=m; j>=0; j--){
                if(j >= w[i]){
                    dp[j] = Math.max(dp[j-w[i]] + v[i], dp[j]);
                }
            }
        }

        int ans = -1;
        for(int i=0; i<=m; i++){
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}