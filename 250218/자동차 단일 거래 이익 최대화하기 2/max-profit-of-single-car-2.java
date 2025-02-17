import java.util.*;
public class Main {
    public static int n;
    public static int[] prices;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        prices = new int[n];
        for(int i=0; i<n; i++){
            prices[i] = sc.nextInt();
        }

        int ans = 0;
        int buy = Integer.MAX_VALUE; // 가장 쌀때
        for(int i=0; i<n; i++){
            if(buy <= prices[i]) continue;
            buy = prices[i];
            int sell = buy;

            for(int j=i+1; j<n; j++){
                if(sell >= prices[j]) continue;
                    
                sell = prices[j];
                ans = Math.max(ans, sell - buy);
            }
           
        }

        System.out.println(ans);
        
    }
}