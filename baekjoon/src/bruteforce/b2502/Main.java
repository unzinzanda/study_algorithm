package bruteforce.b2502;

import java.io.*;

// 백준 2502 떡 먹는 호랑이
/*
 * 	문제 이름이 귀여워서 선택
 * 	처음 보자마자 느낌 : 피보나치? for문 돌면서 피보나치 느낌으로 돌려보기?
 * */
public class Main {
	static int D;
	static int K;
	static int num[];
	
	public static void findAB(int day) {
		if(day == D) {
			if(num[day - 1] == K) {
				System.out.println(num[0]);
				System.out.println(num[1]);
				System.exit(0);
			}
			return;
		}
		num[day] = num[day - 1] + num[day - 2];
		findAB(day + 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		D = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);
		num = new int[D];
		
		for(int i = 1; i < K;i++) {
			for(int j = i + 1; j <= K;j++) {
				num[0] = i;
				num[1] = j;
				findAB(2);
			}
		}
	}
}
