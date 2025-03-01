import java.util.*;
public class Main {
    public static Map<Character, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        int k = sc.nextInt();

        int right = 0;
        int ans = -1;
        for(int left=0; left<n; left++){

            while(right < n){
                if(!map.containsKey(s.charAt(right))){
                    if(map.keySet().size() == k) break;
                    map.put(s.charAt(right), 1);
                }else{
                    map.put(s.charAt(right), map.get(s.charAt(right))+1);
                }

                right++;
            }

            ans = Math.max(right-left, ans);
            if(!map.containsKey(s.charAt(left))) continue;
            if(map.get(s.charAt(left)) == 1){
                map.remove(s.charAt(left));
            }else{
                map.put(s.charAt(left), map.get(s.charAt(left))-1);
            }
        }

        System.out.println(ans);


    }
}