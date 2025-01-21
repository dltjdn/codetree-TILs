import java.util.*;
public class Main {
    public static int N,M;
    public static List<Integer>[] graph;
    public static boolean[] visited;
    public static int vertexCnt = 0;

    public static void dfs(int vertex){
        for(int i=0; i<graph[vertex].size(); i++){
            int curV = graph[vertex].get(i);
            if(!visited[curV]){
                visited[curV] = true;
                vertexCnt++;
                dfs(curV);
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        while(M --> 0){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        visited[1] = true;
        dfs(1);

        System.out.println(vertexCnt);

    }
}