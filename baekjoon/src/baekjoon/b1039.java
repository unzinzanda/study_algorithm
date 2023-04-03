package baekjoon;

import java.io.*;

// 백준 1039 교환
public class b1039 {
	static int N, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);
	}
}
