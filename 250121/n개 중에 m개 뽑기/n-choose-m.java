import java.util.*;

public class Main {
    public static int N,M;
    public static List<Integer> nums = new ArrayList<>();
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        choose(0,1);
    }

    public static void print(){
        for(int i=0; i<nums.size(); i++){
            System.out.print(nums.get(i)+" ");
        }
        System.out.println();
    }

    public static void choose(int cnt, int start){
        if(cnt == M){
            print();
            return;
        }


        for(int i=start; i<=N; i++){
            nums.add(i);
            choose(cnt+1, i+1);
            nums.remove(nums.size()-1);
        }
    }
}