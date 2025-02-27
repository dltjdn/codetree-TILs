import java.util.*;
public class Main {
    public static Map<Integer,List<Integer>> map = new HashMap<>(); // 값 : 위치 리스트
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        int ans = -1;

        for(int i=0; i<n; i++){
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        for(int key : map.keySet()){
            if(map.get(key).size() < 2) continue;

            for(int i=0; i<map.get(key).size()-1; i++){
                if(map.get(key).get(i+1) - map.get(key).get(i) <= k){
                    ans = Math.max(ans, key);
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}