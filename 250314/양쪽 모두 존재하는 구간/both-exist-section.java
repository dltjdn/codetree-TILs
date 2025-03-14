import java.util.*;
public class Main {
    public static Map<Integer,Integer> in = new HashMap<>();
    public static Map<Integer,Integer> out = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            out.put(arr[i], out.getOrDefault(arr[i], 0)+1);
        }
       
        int ans = Integer.MAX_VALUE;
        int right = 0;
        for(int left=0; left<n; left++){
            // 1. 1~M 존재할때까지 구간 늘리기
            while(right<n && in.keySet().size() < m){
                in.put(arr[right], in.getOrDefault(arr[right], 0)+1);
                
                if(out.get(arr[right]) <= 1) out.remove(arr[right]);
                else out.put(arr[right], out.get(arr[right])-1);
                
                right++;
            }
            //System.out.println(left+ " "+right);
          
            //2. 구간 밖에서 1~M 존재 확인 
            if(in.keySet().size() == m && out.keySet().size() == m){
                
                ans = Math.min(ans, right-left);
            }
            
            // 3. left 구간에 포함된 수 제거 
            if(in.get(arr[left]) <= 1) in.remove(arr[left]);
            else in.put(arr[left], in.get(arr[left])-1);
            
            out.put(arr[left], out.getOrDefault(arr[left], 0)+1);
        }

        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);

    }
}