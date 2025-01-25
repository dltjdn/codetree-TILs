import java.util.*;
public class Main {
    public static int n;
    public static int[] a;
    public static int[] dp;

    public static void init(){
        for(int i = 0; i <= n; i++)
            dp[i] = Integer.MIN_VALUE;
        
        // 0번째 index와 비교했을 때 항상 갱신될 수
        // 있는 값을 넣어줍니다.
        dp[0] = 0;
        a[0] = 0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = new int[n+1];
        dp = new int[n+1];

        for(int i=1; i<=n; i++){
            a[i] = sc.nextInt();
        }
        
        init();

        for(int i=1; i<=n; i++){
            for(int j=0; j<i; j++){
                if(a[j] < a[i]) dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }

        int answer = 0;
        for(int i = 0; i <= n; i++)
            answer = Math.max(answer, dp[i]);
        
        System.out.println(answer);

    }
}