import java.util.*;

public class Main {
    public static int n,k;
    public static int[][] grid,step;
    public static boolean[][] visited;
    public static int r1,c1,r2,c2;
    public static List<Point> walls = new ArrayList<>();
    public static List<Set<Point>> brokedList = new ArrayList<>();
    public static int minTime = Integer.MAX_VALUE;
    public static Queue<Point> q = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        grid = new int[n][n];
        step = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if(grid[i][j] == 1){
                    walls.add(new Point(i,j));
                }
            }
        }
        r1 = sc.nextInt()-1;
        c1 = sc.nextInt()-1;
        r2 = sc.nextInt()-1;
        c2 = sc.nextInt()-1;
        
       getBrokedWall(0, new HashSet<>());

       for(Set<Point> broked : brokedList){
            // for(Point temp: broked){
            //     System.out.println(temp.r+" "+temp.c);
            // }
            // System.out.println();
            // 초기화
            for(int i=0; i<n; i++){
                Arrays.fill(visited[i], false);
                Arrays.fill(step[i], 0);
            }
            q = new ArrayDeque<>();
         
            bfs(broked);
       }
        
        if(minTime == Integer.MAX_VALUE) minTime = -1;
        System.out.println(minTime);

    }


    public static void bfs(Set<Point> broked){
            int[] dr = {-1,0,1,0};
            int[] dc = {0,1,0,-1};

            q.add(new Point(r1,c1));
            visited[r1][c1] = true;

            while(!q.isEmpty()){
                Point cur = q.remove();

                if(cur.r == r2 && cur.c == c2){
                    minTime = Math.min(minTime, step[r2][c2]);
                    break;
                }

                for(int d=0;d<4;d++){
                    int nr = cur.r+dr[d];
                    int nc = cur.c+dc[d];
                    Point next = new Point(nr,nc);

                    if(next.inRange(n) && !visited[nr][nc]){
                        if(grid[nr][nc] == 0 || (grid[nr][nc] == 1 && broked.contains(next))){
                            q.add(next);
                            visited[nr][nc] = true;
                            step[nr][nc] = step[cur.r][cur.c] + 1;
                        }
                    }
                }
            }
    }

    public static void getBrokedWall(int start, Set<Point> path){
        if(path.size() == k){
            brokedList.add(new HashSet<>(path));
            return;
        }

        for(int i=start; i<walls.size(); i++){
            path.add(walls.get(i));

            getBrokedWall(i+1, path);

            path.remove(walls.get(i));
        }
    }
}

class Point{
    int r;
    int c;

    public Point(int r, int c){
        this.r=r;
        this.c=c;
    }

    public boolean inRange(int n){
        return this.r>=0 && this.r<n && this.c>=0 && this.c<n;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Point){
            Point p = (Point) o;
            return this.r == p.r && this.c == p.c;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.r, this.c);
    }
}