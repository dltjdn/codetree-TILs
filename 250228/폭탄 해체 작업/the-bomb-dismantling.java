import java.util.*;
public class Main {
    public static List<Bomb> bombs = new ArrayList<>();
    public static Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int maxTime = -1;
        for (int i = 0; i < n; i++) {
            int score = sc.nextInt();
            int time = sc.nextInt();
            bombs.add(new Bomb(score, time));
            maxTime = Math.max(maxTime, time);
        }

        Collections.sort(bombs);

        int ans = 0;
        int j = 0;
        for(int i=maxTime; i>=1; i--){
            while(j < bombs.size() && i <= bombs.get(j).time){
                pq.add(bombs.get(j).score);
                j++;
            }

            if(!pq.isEmpty()) ans += pq.remove();
        }

        System.out.println(ans);
        
    }
}

class Bomb implements Comparable<Bomb>{
    int score;
    int time;

    public Bomb(int score, int time){
        this.score = score;
        this.time = time;
    }

    @Override
    public int compareTo(Bomb b){ // 시간이 큰 순
        return Integer.compare(b.time, this.time);
        // if(this.time != b.time){
        //     return Integer.compare(this.time, b.time);
        // }else{
        //     return Integer.compare(b.score, this.score);
        // }
    }

}