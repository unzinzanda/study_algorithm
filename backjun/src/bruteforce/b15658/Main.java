package bruteforce.b15658;

import java.io.*;

//백준 15658 연산자 끼워넣기(2)
public class Main {
	static int N;
	static int M;
	static int ope[];
	static int num[];
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void calc(int idx, int res) {
		if(idx == N) {
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}
		for(int j = 0; j < 4;j++) {
			if(ope[j] != 0) {
				ope[j] -= 1;
				if(j == 0) calc(idx + 1, res + num[idx]);
				else if(j == 1) calc(idx + 1, res - num[idx]);
				else if(j == 2) calc(idx + 1, res * num[idx]);
				else if(j == 3) calc(idx + 1, res / num[idx]);
				ope[j] += 1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		ope = new int[4];
		
		String str[] = br.readLine().split(" ");
		for(int i = 0;i < N;i++) num[i] = Integer.parseInt(str[i]);
		
		str = br.readLine().split(" ");
		for(int i = 0; i < 4;i++) ope[i] = Integer.parseInt(str[i]);
		
		calc(1, num[0]);
		
		System.out.println(max);
		System.out.println(min);
	}
}
