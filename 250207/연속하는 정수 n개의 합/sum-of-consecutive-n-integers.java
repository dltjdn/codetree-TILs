import java.util.*;
public class Main {
    public static int n,m;
    public static int[] arr;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        int cnt = 0;
        int right = 0;
        int sum = 0;
        for(int left=0; left<n; left++){
            while(right < n && sum+arr[right] <= m){
                sum += arr[right];
                right++;
            }

            if(sum == m){
                cnt++;
            }

            sum -= arr[left];
        }

        System.out.println(cnt);

    }
}