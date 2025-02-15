import java.util.*;
public class Main {
    public static int n,m;
    public static int[] nums;
    public static List<Integer> path = new ArrayList<>();
    public static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        nums = new int[n];
        for(int i=0; i<n; i++){
            nums[i] = sc.nextInt();
        }

        backtrack(0);

        System.out.println(answer);
    }

    public static void backtrack(int start){
        if(path.size() == m){
            int maxNum = path.get(0);
            for(int i=1; i<m; i++){
                maxNum ^= path.get(i);
            }
            answer = Math.max(answer, maxNum);
            return;
        }


        for(int i=start; i<n; i++){
            path.add(nums[i]);
            backtrack(i+1);
            path.remove(path.size()-1);
        }
    }
}