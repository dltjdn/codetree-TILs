import java.util.*;
public class Main {
    public static int n;
    public static List<Meeting> meetings = new ArrayList<>();
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for(int i=0; i<n; i++){
            meetings.add(new Meeting(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(meetings);

        int cnt = 1;
        int end = meetings.get(0).e;
        for(int i=1; i<n; i++){
            if(end <= meetings.get(i).s){
                end = meetings.get(i).e;
                cnt++;
            }
        }

        System.out.println(cnt);

    }

    public static class Meeting implements Comparable<Meeting>{
        int s;
        int e;

        public Meeting(int s, int e){
            this.s=s;
            this.e=e;
        }

        @Override
        public int compareTo(Meeting m){
            return Integer.compare(this.e, m.e); // 끝나는 순 오름차순
        }
    }
}