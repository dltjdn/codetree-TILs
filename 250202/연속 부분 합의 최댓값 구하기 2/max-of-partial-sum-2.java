import java.util.*;
public class Main {
    public static int n;
    public static int[] arr;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        int sum = 0;
        for(int i=0; i<n; i++){
            if(sum < 0){
                sum = arr[i];
            }else{
                sum += arr[i];
            }
        }

        System.out.println(sum);

    }
}