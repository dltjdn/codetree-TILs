import java.util.*;
public class Main {
    // ** 동전 선택은 배수 관계일 때만 -> 그리디
    public static int n,k;
    public static int[] coins;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        coins = new int[n];

        for(int i=0; i<n; i++){
            coins[i] = sc.nextInt();
        }

        int cnt = 0 ;
        for(int i=n-1; i>=0; i--){
            if(k==0) break;
            cnt += k /coins[i];
            k %= coins[i];
        }

        System.out.println(cnt);
    }
}