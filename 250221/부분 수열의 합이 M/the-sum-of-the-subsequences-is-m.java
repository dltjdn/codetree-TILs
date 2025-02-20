import java.util.*;

public class Main {
    public static int n,m;
    public static int[] nums;
    public static int[] dp;
    public static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       n = sc.nextInt();
       m = sc.nextInt();
       nums = new int[n];
       dp = new int[m+1];

       for(int i=0; i<n; i++){
        nums[i] = sc.nextInt();
       }

       Arrays.fill(dp, (int)1e9);
       dp[0] = 0;

       for(int i=0; i<n; i++){
        for(int j=m; j>=0; j--){
            if(j >= nums[i]){
                if(dp[j-nums[i]] == (int)1e9) continue;

                dp[j] = Math.min(dp[j], dp[j-nums[i]]+1);
            }
        }
       }

       int ans = dp[m];
       if(ans == (int)1e9) ans = -1;
       System.out.println(ans);
    }
}