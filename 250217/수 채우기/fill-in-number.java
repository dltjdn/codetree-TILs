import java.util.*;
public class Main {
    public static int n;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int answer = -1;
        int fiveNum = n/5;
        while(fiveNum >=0 ){
            if((n - fiveNum * 5) % 2 == 0){
                answer = fiveNum + (n - fiveNum * 5) / 2;
                break;
            } 
            fiveNum--;
        }

        System.out.println(answer);
        
    }
}