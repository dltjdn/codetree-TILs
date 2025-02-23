import java.util.*;
public class Main {
    public static int n;
    public static char[] arr1,arr2;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr1 = new char[n];
        arr2 = new char[n];
        arr1 = sc.next().toCharArray();
        arr2 = sc.next().toCharArray();

        int cnt = 0;
        for(int i=n-1; i>=0; i--){
            if(arr1[i] != arr2[i]){
                for(int j=i; j>=0; j--){
                    arr1[j] = arr1[j] == 'G' ? 'H' : 'G';
                }
                cnt++;
            }
        }

        System.out.println(cnt);

    }
}