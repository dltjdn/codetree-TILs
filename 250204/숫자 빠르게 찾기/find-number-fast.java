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
            int idx = -1;
            int target = sc.nextInt();

            int left = 0, right = n-1;
            while(left <= right){
                int mid = (left+right)/2;
                if(arr[mid] == target){
                    idx = mid+1;
                    break;
                }

                if(arr[mid] > target){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }

            System.out.println(idx);
        }

    }

    
}