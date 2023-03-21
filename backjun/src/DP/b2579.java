package DP;

import java.io.*;

// 백준 2579 계단 오르기
public class b2579 {
	static int N;
	static int num[];
	static int store[][];
	
	private static int dp(int n, int cnt) {
		if(store[n][cnt] <= 0) {
			if(cnt % 3 == 0) {
				store[n][cnt] = Math.max(dp(n + 1, cnt - 1), dp(n + 2, cnt - 1)) + num[n]; 
			}
		}
		
		return store[n][cnt];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		num = new int[N + 1];
		
		for(int i = 0;i < N;i++) {
			num[i + 1] = Integer.parseInt(br.readLine());
		}
		store = new int[N + 1][N + 1];
		store[N][1] = num[N];
		store[N - 1][2] = store[N][1] + num[N - 1];
		store[N - 2][2] = store[N][1] + num[N - 2];
		dp(N, 1);
	}
}
