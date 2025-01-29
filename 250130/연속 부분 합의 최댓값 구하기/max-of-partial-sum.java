import java.util.*;
public class Main {
    public static int n;
    public static int[] a;
    public static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = new int[n];
        dp = new int[n];

        for(int i=0; i<n; i++){
            a[i] = sc.nextInt();
        }

        init();

        for(int i=1; i<n; i++){
            dp[i] = Math.max(a[i], dp[i-1] + a[i]);
        }

        int ans = Integer.MIN_VALUE;        
        for(int i=0; i<n; i++){
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
        
    }

    public static void init(){
        Arrays.fill(dp, Integer.MIN_VALUE);

        dp[0] = a[0];
    }
}