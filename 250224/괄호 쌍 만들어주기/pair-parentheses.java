import java.util.*;
public class Main {
    public static String a;
    public static int[] L,R;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        a = sc.next();
        int n = a.length();
        L = new int[n];
        R = new int[n];

        for(int i=n-2; i>=0; i--){
            if(a.charAt(i) == ')' && a.charAt(i+1) == ')'){
                R[i] = R[i+1]+1;
            }else{
                R[i] = R[i+1];
            }
        }

        long sum = 0;  
        for(int i=1; i<=n-3; i++){
            if(a.charAt(i-1) == '(' && a.charAt(i) == '('){
                sum += R[i+1];
            }
        }
        System.out.println(sum);


    }
}