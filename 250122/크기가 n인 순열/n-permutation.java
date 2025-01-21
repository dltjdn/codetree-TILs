import java.util.*;
public class Main {
    public static int n;
    public static boolean[] visited;
    public static List<Integer> nums = new ArrayList<>();

    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new boolean[n+1];

        getPermutations();
        
    }

    public static void print(){
        for(int i=0; i<nums.size(); i++){
            System.out.print(nums.get(i)+" ");
        }
        System.out.println();
    }

    public static void getPermutations(){
        if(nums.size() == n){
            print();
            return;
        }

        for(int i=1; i<=n; i++){
            if(visited[i]) continue;

            visited[i] = true;
            nums.add(i);

            getPermutations();

            nums.remove(nums.size()-1);
            visited[i] = false;
        }
    }
}