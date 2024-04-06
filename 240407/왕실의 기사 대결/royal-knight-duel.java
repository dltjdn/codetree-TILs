import java.util.*;
import java.io.*;

public class Main {
	static int[] r;
	static int[] c;
	static int[] h;
	static int[] w;
	static int[] k;
	static int[] dup_k;
	static int[] nr;
	static int[] nc;
	static int[] dmg;
	static int L;
	static int N;
	static int[][] chess;


	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] line  = bf.readLine().split(" ");
		L = Integer.parseInt(line[0]);
		N = Integer.parseInt(line[1]);
		int Q = Integer.parseInt(line[2]);
		
		chess = new int[L+1][L+1];
		List<int[]> commands = new ArrayList<>();
	
		
		r = new int[N+1];
		c = new int[N+1];
		h = new int[N+1];
		w = new int[N+1];
		k = new int[N+1];
		nr = new int[N+1];
		nc = new int[N+1];
		dmg = new int[N+1];
		dup_k = new int[N+1];
		
	
		for(int i=1; i<=L; i++) {
			String[] row = bf.readLine().split(" ");
			for(int j=1; j<=L; j++) {
				chess[i][j] = Integer.parseInt(row[j-1]);
			}			
		}
		
		for(int i=1; i<=N; i++) {
			String[] row = bf.readLine().split(" ");
			r[i] = Integer.parseInt(row[0]);
			c[i] = Integer.parseInt(row[1]);
			h[i] = Integer.parseInt(row[2]);
			w[i] = Integer.parseInt(row[3]);
			k[i] = Integer.parseInt(row[4]);
			dup_k[i] = k[i];
	
		}
		
		
		for(int i=1; i<=Q; i++) {
			String[] row = bf.readLine().split(" ");
			int ii = Integer.parseInt(row[0]);
			int d = Integer.parseInt(row[1]);
			commands.add(new int[] {ii,d});
		}
		
		
		for(int[] command : commands) {
			int idx = command[0];
			int d = command[1];
			if(k[idx] <= 0) continue; // 체스판에서 사라진 기
			
			// 이동가능?
			if(tryMove(idx,d)) {
				for(int i=1; i<=N; i++) {
					r[i] = nr[i];
					c[i] = nc[i];
					k[i] -= dmg[i];
				}
				
			}
			
		}
		
		int answer = 0;
		
		for(int i=1; i<=N ; i++) {
			if(k[i] > 0) {
				answer += (dup_k[i]-k[i]);
			}
		}
		
		
		System.out.println(answer);
		
	}
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1}; 
	
	public static boolean tryMove(int idx, int d) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			nr[i] = r[i];
			nc[i] = c[i];
			dmg[i] = 0;
			visited[i] = false;
		}
		
		
		queue.add(idx);
		
		visited[idx] = true;
		
		
		while(!queue.isEmpty()) {
			
			int x = queue.remove();
			
			nr[x] += dx[d];
			nc[x] += dy[d];
			
			
			// 이동했을  범위 벗어나는지? 
			if(nr[x]<1 || nr[x]+h[x]-1 > L || nc[x]<1 || nc[x]+w[x]-1 > L) {
				return false;
			}
			
			
			// 이동했을 때 장애물만나거나 벽인지
		
			for(int i=nr[x] ; i<=nr[x]+h[x]-1; i++) {
				for(int j=nc[x]; j<=nc[x]+w[x]-1; j++) {
					
					if(chess[i][j] == 2) {
						return false;
					}else if(chess[i][j] ==1) {
						dmg[x]++;
					}
					
				}
			}
			
			
			
			//이동했을 때 다른 기사랑 겹치는지
			for(int i=1; i<=N; i++) {
				
				if(visited[i] || k[i] <= 0) continue;
					
				if(r[i]+h[i]-1 < nr[x] || r[i] > nr[x]+h[x]-1) continue;
				
				if(c[i]+w[i]-1 < nc[x] || c[i] > nc[x]+w[x]-1) continue;
					
				
				queue.add(i);
				visited[i] = true;			
			}
			
			
		}
		
		dmg[idx] = 0;
		
		
	
		return true;
		
		
	}
	

}