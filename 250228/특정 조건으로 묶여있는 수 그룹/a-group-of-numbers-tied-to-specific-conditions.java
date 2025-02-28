import java.util.*;

public class Main {
    public static Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        
        Arrays.sort(arr);

        // 1 5 5 9 10 12 14

        // 최대 2개 뽑기
        int right = 0;
        for(int left=0; left<n; left++){
            while(right < n && arr[right]-arr[left] <= k){
                right++;
            }
            pq.add(right-left);

            if(right == n) break;
        }

        if(n==1) System.out.println(1);
        else System.out.println(pq.remove() + pq.remove());
    }
}