import java.util.*;
public class Main {
    public static int n;
    public static Integer[] arr; // ** Comparator쓰려면 int 안됨

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new Integer[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr, (o1, o2) -> {
            String a = Integer.toString(o1) + Integer.toString(o2);
            String b = Integer.toString(o2) + Integer.toString(o1);
            return b.compareTo(a);
        });

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            sb.append(arr[i]);
        }
        System.out.println(sb.toString());

    }
}