import java.util.*;

public class Main {
    public static int n;
    public static int[][] cost;
    public static boolean[] visited;
    public static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cost = new int[n+1][n+1];
        visited = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }
        
        backtrack(new ArrayList<>());

        System.out.println(ans);
        
    }

    public static void backtrack(List<Integer> path){
        if(path.size() == n-1){
            int sum = 0;
            
            if(cost[1][path.get(0)] == 0) return;
            sum += cost[1][path.get(0)];
            
            for(int i=0; i<n-2; i++){
                if(cost[path.get(i)][path.get(i+1)] == 0) return;
                sum += cost[path.get(i)][path.get(i+1)];
            }
            
            if(cost[path.get(n-2)][1] == 0) return;
            sum += cost[path.get(n-2)][1];
          
            ans = Math.min(ans, sum);
        }

        for(int i=2; i<=n; i++){
            if(visited[i]) continue;
            path.add(i);
            visited[i] = true;

            backtrack(path);

            visited[i] = false;
            path.remove(Integer.valueOf(i));
            
        }
    }
}