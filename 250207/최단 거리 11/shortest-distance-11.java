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

        // ** 초기화 필수
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


        // ** 도착점 b에서 시작
        dist[b] = 0;
        pq.add(new Node(b,0));

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
                }
            }
        }

        System.out.println(dist[a]);

        int x = a; //시작점
        List<Integer> vertex = new ArrayList<>();
        vertex.add(x);

        while(x != b){
            // ** 작은 번호의 간선을 먼저 선택하도록 정렬
            graph[x].sort((o1,o2) -> Integer.compare(o1.idx, o2.idx));

            for(Node neighbor: graph[x]){
                if(dist[neighbor.idx] + neighbor.dist == dist[x]){ // ** 작은 번호가 일치하면 break
                    x = neighbor.idx;
                    vertex.add(x);
                    break;
                }
            }
        }

        for(int i=0; i<vertex.size(); i++){
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
        return Integer.compare(this.dist, n.dist);
    }
}