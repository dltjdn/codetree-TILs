import java.util.*;
public class Main {
    public static int n,m;
    public static int[] a;
    public static int[] b;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n];
        b = new int[m];
        for(int i=0; i<n; i++){
            a[i] = sc.nextInt();
        }
        for(int i=0; i<m; i++){
            b[i] = sc.nextInt();
        }

        if(isSubsequence()){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }

    public static boolean isSubsequence(){
        // ** 문제 잘 읽기 : B가 A의 부분 수열인지 판단
        int i=0;
        for(int j=0; j<m; j++){ 
            while(i<n && a[i] != b[j]){
                i++;
            }

            if(i==n){
                return false;
            }
            i++;
        }

        return true;
    }
}