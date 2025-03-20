import java.util.*;
public class Main {
    public static int n;
    public static int[][] arr;
    public static int[][][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][3];
        dp = new int[3][n][3]; // 시작 열 i, j행, k열 
        for (int i = 0; i < n; i++) {
            for(int j=0; j<3; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        init();
        
        for(int col=0; col<3; col++){
            for(int i=1; i<n; i++){
                for(int j=0; j<3; j++){
                    for(int k=0; k<3; k++){
                        if(j == k) continue;
                        
                        dp[col][i][j] = Math.max(dp[col][i][j], dp[col][i-1][k] + arr[i][j]);
                    }
                }
              
            }
        }

        // for(int col=0; col<3; col++){
        //     for(int j=0; j<3; j++){
        //         if(col == j) continue;

        //         for(int k=0; k<3; k++){
        //             if(j == k) continue;

        //             dp[col][n-1][j] = Math.max(dp[col][n-1][j], dp[col][n-2][k]+arr[n-1][j]);
        //         }
            
        //     }
        // }

        int ans = Integer.MIN_VALUE;
        for(int col=0; col<3; col++){
            for(int j=0; j<3; j++){
                if(col == j) continue;
                ans = Math.max(ans, dp[col][n-1][j]);
            }
        }
        System.out.println(ans);
    }

    public static void init(){
        for(int col=0; col<3; col++){
            for(int i=0; i<n; i++){
                Arrays.fill(dp[col][i], Integer.MIN_VALUE);
            }
        }

        for(int col=0; col<3; col++){
            dp[col][0][col] = arr[0][col];
        }
    }
}