import java.util.*;
public class Main {
    public static int n,m,k;
    public static int[] nums;
    public static int[] step;
    public static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 턴의 수
        m = sc.nextInt(); // 윷놀이 판 상태
        k = sc.nextInt(); // 말의 수
        nums = new int[n];
        step = new int[2000];
        for(int i=0; i<n; i++){
            nums[i] = sc.nextInt(); // 각 턴마다 나아갈 수 있는 거리
        }

        // 조건 범위 작음 -> 완전탐색!
        backtrack(0);

        System.out.println(ans);
    }

    public static void backtrack(int idx){
        if(idx == n){
            int cnt = 0;
            for(int i=0; i<k; i++){
                if(step[i] >= m-1) cnt++;
            }
            
            ans = Math.max(ans, cnt);
            return;
        }

        for(int i=0; i<k; i++){
            if(step[i] > m-1) continue; // ** 조건추가

            step[i] += nums[idx];
                
            backtrack(idx+1);

            step[i] -= nums[idx];
        }
        
    }
}