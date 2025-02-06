import java.util.*;
public class Main {
    public static int n,m;
    public static List<Node>[] graph;
    public static int[] dist;
    public static Queue<Node> pq = new PriorityQueue<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new ArrayList[n+1];
        dist = new int[n+1];

        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        Arrays.fill(dist, (int)1e9);

        for(int i=0; i<m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();

            graph[x].add(new Node(y, d));
            graph[y].add(new Node(x, d));
        }

        dist[n] = 0;
        pq.add(new Node(n, 0));

        while(!pq.isEmpty()){
            int minIdx = pq.peek().idx;
            int minDist = pq.peek().dist;
            pq.remove();

            if(dist[minIdx] != minDist) continue;

            for(int i=0; i<graph[minIdx].size(); i++){
                int targetIdx = graph[minIdx].get(i).idx;
                int targetDist = graph[minIdx].get(i).dist;

                int newDist = targetDist + dist[minIdx];
                if(dist[targetIdx] > newDist){
                    dist[targetIdx] = newDist;
                    pq.add(new Node(targetIdx, newDist));
                }

            }
        }

        int ans = Integer.MIN_VALUE;
        for(int i=1; i<=n; i++){
            ans = Math.max(ans, dist[i]);
        }
        System.out.println(ans);

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
    public int  compareTo(Node n){
        return Integer.compare(this.dist, n.dist);
    }
}