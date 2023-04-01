package baekjoon;

import java.io.*;

//백준 7579 앱
public class b7579 {
	static int N, M;
	static int memory[];
	static int cost[];
	static Integer store[][];
	static int res = Integer.MAX_VALUE;
	
	private static int dp(int n, int c) {
		if(n < 0) return 0;
		if(c < 0) return 0;
		if(store[n][c] == null) {
			if(c < cost[n]) {
				store[n][c] = dp(n - 1, c);
			}
			else {
				store[n][c] = Math.max(dp(n - 1, c), Math.max(dp(n - 1, c - cost[n]) + memory[n], dp(n, c - 1)));
			}
			
		}
		
		if(store[n][c] >= M) res = Math.min(res, c);
		return store[n][c];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		memory = new int[N];
		cost = new int[N];
		
		str = br.readLine().split(" ");
		for(int i = 0;i < str.length;i++) memory[i] = Integer.parseInt(str[i]);
		
		str = br.readLine().split(" ");
		int total = 0;
		for(int i = 0;i < str.length;i++) {
			cost[i] = Integer.parseInt(str[i]);
			total += cost[i];
		}
		
		store = new Integer[N][total + 1];
		
		dp(N - 1, total);
		
		System.out.println(res);
	} 
}
