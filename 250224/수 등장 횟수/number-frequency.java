import java.util.*;
public class Main {
    public static Map<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] queries = new int[m];
        for (int i = 0; i < m; i++) {
            queries[i] = sc.nextInt();
        }
        // Please Write your code.
        for(int i=0; i<n; i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i],1);
            }else{
                map.put(arr[i], map.get(arr[i])+1);
            }
        }

        for(int i=0; i<m; i++){
            if(map.containsKey(queries[i])){
                System.out.print(map.get(queries[i])+" ");
            }else{
                System.out.print("0 ");
            }
        }

    }
}