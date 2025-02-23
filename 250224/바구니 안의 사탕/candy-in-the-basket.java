import java.util.*;
public class Main {
    public static int n,k;
    public static int min,max;
    public static Map<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        for(int i=0; i<n; i++){
            int cnt = sc.nextInt();
            int x = sc.nextInt();
            min = Math.min(min, x);
            max = Math.max(max, x);

            if(!map.containsKey(x)){
                map.put(x, cnt);
            }else{
                map.put(x, map.get(x)+cnt);
            }
        }

        int right = min;
        int cnt = 0;
        int ans = 0;
        for(int left=min; left<=max-2*k; left++){
            while(right <= left+2*k){
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