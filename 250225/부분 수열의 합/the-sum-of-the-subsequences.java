import java.util.*;
public class Main {
    public static int n,m;
    public static int[] arr;
    public static boolean[] dp;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];
        dp = new boolean[m+1];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        // dp[i] : 합이 i일때 true,false
        dp[0] = true;
        for(int i=0; i<n; i++){
            for(int j=m; j>=arr[i]; j--){
                if(dp[j-arr[i]]){
                    dp[j] = true;
                }
            }
        }
    
        if(dp[m]){
            System.out.println("Yes");
        }else{
             System.out.println("No");
        }
        
    }
}