package gumi_5;

import java.io.*;

//백준 1003 피보나치함수
public class b1003 {
	static class Cnt {
		public int zero;
		public int one;
		
		public Cnt(int zero, int one) {
			this.zero = zero;
			this.one = one;
		}
	}
	
	static Cnt cnt[];
	static int N;
	
	public static Cnt fiboCnt(int n) {
		if(n == 0) {
			cnt[n] = new Cnt(1, 0);
			return cnt[n];
		}
		if(n == 1) {
			cnt[n] = new Cnt(0, 1);
			return cnt[n];
		}
		if(cnt[n] == null) {
			cnt[n] = new Cnt(fiboCnt(n - 1).zero + fiboCnt(n - 2).zero, fiboCnt(n - 1).one + fiboCnt(n - 2).one);
		}
		
		return cnt[n];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		cnt = new Cnt[41];
		Cnt res = fiboCnt(40);
		
		for(int t = 0; t < T;t++) {
			N = Integer.parseInt(br.readLine());
			
			System.out.println(cnt[N].zero + " " + cnt[N].one);
		}
	}
}
