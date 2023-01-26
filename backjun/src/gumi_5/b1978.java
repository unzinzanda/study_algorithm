package gumi_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//백준 1978 소수 찾기
public class b1978 {
	public static boolean prime[] = new boolean[1001];
	
	public static void calcPrime() {
		prime[1] = true;
		for(int i = 2; i < 1001;i++) {
			for(int j = 2; j < i;j++) {
				if(i % j == 0) {
					prime[i] = true;
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		calcPrime();
		
		int cnt = 0;
		String str = br.readLine();
		String strs[] = str.split(" ");
		for(int i = 0; i < N;i++) {
			if(!prime[Integer.parseInt(strs[i])]) cnt++;
		}
		
		System.out.println(cnt);
	}
}
