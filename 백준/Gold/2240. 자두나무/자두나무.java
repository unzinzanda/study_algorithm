import java.io.*;
import java.util.Arrays;

//백준 2240 자두나무
public class Main {
	static int T, W, arr[], dp[][][];
	
	private static int pick() {
		dp[1][W][0] = 0;
		
		for (int time = 0; time < T; time++) {
			for (int cnt = 0; cnt <= W; cnt++) {
				for (int cur = 1; cur <= 2; cur++) {
					if(dp[cur][cnt][time] >= 0) {
						if (cur == arr[time]) {
							// 현재 위치에 자두 떨어짐 -> 이동하지 않고 자두 주움
							dp[cur][cnt][time + 1] = dp[cur][cnt][time] + 1;
						} else {
							// 현재 위치에 자두 떨어지지 않음 -> 이동해서 줍거나 이동하지 않고 줍지 않음
							int nextCur = arr[time];
							if (cnt != 0) {
								//이동
								// 현재 위치의 값과 하나 이동하기 전에 값 + 1 중 큰 값 선택
								dp[nextCur][cnt - 1][time + 1] = Math.max(dp[nextCur][cnt - 1][time + 1],
										dp[cur][cnt][time] + 1);
							}
							// 이동하지 않음
							dp[cur][cnt][time + 1] = dp[cur][cnt][time];
						}
					}
				}
			}
		}

		int res = 0;
		for(int pos = 1; pos <= 2;pos++) {
			for(int time = 1; time <= T;time++) {
				res = Math.max(res, dp[pos][0][time]);
			}
			for(int w = 0; w <= W;w++) {
				res = Math.max(res, dp[pos][w][T]);
			}
		}
		
		return res;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		T = Integer.parseInt(str[0]);
		W = Integer.parseInt(str[1]);
		arr = new int[T];
		dp = new int[3][W + 1][T + 1];
		
		for(int i = 0;i < T;i++) arr[i] = Integer.parseInt(br.readLine());
		
		for(int i = 0;i < 3;i++) {
			for(int j = 0;j <= W;j++) Arrays.fill(dp[i][j], -1);
		}
		
		System.out.println(pick());
	}
}