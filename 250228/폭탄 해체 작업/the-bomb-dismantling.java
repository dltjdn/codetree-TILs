import java.util.*;
public class Main {
    public static Queue<Bomb> pq = new PriorityQueue<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            pq.add(new Bomb(sc.nextInt(), sc.nextInt()));
        }
        
        int curTime= 0;
        int sum = 0;
        while(!pq.isEmpty()){
            Bomb b = pq.remove();

            if(b.time >= curTime + 1){
                sum += b.score;
                curTime++;
            }
        }
        
        System.out.println(sum);
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
    public int compareTo(Bomb b){
        if(this.time != b.time){
            return Integer.compare(this.time, b.time);
        }else{
            return Integer.compare(b.score, this.score);
        }
    }

}