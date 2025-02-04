import java.util.*;
public class Main {
    public static int n,m;
    public static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        // 1개의 정수를 분배해 같은 크기의 정수k를 10개
        int left = 1;
        int right = 100000;
        int ans = 0; // ** 초기값 0 설정 매우 중요 (n: 1, m: 10, arr: 1)
        while(left <= right){
            int mid = (left+right)/2;

            if(isPossible(mid)){
                left = mid + 1;
                ans = Math.max(ans, mid);
            }else{
                right = mid - 1;
            }
        }

        System.out.println(ans);

    }

    public static boolean isPossible(int maxNum){
        int cnt = 0;
        for(int i=0; i<n; i++){
            cnt += arr[i]/maxNum;
        }

        return cnt >= m;
    }
}