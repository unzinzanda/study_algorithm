package gumi_5;

import java.io.*;

// 백준 11053 가장 긴 증가하는 부분 수열
public class b11053 {
	static int N;
	static int num[];
	static int len[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		String str[] = br.readLine().split(" ");
		num = new int[N];
		len = new int[N];
		for(int i = 0;i < str.length;i++) num[i] = Integer.parseInt(str[i]);
		
		// 0번 인덱스의 수열 길이는 무조건 1
		len[0] = 1;
		for(int i = 1;i < N;i++) {
			len[i] = 1;
			for(int j = 0;j < i;j++) {
				// 자신보다 작은 값을 만나면 그 값의 len + 1, 대신 최댓값을 찾아야하므로 max()사용
				if(num[j] < num[i]) len[i] = Math.max(len[j] + 1, len[i]);
			}
		}
		
		int max = 0;
		for(int i = 0; i < N;i++) {
			max = Math.max(max, len[i]);
		}
		
		System.out.println(max);
	}
}