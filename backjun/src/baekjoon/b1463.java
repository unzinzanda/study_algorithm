package baekjoon;

import java.io.*;

//백준 1463 1로 만들기
/*
* 1.연산하는 n이라는 숫자에서 가능한 연산을 모두 진행
* 		n이 6의 배수 : / 3, / 2, -1
* 		n이 3의 배수 : / 3, -1
* 		n이 2의 배수 : / 2, -1
* 		나머지 : -1
* 
* 2. 단, 연산의 수를 세는 cnt가 현재 결과값인 res(1이 되었을 때 최소연산의 수)보다 커질 경우
* 	      더 이상 연산을 할 필요가 없음 -> return
* 
* */
public class b1463 {
	static int res = Integer.MAX_VALUE;
	
	public static void makeOne(int n, int cnt) {
		if(cnt > res) return;
		if(n == 1) {
			res = Math.min(res, cnt);
			return;
		}
		
		if(n % 3 == 0) makeOne(n / 3, cnt + 1);
		if(n % 2 == 0) makeOne(n / 2, cnt + 1);
		
		makeOne(n - 1, cnt + 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		makeOne(N, 0);
		
		System.out.println(res);
	}
}
