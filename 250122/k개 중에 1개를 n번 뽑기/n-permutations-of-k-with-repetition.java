import java.util.*;

public class Main {
    public static int K,N;
    public static List<Integer> selectedNums = new ArrayList<>();

    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        N = sc.nextInt();

        findPermutations();

    }

    public static void print(){
        for(int i=0; i<selectedNums.size(); i++){
            System.out.print(selectedNums.get(i)+" ");
        }
        System.out.println();
    }

    public static void findPermutations(){
        if(selectedNums.size() == N){
            print();
            return;
        }

        for(int i=1; i<=K; i++){
            selectedNums.add(i);
            findPermutations();
            selectedNums.remove(selectedNums.size()-1);
        }


    }
}