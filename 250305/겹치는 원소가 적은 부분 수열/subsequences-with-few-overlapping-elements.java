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
            while(right < n){
                if(!map.containsKey(arr[right])){
                    map.put(arr[right],1);
                    ans = Math.max(ans, right-left+1);
                }else if(map.get(arr[right]) < k){
                    map.put(arr[right], map.get(arr[right])+1); 
                    ans = Math.max(ans, right-left+1);
                }else{
                    break;
                }
                
                right++;
  
            }

            if(map.get(arr[left]) == 1){
                map.remove(arr[left]);
            }else if(map.get(arr[left]) > 1){
                map.put(arr[left], map.get(arr[left])-1);
            }
        }

        System.out.println(ans);

    }
}