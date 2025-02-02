import java.util.*;
public class Main {
    public static int n;
    public static Queue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for(int i=0; i<n; i++){
            pq.add(sc.nextInt());
        }

        int cost = 0;
        while(pq.size() > 1){
            int num1 = pq.remove();
            int num2 = pq.remove();

            cost += (num1 + num2);

            pq.add(num1+num2);
        }

        System.out.println(cost);

    }
}