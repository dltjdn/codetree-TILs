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
        // Please write your code here.
        int ans = Integer.MAX_VALUE;
        int right = 0;
        for(int left=0; left<n; left++){
            while(right<n){
                map.put(arr[right], map.getOrDefault(arr[right], 0)+1 );

                boolean flag = true;
                for(int i=1; i<=m; i++){
                    if(!map.containsKey(i)){
                        flag = false;
                        break;
                    }
                }
                if(flag) break;
                right++;
               
            }
            if(right == n) break;
            //System.out.println(left+ " "+right);
            ans = Math.min(ans, right-left+1);
            if(map.get(arr[left]) <= 1) map.remove(arr[left]);
            else map.put(arr[left], map.get(arr[left])-1);
        }

        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);

    }
}