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

        int cnt = 0;
        for(int i=1; i<n; i++){
            if(arr[i-1] == 0){
                arr[i-1] = 1;
                arr[i] ^= 1;

                if(i < n-1){
                   arr[i+1] ^= 1;
                }
                cnt++;
            }
        }

        // ** n==1 일수도 있으므로 n-2 은 인덱스 오류남
        if(arr[n-1] == 1){
            System.out.println(cnt);
        }else{
            System.out.println(-1);
        }
    }
}