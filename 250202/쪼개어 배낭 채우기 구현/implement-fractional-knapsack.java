import java.util.*;
public class Main {
    // ** 쪼개서 담을 수 있는 경우만 가능
    // ** 주의 ) 나눌 때 double로 변환해야함
    public static int n,m;
    public static Jewel[] jewels;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        jewels = new Jewel[n];

        for(int i=0; i<n; i++){
            jewels[i] = new Jewel(sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(jewels);

        double price = 0;
        for(int i=0; i<n; i++){
            if(m - jewels[i].w >= 0){
                m -= jewels[i].w;
                price += jewels[i].v;
            }else{
                price += jewels[i].v * (m / (double)jewels[i].w);
                break;
            }
            
        }
        System.out.printf("%.3f", price);

    }

    public static class Jewel implements Comparable<Jewel>{
        int w;
        int v;

        public Jewel(int w, int v){
            this.w=w;
            this.v=v;
        }

        @Override
        public int compareTo(Jewel j){
            return Double.compare(j.v/(double)j.w, this.v/(double)this.w);
        }
    }
}