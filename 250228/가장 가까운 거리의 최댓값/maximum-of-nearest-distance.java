import java.util.*;
public class Main {
    public static int n,m;
    public static int a,b,c;
    public static Map<Integer, List<Node>> edges = new HashMap<>();
    public static Queue<Node> pq = new PriorityQueue<>();
    public static int[] dist;
    public static int[] minD;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        dist = new int[n+1];
        minD = new int[n+1];
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();
            edges.putIfAbsent(x, new ArrayList<>());
            edges.get(x).add(new Node(y,d));
            edges.putIfAbsent(y, new ArrayList<>());
            edges.get(y).add(new Node(x,d));
        }
        
        Arrays.fill(dist, (int)1e9);
        Arrays.fill(minD, (int)1e9);

        dikstra(a);
        dikstra(b);
        dikstra(c);

        int ans = -1;
        for(int i=1; i<=n; i++){
            ans = Math.max(ans, minD[i]);
        }
        System.out.println(ans);
        
        
    }

    public static void dikstra(int s){
            dist[s] = 0;
            pq.add(new Node(s, 0));
            minD[s] = 0;

            while(!pq.isEmpty()){
                int minX = pq.peek().x;
                int minDist = pq.peek().dist;
                pq.remove();

                if(dist[minX] != minDist) continue;
                if(!edges.containsKey(minX)) continue;

                for(int i=0; i<edges.get(minX).size(); i++){
                    int targetX = edges.get(minX).get(i).x;
                    int targetDist = edges.get(minX).get(i).dist;

                    int newDist = dist[minX] + targetDist;
                    if(newDist < dist[targetX]){
                        dist[targetX] = newDist;
                        pq.add(new Node(targetX, newDist));
                        minD[targetX] = Math.min(minD[targetX], newDist);
                    }
                }
            }
        
    }

}

class Node implements Comparable<Node>{
    int x;
    int dist;

    public Node(int x, int dist){
        this.x=x;
        this.dist=dist;
    }

    @Override
    public int compareTo(Node n){
        return Integer.compare(this.dist, n.dist);
    }
}