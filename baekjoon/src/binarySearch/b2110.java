package binarySearch;

import java.io.*;
import java.util.Arrays;

// 백준 2110 공유기 설치
public class b2110 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] =br.readLine().split(" ");
		
		int N = Integer.parseInt(str[0]);
		int C = Integer.parseInt(str[1]);
		
		int num[] = new int[N];
		for(int i = 0;i < N;i++) num[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(num);
		
		// 최소길이부터 시작해야함(1)
		int start = 1;
		int end = num[N - 1] - num[0];
		int max = 0;
		while(start <= end) {
			int mid = (start + end) / 2;
			int pre = num[0];
			int cnt = 1;
			for(int i = 0;i < N;i++) {
				if(num[i] - pre >= mid) {
					pre = num[i];
					cnt++;
				}
			}
			if(cnt >= C) {
				max = Math.max(max, mid);
				start = mid + 1;
			}
			else if(cnt < C) {
				end = mid - 1;
			}
		}
		
		System.out.println(max);
	}
}
