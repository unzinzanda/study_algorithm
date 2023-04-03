package DP;

import java.io.*;

// 백준 11726 2xn 타일링
// n >= 3 일 때, 2x1 타일을 한 개 고정 -> 남은 부분은 n - 1일 때 타일 놓는 경우의 수
//				2x1 타일을 두 개 고정 -> 남은 부분은 n - 2일 때 타일 놓는 경우의 수
public class b11726 {
	static int store[];
	
	static int dp(int n) {
		if(n == 1) return 1;
		if(n == 2) return 2;
		if(store[n] == 0) {
			store[n] = dp(n - 1) + dp(n - 2);
		}
		
		return store[n] % 10007;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		store = new int[N + 1];
		
		System.out.println(dp(N));
	}
}
