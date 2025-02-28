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

        Arrays.sort(arr); //** 정렬 필수!!!!!
        
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
        int cur = arr[0];
        int i = 0;
        while(i < arr.length){
            if(arr[i] - cur < mid){
                i++;
                continue;
            }
            cnt++;
            cur = arr[i];
        }

        return cnt >= m;
    }
}
