import java.util.*;

public class Main {
    public static int n;
    public static String str1, str2;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        str1 = sc.next();
        str2 = sc.next();
        
        boolean flag = false;
        int cnt = 0;
        for(int i=0; i<n; i++){
            if(str1.charAt(i) != str2.charAt(i)){
                flag = true;
            }

            if(str1.charAt(i) == str2.charAt(i) && flag){
                cnt++;
                flag = false;
            }
        }

        System.out.println(cnt);
        
    }
}