import java.util.*;
public class Main {
    public static int n,m;
    public static int[] arr;
    public static int minValue = Integer.MAX_VALUE;
    public static int maxValue = Integer.MIN_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            minValue = Math.min(minValue, arr[i]);
            maxValue = Math.max(maxValue, arr[i]);
        }
        
        int left = minValue;
        int right = maxValue;
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
        int cur = arr[0];
        int i = 1;
        while(i < arr.length){
            if(arr[i] - cur < mid){
                i++;
                continue;
            }
            cnt++;
            cur = arr[i];
            i++;
        }

        return cnt >= m;
    }
}