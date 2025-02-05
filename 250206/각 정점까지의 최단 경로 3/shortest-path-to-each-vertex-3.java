import java.util.*;
public class Main {
    public static int n,m;
    public static int[][] graph;
    public static boolean[] visited;
    public static int[] dist;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new int[n+1][n+1];
        visited = new boolean[n+1];
        dist = new int[n+1];

        for(int i=0; i<m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            graph[x][y] = z;
        }


        for(int i=1; i<=n; i++){
            dist[i] = (int)1e9;  // INT_MAX 그 자체로 설정하면
        // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
        // 적당히 큰 숫자로 적어줘야함에 유의
        }

        dist[1] = 0;
        for(int i=1; i<=n; i++){
             // V개의 정점 중 
            // 아직 방문하지 않은 정점 중
            // dist값이 가장 작은 정점을 찾아줍니다.
            int minIdx = -1;
            for(int j=1; j<=n; j++){
                if(visited[j]) continue;

                if(minIdx == -1 || dist[minIdx] > dist[j]){
                    minIdx = j;
                }
            }

            visited[minIdx] = true;

            for(int j=1; j<=n; j++){
                if(graph[minIdx][j] == 0) continue;

                dist[j] = Math.min(dist[j], dist[minIdx] + graph[minIdx][j]);
            }
        }

        for(int i=2; i<=n; i++){
            if(dist[i] == (int)1e9) System.out.println(-1);
            else System.out.println(dist[i]);
        }

    }
}