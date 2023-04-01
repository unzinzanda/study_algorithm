package baekjoon;

import java.io.*;

//백준 2156 포도주 시식
/*
* i 번째 날에 와인을 마셨다고 가정
* 	1. i - 1번째 와인을 마셨을 경우, 3잔 연속으로 마실 수 없기 때문에 i - 2 번째 잔은 마실 수 없으므로 store[i - 3] + wine[i - 1] + wine[i]
* 	2. i - 1번째 와인을 안 마셨을 경우, store[i - 2] + wine[i]
* 
* i 번째 날 와인을 마시지 않았다면 store[i - 1]을 그대로 이어 받는다.
* 
* 이 세 가지 경우 중 가장 큰 값을 store[i] 자리에 넣는다.
* */
public class b2156 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int wine[] = new int[N];
	
		for(int i = 0;i < N;i++) wine[i] = Integer.parseInt(br.readLine());
		
		int store[] = new int[N];
		
		
		store[0] = wine[0];
		if(N == 2) {
			store[1] = wine[0] + wine[1];			
		}
		
		else if(N >= 3) {
			store[0] = wine[0];
			store[1] = wine[0] + wine[1];
			store[2] = Math.max(Math.max(store[0] + wine[2], wine[1] + wine[2]), store[1]);
			for(int i = 3; i < N;i++) {
				store[i] = Math.max(store[i - 2] + wine[i], store[i - 3] + wine[i - 1] + wine[i]);
				store[i] = Math.max(store[i - 1], store[i]);
			}
		}
		System.out.println(store[N - 1]);
	}
}
