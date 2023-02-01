package gumi_5;

import java.io.*;

public class b2798 {
	static int N;
	static int M;
	static int num[];
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		num = new int[N];
		
		str = br.readLine().split(" ");
		for(int i = 0; i < N;i++) num[i] = Integer.parseInt(str[i]);
		
		int res = 0;
		for(int i = 0; i < N - 2;i++) {
			for(int j = i + 1; j < N - 1;j++) {
				for(int k = j + 1;k < N;k++) {
					res = num[i] + num[j] + num[k];
					if(res <= M) {
						max = Math.max(res, max);
						if(max == M) {
							System.out.println(max);
							System.exit(0);
						}
					}
				}
			}
		}
		
		
		System.out.println(max);
	}
}
