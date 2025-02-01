import java.util.*;
public class Main {
    public static int n;
    public static int[] arr;
    public static int[] countArr;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        countArr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        int j=0;
        int ans = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            while(j<n && countArr[arr[j]] != 1){
                countArr[arr[j]] = 1;
                j++;
            }

            ans = Math.max(ans, j-i);

            countArr[arr[i]] = 0;
        }

        System.out.println(ans);
        
    }
}