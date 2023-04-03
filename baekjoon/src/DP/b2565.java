package DP;

import java.io.*;
import java.util.Arrays;

// 백준 2565 전깃줄
public class b2565 {
	static int N;
	static Pair num[];
	static int dp[];
	static boolean visited[][];
	
	static class Pair implements Comparable<Pair>{
		int first;
		int second;
		
		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public int compareTo(Pair o) {
			return this.first - o.first;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		num = new Pair[N];
		dp = new int[N];
		
		for(int i = 0;i < N;i++) {
			String str[] = br.readLine().split(" ");
			num[i] = new Pair(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
			dp[i] = N - 1;
		}
		
		Arrays.sort(num);
		
		for(int i = 1;i < N;i++) {
		 	for(int j = 0;j < i;j++) {
		 		if(num[j].second<= num[i].second) {
	 				dp[i] = Math.min(dp[i], dp[j] - 1);
	 			}
		 	}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0;i < N;i++) {
			min = Math.min(min, dp[i]);
		}
		
		System.out.println(min);
	}
}
