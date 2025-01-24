import java.util.*;

public class Main {
    public static int n;
    public static int[] dp;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dp = new int[n+1];

        if(n == 1){
            System.out.println(1);
            return;
        }

        dp[1] = 1;
        dp[2] = 1; // n=1 일때 에러
        for(int i=3; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(dp[n]);

    }
}