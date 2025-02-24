import java.util.*;

public class Main {
    public static Set<Integer> set = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = sc.nextInt();
            set.add(arr1[i]);
        }
        int m = sc.nextInt();
        int[] arr2 = new int[m];
        for (int i = 0; i < m; i++) {
            arr2[i] = sc.nextInt();
        }
        // Please Write your code.
        for (int i = 0; i < m; i++) {
            if(set.contains(arr2[i])){
                System.out.print("1 ");
            }else{
                System.out.print("0 ");
            }
        }
        
    }
}