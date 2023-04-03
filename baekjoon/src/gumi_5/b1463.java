package gumi_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class b1463 {
	static int min = Integer.MAX_VALUE;
	static int store[];
	public static int dp(int num) {
		if(store[num] == Integer.MAX_VALUE) {
			if(num % 6 == 0) {
				store[num] = Math.min(dp(num / 3), Math.min(dp(num / 2), dp(num - 1))) + 1;
			}
			else if(num % 3 == 0) {
				store[num] = Math.min(dp(num / 3), dp(num - 1)) + 1;
			}
			else if(num % 2 == 0) {
				store[num] = Math.min(dp(num / 2), dp(num - 1)) + 1;
			}
			else {
				store[num] = dp(num - 1) + 1;
			}
		}
		return store[num];
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		store = new int[num + 1];
		Arrays.fill(store, Integer.MAX_VALUE);
		store[0] = 0;
		store[1] = 0;
		
		System.out.println(dp(num));
	}
}
