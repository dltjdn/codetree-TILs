import java.util.*;
public class Main {
    public static int n;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        long left = 1;
        long right = Integer.MAX_VALUE;
        long minNum = Integer.MAX_VALUE;
        while(left <= right){
            long mid = (left + right) / 2;

            if(cntNum(mid) >= n){
                right = mid - 1;
                minNum = Math.min(minNum, mid);
            }else{
                left = mid + 1;
            }
        }

        System.out.println(minNum);
        
    }

    public static long cntNum(long num){
        long moo = num/3 + num/5 - num/15;

        return num-moo;
    }
}