import java.util.*;
public class Main {
    public static int n;
    public static int[] arr;
    public static int endOfArr;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        int s1 = sc.nextInt()-1;
        int e1 = sc.nextInt()-1;
        int s2 = sc.nextInt()-1;
        int e2 = sc.nextInt()-1;

        bomb(s1,e1, n);

        // System.out.println(endOfArr);
        // for(int i=0; i<endOfArr; i++){
        //     System.out.println(arr[i]);
        // }

        bomb(s2,e2, endOfArr);
       
        System.out.println(endOfArr);
        for(int i=0; i<endOfArr; i++){
            System.out.println(arr[i]);
        }

    }

    public static void bomb(int s, int e, int len){
        int[] temp = new int[n];
        endOfArr = 0;
        for(int i=0; i<len; i++){
            if(i < s || i > e){
                temp[endOfArr++] = arr[i];
            }
        } 

        for(int i=0; i<endOfArr; i++){
            arr[i] = temp[i];
        }

    }
}