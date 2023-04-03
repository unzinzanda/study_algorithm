package gumi_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b1806 {
	static int N;
	static int S;
	static int[] nums;
	static boolean[] visited;
	static int minLen = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		S = Integer.parseInt(str[1]);
		
		str = br.readLine().split(" ");
		nums = new int[N];
		visited = new boolean[N];
		for(int i = 0; i < str.length;i++) {
			nums[i] = Integer.parseInt(str[i]);
		}
		
		int sum = 0;
		int cnt = 0;
		if(S == 0) {
			System.out.println(1);
			System.exit(0);
		}
		else {
			for(int i = 0; i < N;i++) {
				sum = nums[i];
				cnt = 1;
				if(sum >= S) {
					minLen = Math.min(cnt, minLen);
					continue;
				}
				for(int j = i + 1; j < N;j++) {
					sum += nums[j];
					cnt += 1;
					if(sum >= S) {
						minLen = Math.min(cnt, minLen);
						break;
					}
				}
			}
		}
		
		if(minLen == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(minLen);
	}
}

