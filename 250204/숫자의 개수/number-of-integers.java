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

        for(int i=0; i<m; i++){
            int target = sc.nextInt();

            System.out.println(upperBound(target)-lowerBound(target));
        }


    }

    public static int upperBound(int target){
        int left = 0;
        int right = n-1;
        int minIdx = n-1;

        while(left <= right){
            int mid = (left+right)/2;

            if(arr[mid] > target){
                right = mid-1;
                minIdx = Math.min(minIdx, right);
            }else{
                left = mid + 1;
            }
        }
        
        return minIdx;
    }

    public static int lowerBound(int target){
        int left = 0;
        int right = n-1;
        int minIdx = n-1;

        while(left <= right){
            int mid = (left+right)/2;

            if(arr[mid] >= target){
                right = mid-1;
                minIdx = Math.min(minIdx, right);
            }else{
                left = mid + 1;
            }
        }
        
        return minIdx;

    }
}