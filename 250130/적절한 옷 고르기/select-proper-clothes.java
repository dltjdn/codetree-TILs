import java.util.*;
public class Main {
    public static int n,m;
    public static Clothe[] clothes;
    public static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 옷 n개
        m = sc.nextInt(); // m일
        clothes = new Clothe[n+1];
        dp = new int[m+1][n+1];
        
        for(int i=1; i<=n; i++){
            clothes[i] = new Clothe(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        init();
      
        for(int i=2; i<=m; i++){
            for(int j=1; j<=n; j++){ // 이전 선택 옷
                for(int k=1; k<=n; k++){ // 현재 선택 옷
                    if(clothes[k].s <= i && clothes[k].e >= i){
                        if(dp[i-1][j] == Integer.MIN_VALUE) continue;
                        dp[i][k] = Math.max(dp[i][k], dp[i-1][j] + Math.abs(clothes[j].score - clothes[k].score));
                    }
                }  
            }
        }

        int ans = Integer.MIN_VALUE;
        for(int i=1; i<=n; i++){
            ans = Math.max(ans, dp[m][i]);
        }

        System.out.println(ans);

    }

    public static void init(){
        for(int i=0; i<=m; i++){
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        for(int i=1; i<=n; i++){
            if(clothes[i].s == 1){
                dp[1][i] = 0;
            }
            
        }
    }

    public static class Clothe{
        int s;
        int e;
        int score;

        public Clothe(int s, int e, int score){
            this.s=s;
            this.e=e;
            this.score=score;
        }
    }
}