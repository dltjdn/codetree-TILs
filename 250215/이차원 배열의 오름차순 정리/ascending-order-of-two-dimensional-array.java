import java.util.*;
public class Main {
    public static long n,k;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextLong();
        k = sc.nextLong();

        long lo = 0;
        long hi = n * n;
        long ans = n * n;

        while(lo <= hi){
            long mid = (lo + hi) / 2; // 특정 숫자 지정

            // mid 라는 숫자가 몇번째인지
            long val = 0;
            for(int i=1; i<=n; i++){ // 한 행씩
                val += Math.min(n, mid/i);
            }

            if(val >= k){
                hi = mid - 1;
                ans = Math.min(ans, mid);
            }else{
                lo = mid + 1;
            }
          
        }

        System.out.println(ans);
    }

}