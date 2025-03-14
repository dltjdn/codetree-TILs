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
       
        int left = 0;
        int right = (int)1e10;
        int ans = Integer.MAX_VALUE;
        while(left <= right){
            int mid = (left+right)/2; // 최소 시간

            if(isPossible(mid)){
                right = mid - 1;
                ans = Math.min(ans, mid);
            }else{
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    public static boolean isPossible(int mid){
        int sum = 0;
        for(int i=0; i<m; i++){
            sum += (mid / arr[i]);
        }
        return sum >= n;
    }

}