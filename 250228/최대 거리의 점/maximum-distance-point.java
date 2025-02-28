import java.util.*;
public class Main {
    public static int n,m;
    public static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        
        int left = 1;
        int right = (int)1e9;
        int ans = Integer.MIN_VALUE;
        while(left <= right){
            int mid = (left+right)/2;

            if(isPossible(mid)){
                left = mid + 1;
                ans = Math.max(ans, mid);
            }else{
                right = mid -1;
            }
        }
        System.out.println(ans);
    }

    public static boolean isPossible(int mid){
        int cnt = 1;
        int lastIdx = 0;

        for(int i=1; i<n; i++){
            if(arr[i] - arr[lastIdx] >= mid){
                cnt++;
                lastIdx = i;
            }
        }

        return cnt >= m;
    }
}