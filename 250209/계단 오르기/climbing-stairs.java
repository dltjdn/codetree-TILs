import java.util.*;
public class Main {
    public static int n;
    public static int[] dp;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dp = new int[n+1];

        if(n==2 || n==3){
            System.out.println(1);
            return;
        }

        dp[2] = 1;
        dp[3] = 1;
        for(int i=4; i<=n; i++){
            dp[i] = (dp[i-2] + dp[i-3]) % 10007; //** 마지막에 나누는 것이 아니라 매번 나누기 ( 오버플로우 방지 )
        }

        System.out.println(dp[n]);
    }
}