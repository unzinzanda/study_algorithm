package backjun;

import java.io.*;

// 백준 17070 파이프 옮기기 1
public class b17070 {
	static int N;
	static int house[][];
	static int dp[][][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		house = new int[N + 1][N + 1];
		
		for(int i = 1;i <= N;i++) {
			String str[] = br.readLine().split(" ");
			for(int j = 0;j < str.length;j++) house[i][j + 1] = Integer.parseInt(str[j]);
		}
		// 0 : 가로, 1 : 세로, 2 : 대각선
		dp = new int[3][N + 1][N + 1];
		
		dp[0][1][2] = 1;
		
		for(int x = 1;x <= N;x++) {
			for(int y = 1;y <= N;y++) {
				if(x == 1 && y == 2) {
					if(house[x][y] != 1) dp[1][x][y] = dp[1][x - 1][y] + dp[2][x - 1][y];
					if(house[x][y] != 1 && house[x - 1][y] != 1 && house[x][y - 1] != 1) {
						dp[2][x][y] = dp[0][x - 1][y - 1] + dp[1][x - 1][y - 1] + dp[2][x - 1][y - 1];
					}
				}
				else {
					if(house[x][y] != 1) {
						dp[0][x][y] = dp[0][x][y - 1] + dp[2][x][y - 1];
						dp[1][x][y] = dp[1][x - 1][y] + dp[2][x - 1][y];
					}
					if(house[x][y] != 1 && house[x - 1][y] != 1 && house[x][y - 1] != 1) {
						dp[2][x][y] = dp[0][x - 1][y - 1] + dp[1][x - 1][y - 1] + dp[2][x - 1][y - 1];
					}
				}
			}
		}
		
		System.out.println(dp[0][N][N] + dp[1][N][N] + dp[2][N][N]);
	}
}
