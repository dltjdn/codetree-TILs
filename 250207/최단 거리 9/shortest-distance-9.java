import java.util.*;
public class Main {
    public static int n,m;
    public static List<Node>[] graph;
    public static int[] dist;
    public static int[] path;
    public static int a,b;
    public static Queue<Node> pq = new PriorityQueue<>();
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        dist = new int[n+1];
        path = new int[n+1];
        graph = new ArrayList[n+1];

        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        Arrays.fill(dist, (int)1e9);

        for(int i=0; i<m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();

            graph[x].add(new Node(y,z));
            graph[y].add(new Node(x,z));
        }

        a = sc.nextInt();
        b = sc.nextInt();

        dist[a] = 0;
        pq.add(new Node(a,0));

        while(!pq.isEmpty()){
            int minIdx = pq.peek().idx;
            int minDist = pq.peek().dist;
            pq.remove();

            if(dist[minIdx] != minDist) continue;

            for(int i=0; i<graph[minIdx].size(); i++){
                int targetIdx = graph[minIdx].get(i).idx;
                int targetDist = graph[minIdx].get(i).dist;

                int newDist = dist[minIdx] + targetDist;
                if(dist[targetIdx] > newDist){
                    dist[targetIdx] = newDist;
                    pq.add(new Node(targetIdx, newDist));

                    path[targetIdx] = minIdx;
                }
            }
        }

        System.out.println(dist[b]);

        int x = b;
        List<Integer> vertex = new ArrayList<>();
        vertex.add(x);
        while(x != a){
            x  = path[x];
            vertex.add(x);
        }
        for(int i=vertex.size()-1; i>=0; i--){
            System.out.print(vertex.get(i) + " ");
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
        return Integer.compare(this.idx, n.dist);
    }
}