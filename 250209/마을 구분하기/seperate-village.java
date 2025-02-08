import java.util.*;
public class Main {
    public static int n;
    public static int[][] grid;
    public static int peopleCnt;
    public static List<Integer> nums = new ArrayList<>();
    public static boolean[][] visited;

    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        visited = new boolean[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                grid[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(canGo(i,j)){
                    peopleCnt = 0; // 리셋

                    dfs(i, j);

                    nums.add(peopleCnt);
                }
            }
        }

        System.out.println(nums.size());
        Collections.sort(nums);
        for(int i=0; i<nums.size(); i++){
            System.out.println(nums.get(i));
        }
        

    }

    public static boolean canGo(int r, int c){
        if(!inRange(r,c)) return false;
        if(visited[r][c] || grid[r][c] == 0) return false;
        return true;
    }

    public static boolean inRange(int r, int c){
        return r>=0 && r<n && c>=0 && c<n;
    }

    public static void dfs(int r, int c){
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};

        visited[r][c] = true;
        peopleCnt++;

        for(int d=0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if(canGo(nr,nc)){
                dfs(nr, nc);
            }
        }
    }
}