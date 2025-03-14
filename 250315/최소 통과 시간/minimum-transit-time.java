import java.util.*;
public class Main {
    public static int n,m;
    public static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[m];
        for (int i = 0; i < m; i++)
            arr[i] = sc.nextInt();
       
        long left = 1;
        long right = (int)1e14; // **long 고려하기
        long ans = Integer.MAX_VALUE;
        while(left <= right){
            long mid = (left+right)/2; // 최소 시간

            if(isPossible(mid)){
                right = mid - 1;
                ans = Math.min(ans, mid);
            }else{
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    public static boolean isPossible(long mid){
        long cnt = 0;
        for(int i=0; i<m; i++){
            cnt += (mid / arr[i]);
        }
        return cnt >= n;
    }

}