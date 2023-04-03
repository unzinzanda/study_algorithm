package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//백준 10844 쉬운 계단 수
public class b10844 {
	static int store[];
	public static int dp(int n) {
		if(n == 1) return store[1] = 9;
		if(store[n] == 0) {
			store[n] = ((2 * (dp(n - 1)) % 1000000000) - (n - 1)) % 1000000000;
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
