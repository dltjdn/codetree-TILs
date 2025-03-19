import java.util.*;
public class Main {
    public static int n,m;
    public static Line[] lines;
    public static long ans = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        lines = new Line[m];
        for (int i = 0; i < m; i++) {
            lines[i] = new Line(sc.nextLong(),sc.nextLong());
        }
        Arrays.sort(lines);

        long lo = 0;
        long hi = (long)1e18;
        while(lo <= hi){
            long mid = (lo+hi)/2; // 두 점 사이 거리

            if(isPossible(mid)){
                //System.out.println("??"+mid);
                lo = mid + 1;
                ans = Math.max(ans, mid);
            }else{
                hi = mid - 1;
            }
        }
        System.out.println(ans);
    }

    public static boolean isPossible(long mid){
        int cnt = 1;
        long cur = lines[0].s; // 현재 좌표
        int idx = 0;
        
        while(idx < m){
            long next = cur + mid;
            if(next < lines[idx].s){
                cnt++;
                cur = lines[idx].s;
                continue;
            }else if(next >= lines[idx].s && next <= lines[idx].e){
                cnt++;
                cur = next;
                continue;
            }
  
            idx++;
        }

        return cnt >= n;
    }

    static class Line implements Comparable<Line>{
        long s;
        long e;

        public Line(long s, long e){
            this.s=s;
            this.e=e;
        }

        @Override
        public int compareTo(Line l){
            return Long.compare(this.s, l.s);
        }
    }
}