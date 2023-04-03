import java.io.*;

//백준 2579 계단 오르기
/*
 * step == 0 : 현재 계단을 안 밟을 예정 -> 전 계단은 무조건 밟았어야 함.(두 칸 건너 뛰기 안되니까)
 * step == 1 : 현재 계단을 밟을 예정 -> 이전 계단은 밟았어도 되고 안 밟았어도 됨.
 * */

public class Main {
	static int arr[], dp[][];
	static int N;
	
	private static int calc(int n, int step) {
		if(n == 0) return dp[n][step] = arr[n];
		if(n == 1) {
			if(step == 0) return dp[n][step] = arr[n - 1];
			else return dp[n][step] = arr[n] + arr[n - 1];
		}
		if(n == 2) {
			if(step == 0) return dp[n][step] = calc(n - 1, 1);
			else return dp[n][step] = Math.max(arr[n - 2] + arr[n], arr[n - 1] + arr[n]);
		}
		if(dp[n][step] == 0) {
			if(step == 0) {
				dp[n][step] = calc(n - 1, 1);
			} else if(step == 1) {
				dp[n][step] = Math.max(calc(n - 1, 0) + arr[n], calc(n - 3, 1) + arr[n - 1] + arr[n]);
			}
		}
		
		return dp[n][step];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N][2];
		
		for(int i = 0;i < N;i++) arr[i] = Integer.parseInt(br.readLine());
		
		calc(N - 1, 1);
		
		System.out.println(dp[N - 1][1]);
	}
}