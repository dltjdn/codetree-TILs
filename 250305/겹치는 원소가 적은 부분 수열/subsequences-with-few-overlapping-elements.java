import java.util.*;
public class Main {
    public static int n,k;
    public static int[] arr;
    public static Map<Integer,Integer> map = new HashMap<>();
    public static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int right=0;
        for(int left=0; left<n; left++){
            // ** getOrDefault 활용하기 
            while(right < n && map.getOrDefault(arr[right],0) < k){
                map.put(arr[right], map.getOrDefault(arr[right], 0) +1);
                right++;
            }

            ans = Math.max(ans, right-left);

    
            map.put(arr[left], map.get(arr[left])-1);
        }

        System.out.println(ans);

    }
}