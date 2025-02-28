import java.util.*;

public class Main {
    public static int[] L,R;
    public static Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        L = new int[n];
        R = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        
        Arrays.sort(arr);

        // 1 5 5 9 10 12 14
        // L[i] : i일때 최대 그룹길이
        int maxNum = Integer.MIN_VALUE;
        int i=0; 
        for(int j=1; j<n; j++){
            while(i+1 <= j && arr[j]-arr[i] > k){
                i++;
            }
            maxNum = Math.max(j-i+1, maxNum);

            L[j] = maxNum;
        }

        // R[i] : i일때 최대 그룹 길이
        maxNum = Integer.MIN_VALUE;
        i = n-1;
        for(int j=n-1; j>=0; j--){
            while(j <= i-1 && arr[i]-arr[j] > k){
                i--;
            }
            maxNum = Math.max(i-j+1, maxNum);

            R[j] = maxNum;
        }

        int ans = -1;
        for(int idx=0; idx<n-1; idx++){
            ans = Math.max(ans, L[idx] + R[idx+1]);
        }
        System.out.println(ans);
    
    }
}