import java.util.*;
public class Main {
    public static int n;
    public static int sr, sc, er, ec;
    public static List<Integer> nums = new ArrayList<>();
    public static int ans = Integer.MAX_VALUE;
    public static Map<Integer, Point> map = new HashMap<>();
    public static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        // Please write your code here.
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        for(int i=0; i<n; i++){
            String str = scanner.next();
            for(int j=0; j<n; j++){
                if(str.charAt(j) == 'S'){
                    sr = i;
                    sc = j;
                }else if(str.charAt(j) == 'E'){
                    er = i;
                    ec = j;
                }else if(str.charAt(j) != '.'){
                    int num = str.charAt(j) - '0';
                    //System.out.println(num);
                    map.put(num, new Point(i,j)); // 같은 숫자를 지닌 동전X
                    nums.add(num);
                }
                
            }
        }

        Collections.sort(nums);

        backtrack(0);

        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    public static int calDis(List<Integer> path){
        int dis = 0;
        Point p1 = map.get(path.get(0));
        Point p2 = map.get(path.get(1));
        Point p3 = map.get(path.get(2));

        dis += Math.abs(p1.r - sr) + Math.abs(p1.c - sc);
        dis += Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
        dis += Math.abs(p2.r - p3.r) + Math.abs(p2.c - p3.c);
        dis += Math.abs(p3.r - er) + Math.abs(p3.c - ec);

        return dis;
    }

    public static void backtrack(int s){
        if(path.size() == 3){
            //System.out.println(">>>");
            ans = Math.min(ans, calDis(path));
        }

        for(int i=s; i<nums.size(); i++){
            path.add(nums.get(i));
            backtrack(i+1);
            path.remove(path.size()-1);
        }

    }
}


class Point{
    int r;
    int c;

    public Point(int r, int c){
        this.r=r;
        this.c=c;
    }
}