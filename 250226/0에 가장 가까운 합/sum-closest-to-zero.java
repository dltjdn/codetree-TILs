import java.util.*;
public class Main {
    public static int n;
    public static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        Arrays.sort(arr);

        // 1 1 1 2 2 124
        // -123 -11 1 2 3 
        int left = 0;
        int right = n-1;
        int m = Integer.MAX_VALUE;
        while(left < right){
            m = Math.min(m, Math.abs(arr[left]+arr[right]));
            if(arr[left]+arr[right] >= 0){
                right--;
            }else{
                left++;
            } 
        }

        System.out.println(m);
        
    }
}