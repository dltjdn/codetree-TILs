import java.util.*;
public class Main {
    public static String a,b;
    public static int[][] dp;
    public static int n,m;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        a = sc.next();
        n = a.length();
        b = sc.next();
        m = b.length();
        dp = new int[n][m];

        init();

        // A : S 'A' 'B' 'S' B 'A'
        // B : 'A' 'B' A B 'S' 'A'

        for(int i=1; i<n; i++){
            for(int j=1; j<m; j++){
                if(a.charAt(i) == b.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); // ** +1 하지 않음
                }
            }
        }

        System.out.println(dp[n-1][m-1]);


    }

    public static void init(){ // ** 초기화 값 주의
        dp[0][0] = (a.charAt(0) == b.charAt(0))? 1 : 0;

        for(int i=1; i<n; i++){
            if(a.charAt(i) == b.charAt(0)) dp[i][0] = 1;
            else dp[i][0] = dp[i-1][0];
        }

        for(int j=1; j<m; j++){
            if(a.charAt(0) == b.charAt(j)) dp[0][j] = 1;
            else dp[0][j] = dp[0][j-1];
        }
    }
}

