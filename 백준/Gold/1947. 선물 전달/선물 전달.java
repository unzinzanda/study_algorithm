import java.io.*;

//백준 1947 선물 전달
public class Main {
	static long store[];
	private static long dp(int n) {
		if(n == 1) return 0;
		if(store[n] == 0) {
			if(n % 2 == 0) store[n] = n * dp(n - 1) % 1000000000 + 1;
			else if(n % 2 == 1) store[n] = n * dp(n - 1) % 1000000000 - 1;
		}
		return store[n] % 1000000000;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		store = new long[N + 1];
		System.out.println(dp(N));
	}
}