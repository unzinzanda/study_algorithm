package DP;

import java.io.*;
import java.util.Arrays;

// 백준 11053 가장 긴 증가하는 부분 수열
public class b11053 {
	static int N;
	static int num[];
	static int store[];
	
	// 현재 숫자까지의 최대 수열 = 자신보다 작은 수들의 수열 + 1
	// 최대 수열의 길이를 찾아야 하므로 그 중에 최댓값
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		String str[] = br.readLine().split(" ");
		for(int i = 0;i < str.length;i++) num[i] = Integer.parseInt(str[i]);
		
		store = new int[N];
		// 기본적으로 자기자신은 수열에 포함 -> 전체를 1로 초기화
		Arrays.fill(store, 1);
		for(int i = 1;i < N;i++) {
			for(int j = 0;j < i;j++) {
				// 자신보다 작은 수를 만나면 수열의 길이 + 1을 얻어오고 현재 지니고 있던 수열의 길이와 비교하여 큰 값 저장
				if(num[j] < num[i]) {
					store[i] = Math.max(store[j] + 1, store[i]);
				}
			}
		}
		
		int max = 0;
		for(int i = 0;i < N;i++) max = Math.max(max, store[i]);
		
		System.out.println(max);
	}
}
