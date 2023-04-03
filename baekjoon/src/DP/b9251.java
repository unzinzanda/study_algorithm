package DP;

import java.io.*;

// 백준 9251 LCS
public class b9251 {
	static String str1[];
	static String str2[];
	static int comp[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine().split("");
		str2 = br.readLine().split("");
		
		int N = Math.max(str1.length, str2.length);
		comp = new int[N + 1][N + 1];
		
		for(int x = 0;x < str1.length;x++) {
			for(int y = 0;y < str2.length;y++) {
				if(str1[x].equals(str2[y])) comp[x + 1][y + 1] = comp[x][y] + 1;
				else comp[x + 1][y + 1] = Math.max(comp[x][y + 1], comp[x + 1][y]);
			}
		}
		
		System.out.println(comp[str1.length][str2.length]);
	}
}
