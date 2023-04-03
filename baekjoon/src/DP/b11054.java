package DP;

import java.io.*;

// 백준 11054 가장 긴 바이토닉 부분 수열
public class b11054 {
	static int num[];
	static int dp[];
	static int dp2[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		num = new int[N];
		dp = new int[N];
		dp2 = new int[N];
		
		String str[] = br.readLine().split(" ");
		for(int i = 0;i < str.length;i++) {
			num[i] = Integer.parseInt(str[i]);
			dp[i] = 1;
		}
		
		for(int i = 1;i < N;i++) {
			for(int j = 0;j < i;j++) {
				if(num[j] < num[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		}
		
		for(int i = N - 2; i >= 0;i--) {
			for(int j = N - 1; j >= i;j--) {
				if(num[j] < num[i]) dp2[i] = Math.max(dp2[i], dp2[j] + 1);
			}
		}
		
		int max = 0;
		for(int i = 0;i < N;i++) {
			max = Math.max(max, dp[i] + dp2[i]);
		}
		
		System.out.println(max);
	}
}
