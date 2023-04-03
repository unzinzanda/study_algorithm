package baekjoon;

import java.io.*;

public class b11066 {
	static int N;
	static int cost[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0;t < T;t++) {
			N = Integer.parseInt(br.readLine());
			cost = new int[N];
			String str[] = br.readLine().split(" ");
			
			for(int i = 0;i < str.length;i++) cost[i] = Integer.parseInt(str[i]);
			
			
		}
	}
	
}
