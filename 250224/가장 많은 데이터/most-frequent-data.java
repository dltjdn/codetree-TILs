import java.util.*;
public class Main {
    public static Map<String,Integer> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }
     
        for(int i=0; i<n; i++){
            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            }else{
                map.put(arr[i], 1);
            }
        }

        int ans = 1;
        for(String key : map.keySet()){
            ans = Math.max(ans, map.get(key));
        }

        System.out.println(ans);
    }
}