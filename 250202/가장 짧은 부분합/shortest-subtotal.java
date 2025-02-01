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
        long sum = 0;
        int ans = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            while(j < n && sum + arr[j] < s){
                sum += arr[j];
                j++;
            }

            if(i == 0 && j == n && sum + arr[j] < s){
                ans = -1;
            }

            ans = Math.min(j-i+1, ans);

            sum -= arr[i];
        }

        System.out.println(ans);
        
    }
}