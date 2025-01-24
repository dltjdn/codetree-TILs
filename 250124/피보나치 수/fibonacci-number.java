import java.util.*;

public class Main {
    public static int n;
    public static int[] dp = new int[45];
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        dp[1] = 1;
        dp[2] = 1;
        for(int i=3; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(dp[n]);

    }
}