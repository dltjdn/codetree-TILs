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
        
        init();

        // dp[i]: 무게가 i일 때 최대 가치
        for(int i=0; i<n; i++){
            for(int j=m; j>=0; j--){ 
                // 같은 보석을 중복하여 사용할 수 없기 때문에
                // 반복문을 거꾸로 돌려 보석을 중복하여 사용하는 것을 방지합니다.    
                if(j >= w[i]){
                    dp[j] = Math.max(dp[j-w[i]] + v[i], dp[j]);
                }
            }
        }

        int ans = 0; // 초기값 0 이여야함!
        for(int i=0; i<=m; i++){
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }

    public static void init(){
        Arrays.fill(dp, Integer.MIN_VALUE);

        dp[0] = 0;
    }
}