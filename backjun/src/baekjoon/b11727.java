package baekjoon;

import java.io.*;

//백준 11727 2xn 타일링 2
public class b11727 {
	static int store[];
	public static int dp(int n) {
		if(n == 1) return store[n] = 1;
		if(n == 2) return store[n] = 3;
		if(store[n] == 0) {
			store[n] = (dp(n - 1) % 10007 + (2 * dp(n - 2) % 10007)) % 10007;
		}
		return store[n];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		store = new int[N + 1];
		dp(N);
		
		System.out.println(store[N]);
	}
}
