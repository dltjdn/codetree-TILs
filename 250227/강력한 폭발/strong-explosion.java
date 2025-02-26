import java.util.*;
public class Main {
    public static int n;
    public static List<Point> bombs = new ArrayList<>();
    public static int ans = Integer.MIN_VALUE;
    public static boolean[][] visited;
    public static Point[][] area = {
        {new Point(-2,0), new Point(-1,0),new Point(1,0),new Point(2,0)},
        {new Point(0,-1), new Point(-1,0), new Point(1,0), new Point(0,1)},
        {new Point(-1,-1), new Point(1,1), new Point(-1,1), new Point(1,-1)}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(sc.nextInt()== 1){
                    bombs.add(new Point(i,j));
                }
            }
        }

        backtrack(0, new ArrayList<>());

        System.out.println(ans);
    }

    public static void backtrack(int cnt, List<Integer> path){
        if(cnt == bombs.size()){
            for(int i=0; i<n; i++) Arrays.fill(visited[i], false);

            for(int i=0; i<bombs.size(); i++){
                Point bomb = bombs.get(i);
                visited[bomb.r][bomb.c] = true;

                Point[] cur = area[path.get(i)];
                for(int j=0; j<4; j++){
                    if(!inRange(bomb.r+cur[j].r, bomb.c+cur[j].c)) continue;
                    visited[bomb.r+cur[j].r][bomb.c+cur[j].c] = true;
                }

            }

            int bombCnt = 0;
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(visited[i][j]) bombCnt++;
                }
            }
            ans = Math.max(ans, bombCnt);
            return;
        }

        for(int i=0; i<3; i++){
            path.add(i);
            backtrack(cnt+1, path);
            path.remove(path.size()-1);
        }
    }

    public static boolean inRange(int r, int c){
        return r>=0 && r<n && c>=0 && c<n;
    }
}

class Point{
    int r;
    int c;

    public Point(int r, int c){
        this.r=r;
        this.c=c;
    }
}