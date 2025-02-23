import java.util.*;
public class Main {
    public static int n,k;
    public static long min,max;
    public static Map<Long,Long> map = new HashMap<>();
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        for(int i=0; i<n; i++){
            long cnt = sc.nextInt();
            long x = sc.nextInt();
            min = Math.min(min, x);
            max = Math.max(max, x);

            if(!map.containsKey(x)){
                map.put(x, cnt);
            }else{
                map.put(x, map.get(x)+cnt);
            }
        }

        long right = min;
        long cnt = 0;
        long ans = 0;
        for(long left=min; left<=max-2*k; left++){
            while(right <= Math.min(left+2*k, max)){
                if(map.containsKey(right)){
                    cnt += map.get(right);
                }
                right++;   
            }

            ans = Math.max(ans, cnt);
            
            if(map.containsKey(left)){
                cnt -= map.get(left);
            }
        }


        System.out.println(ans);
    }
}