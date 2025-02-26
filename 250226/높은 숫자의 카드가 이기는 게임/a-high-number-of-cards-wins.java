import java.util.*;
public class Main {
    public static int n;
    public static int[] bCards,aCards;
    public static Set<Integer> bSet = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        bCards = new int[n];
        aCards = new int[n];

        for (int i = 0; i < n; i++) {
            bCards[i] = sc.nextInt();
            bSet.add(bCards[i]);
        }
        // Please Write your code.
        int idx = 0;
        for(int i=1; i<=2*n; i++){
            if(!bSet.contains(i)){
                aCards[idx++] = i;
            }
        }

        Arrays.sort(aCards);
        Arrays.sort(bCards);

        int aIdx = 0;
        int cnt = 0; // a > b 갯수
        for(int bIdx=0; bIdx<n; bIdx++){
            while(aIdx < n && bCards[bIdx] > aCards[aIdx]){
                aIdx++;
            }

            if(aIdx >= n) break;
            cnt++;
            aIdx++;
        }

        System.out.println(cnt);
    }

    
}