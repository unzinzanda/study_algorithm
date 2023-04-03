package gumi_5;

import java.io.*;

// 백준 2869 달팽이는 올라가고 싶다
public class b2869 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		int A = Integer.parseInt(str[0]);
		int B = Integer.parseInt(str[1]);
		int V = Integer.parseInt(str[2]);
		
		int res = 1;
		V = V - A;
		if(V % (A - B) == 0) res += V / (A - B);
		else res += V / (A - B) + 1;
 		
		System.out.println(res);
	}
}
