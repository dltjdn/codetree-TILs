import java.util.*;

public class Main {
    public static int K,N;
    public static List<Integer> selectedNums = new ArrayList<>();

    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        N = sc.nextInt();

        choose(0);

    }

    public static void print(){
        for(int i=0; i<selectedNums.size();i++){
            System.out.print(selectedNums.get(i)+" ");
        }
        System.out.println();
    }

    public static void choose(int cnt){
        if(cnt == N){
            print();
            return;
        }

        for(int i=1; i<=K; i++){
            if(selectedNums.size() >= 2 && selectedNums.get(selectedNums.size()-1) == i 
            && selectedNums.get(selectedNums.size()-2) == i){
                continue;
            }

            selectedNums.add(i);
            choose(cnt+1);
            selectedNums.remove(selectedNums.size()-1);
        }
    }

}