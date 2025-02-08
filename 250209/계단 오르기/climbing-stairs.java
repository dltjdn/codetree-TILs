import java.util.*;
public class Main {
    public static int n;
    public static int[] dp;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dp = new int[n];

        if(n==2 || n==3){
            System.out.println(1);
            return;
        }

        dp[2] = 1;
        dp[3] = 1;
        for(int i=4; i<n; i++){
            dp[i] = Math.max(dp[i-2], dp[i-3])+1;
        }

        int ans;
        if(dp[n-1] == 0) ans = 0;
        else ans = dp[n-1] % 10007;
        System.out.println(ans);
    }
}