import java.util.*;
public class Main {
    public static Map<Integer,Integer> map = new HashMap<>();
    public static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        
        visited = new boolean[m+1];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
       
        int ans = Integer.MAX_VALUE;
        int right = 0;
        for(int left=0; left<n; left++){
            // 1. 1~M 존재할때까지 구간 늘리기
            while(right<n && map.keySet().size() < m){
                map.put(arr[right], map.getOrDefault(arr[right], 0)+1);
                right++;
            }
            //System.out.println(left+ " "+right);
            if(right == n) break;
          
            //2. 구간 밖에서 1~M 존재 확인 
            Arrays.fill(visited, false);

            for(int i=0; i<left; i++){
                visited[arr[i]] = true;
            }
            for(int i=right; i<n; i++){
                visited[arr[i]] = true;
            }
            boolean flag2 = true;
            for(int i=1; i<=m; i++){
                if(!visited[i]) flag2 = false;
            }
            if(flag2) ans = Math.min(ans, right-left);
            
            // 3. left 구간에 포함된 수 제거 
            if(map.get(arr[left]) <= 1) map.remove(arr[left]);
            else map.put(arr[left], map.get(arr[left])-1);
        }

        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);

    }
}