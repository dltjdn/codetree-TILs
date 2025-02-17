import java.util.*;
public class Main {
    public static int n;
    public static List<List<Integer>> ans = new ArrayList<>();
    public static List<Integer> path = new ArrayList<>();
    public static boolean[] visited;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new boolean[n+1];

        backtrack();

           
    }

    public static void backtrack(){
        if(path.size() == n){
            for(int j=0; j<n; j++){
                System.out.print(path.get(j)+" ");
            }
            System.out.println();
            return;
        }

        for(int i=n; i>=1; i--){
            if(!visited[i]){
                path.add(i);
                visited[i] = true;

                backtrack();

                visited[i] = false;
                path.remove(path.size()-1);   
            }
        }
    }
}