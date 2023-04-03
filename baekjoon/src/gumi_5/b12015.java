package gumi_5;

import java.io.*;

// 백준 12015 가장 긴 증가하는 부분 수열 2
public class b12015 {
	static int N;
	static int A[];
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		
		String str[] = br.readLine().split(" ");
		for(int i = 0; i < str.length;i++) A[i] = Integer.parseInt(str[i]);
		
		
		
		System.out.println(max);
	}
}
