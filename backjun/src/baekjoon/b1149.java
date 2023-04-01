package baekjoon;

import java.io.*;

// 백준 1149 RGB거리
public class b1149 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		//0 : ����, 1 : �ʷ�, 2 : �Ķ�
		int cost[][] = new int[N][3];
		int result[][] = new int[N][3];
		
		String str[];
		for(int i = 0;i < N;i++) {
			str = br.readLine().split(" ");
			for(int j = 0;j < str.length;j++) cost[i][j] = Integer.parseInt(str[j]);
		}
		
		result[0][0] = cost[0][0];
		result[0][1] = cost[0][1];
		result[0][2] = cost[0][2];
		
		for(int i = 1;i < N;i++) {
			result[i][0] = Math.min(result[i - 1][1] + cost[i][0], result[i - 1][2] + cost[i][0]);
			result[i][1] = Math.min(result[i - 1][0] + cost[i][1], result[i - 1][2] + cost[i][1]);
			result[i][2] = Math.min(result[i - 1][1] + cost[i][2], result[i - 1][0] + cost[i][2]);
		}
		
		System.out.println(Math.min(result[N - 1][0], Math.min(result[N - 1][1], result[N - 1][2])));
	}
}
