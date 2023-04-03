package bruteforce.b14888;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 백준 14888 연산자끼워넣기
public class Main {
	
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int N;
	static int num[];
	static int ope[] = new int[4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		String str = br.readLine();
		String s[] = str.split(" ");
		num = new int[N];
		for(int i = 0; i < N;i++) num[i] = Integer.parseInt(s[i]);
		
		str = br.readLine();
		s = str.split(" ");
		for(int i = 0; i < 4;i++) ope[i] = Integer.parseInt(s[i]);
 		
 		dfs(num[0], 1);
 		
 		System.out.println(max);
 		System.out.println(min);
	}
	
	public static void dfs(int res, int idx) {
		if(idx == N) {
			min = Math.min(min, res);
			max = Math.max(res, max);
			return;
		}
		else {
			for(int i = 0;i < 4;i++) {
				if(ope[i] != 0) {
					ope[i]--;
					if(i == 0) dfs(res + num[idx], idx + 1);
					else if(i == 1) dfs(res - num[idx], idx + 1);
					else if(i == 2) dfs(res * num[idx], idx + 1);
					else dfs(res / num[idx], idx + 1);
					ope[i]++;
				}
			}
		}
	}
}