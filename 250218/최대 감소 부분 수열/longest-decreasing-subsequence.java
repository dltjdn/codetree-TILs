import java.util.*;
public class Main {
    public static int n;
    public static int[] arr;
    public static int[] dp;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        dp = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.fill(dp, 1);

        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if(arr[j] > arr[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
                
            }
        
        }

        int ans = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
        

    }
}