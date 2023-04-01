package baekjoon;

import java.io.*;

//백준 1912 연속합
/*
* i 번째 숫자까지의 연속합 중 최댓값 = i - 1 번째 숫자까지의 연속합의 최댓값 + i 번 숫자 혹은 i번째 숫자
* -> 연속합을 이어받을지 자신부터 다시 시작할지 선택
* */
public class b1912 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		
		String str[] = br.readLine().split(" ");
		
		for(int i = 0;i < N;i++) arr[i] = Integer.parseInt(str[i]);
		
		int result[] = new int[N];
		result[0] = arr[0];
		
		for(int i = 1;i < N;i++) {
			result[i] = Math.max(arr[i], result[i - 1] + arr[i]);
		}
		
		int ans = Integer.MIN_VALUE;
		for(int i = 0;i < N;i++) ans = Math.max(ans, result[i]);
		
		System.out.println(ans);
	}
}
