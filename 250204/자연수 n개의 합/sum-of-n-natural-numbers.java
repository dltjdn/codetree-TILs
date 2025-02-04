import java.util.*;
public class Main {
    public static long s;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        s = sc.nextLong();

        long left = 1;
        long right = (int)1e9; // 
        long maxNum = 1;
        while(left <= right){
            long mid = (left + right) / 2;
            
            if(mid * (mid+1) / 2 <= s){
                left = mid + 1;
                maxNum = Math.max(maxNum, mid);
            }else{
                right = mid - 1;
            }
        }

        System.out.println(maxNum);
    }
}