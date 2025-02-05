import java.util.*;
public class Main {
    public static int n,m;
    public static int k;
    public static List<Node>[] graph;
    public static Queue<Node> pq = new PriorityQueue<>();
    public static int[] dist;
 
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        graph = new ArrayList[n+1];
        dist = new int[n+1];

        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();

            graph[x].add(new Node(y,z));
            graph[y].add(new Node(x,z));
        }


        for(int i=1; i<=n; i++){
            dist[i] = (int)1e9;
        }

        dist[k] = 0;
        pq.add(new Node(k, 0));

        while(!pq.isEmpty()){
            int minIdx = pq.peek().idx;
            int minDist = pq.peek().dist;
            pq.remove();

            if(minDist != dist[minIdx]) continue;

            for(int j=0; j<graph[minIdx].size(); j++){
                int targetIdx = graph[minIdx].get(j).idx;
                int targetDist = graph[minIdx].get(j).dist;

                int newDist = dist[minIdx] + targetDist;
                if(dist[targetIdx] > newDist){
                    dist[targetIdx] = newDist;
                    pq.add(new Node(targetIdx, newDist));
                }
            }

        }

        for(int i=1; i<=n; i++){
            if(dist[i] == (int)1e9) System.out.println(-1);
            else System.out.println(dist[i]);
        }
    }

}

class Node implements Comparable<Node>{
    int idx;
    int dist;

    public Node(int idx, int dist){
        this.idx=idx;
        this.dist=dist;
    }

    @Override
    public int compareTo(Node n){
        return Integer.compare(this.dist, n.dist);
    }
}