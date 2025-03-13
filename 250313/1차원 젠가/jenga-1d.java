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
        endOfArr = n;
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        int s1 = sc.nextInt()-1;
        int e1 = sc.nextInt()-1;
        int s2 = sc.nextInt()-1;
        int e2 = sc.nextInt()-1;

        bomb(s1,e1);
        bomb(s2,e2);
       
        System.out.println(endOfArr);
        for(int i=0; i<endOfArr; i++){
            System.out.println(arr[i]);
        }

    }

    public static void bomb(int s, int e){
        int[] temp = new int[n];
        int endOfTempArr = 0; // ** endOfArr, endOfTempArr 따로 선언

        for(int i=0; i<endOfArr; i++){
            if(i < s || i > e){
                temp[endOfTempArr++] = arr[i];
            }
        } 

        for(int i=0; i<endOfTempArr; i++){
            arr[i] = temp[i];
        }

        endOfArr = endOfTempArr;

    }
}