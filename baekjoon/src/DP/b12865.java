package DP;

import java.io.*;

// 백준 12865 평범한 배낭
public class b12865 {
	static int N;
	static int K;
	static int W[];
	static int V[];
	static Integer dp[][]; // int로 하면 초기값 = 0, 값이 한 번도 안들어간 것과 0인걸 구분하기 어려움 따라서 integer 사용
	static boolean visited[];

	private static int choose(int n, int k) {
		if (n < 0)
			return 0;
		if (dp[n][k] == null) {
			if (W[n] > k) {
				dp[n][k] = choose(n - 1, k);
			} else {
				dp[n][k] = Math.max(choose(n - 1, k), choose(n - 1, k - W[n]) + V[n]);
			}
		}

		return dp[n][k];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");

		N = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);

		W = new int[N];
		V = new int[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			W[i] = Integer.parseInt(str[0]);
			V[i] = Integer.parseInt(str[1]);
		}
		dp = new Integer[N][K + 1];
		System.out.println(choose(N - 1, K));
	}
}

