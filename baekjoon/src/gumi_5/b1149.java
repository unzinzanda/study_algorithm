package gumi_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.IOException;

// 백준 1149 RGB거리
public class b1149 {
	static int N;
	static int color[][];
	static int price[][];
	static int min = Integer.MAX_VALUE;
	
	public static int select(int idx, int c) {
		if(color[idx][c] == Integer.MAX_VALUE) {
			if(c == 0) {
				color[idx][c] = price[idx][c] + Math.min(select(idx - 1, 1), select(idx - 1, 2));
			}
			else if(c == 1) {
				color[idx][c] = price[idx][c] + Math.min(select(idx - 1, 0), select(idx - 1, 2));
			}
			else if(c == 2) {
				color[idx][c] = price[idx][c] + Math.min(select(idx - 1, 1), select(idx - 1, 0));
			}
		}
		
		return color[idx][c];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// [n][0] : R / [n][1] : G / [n][2] : B
		price = new int[N][3];
		color = new int[N][3];
		for(int i = 0; i < N;i++ ) {
			Arrays.fill(color[i], Integer.MAX_VALUE);
		}
		
		for(int i = 0; i < N;i++) {
			String str[] = br.readLine().split(" ");
			for(int j = 0; j < str.length;j++) {
				price[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		color[0][0] = price[0][0];
		color[0][1] = price[0][1];
		color[0][2] = price[0][2];
		
		int min = Math.min(select(N - 1, 0), Math.min(select(N - 1, 1), select(N - 1, 2)));
		
		System.out.println(min);
	}
}
