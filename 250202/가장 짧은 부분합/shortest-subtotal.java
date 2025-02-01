import java.util.*;
public class Main {
    //** 투포인터 : 2개의 포인터가 한 방향으로만 계속 전진
    public static int n,s;
    public static int[] arr;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = sc.nextInt();
        arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        int j = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            while(j < n && sum < s){
                sum += arr[j];
                j++;
            }

            // 최대한 이동했는데도 sum이 s가 되지 못함
            if(sum < s){
                // ans = -1 (X) -> 기존 ans도 초기화됨
                break;
            }

            ans = Math.min(j-i, ans);

            sum -= arr[i];
        }

        if(ans == Integer.MAX_VALUE){
            ans = -1;
        }

        System.out.println(ans);
        
    }
}