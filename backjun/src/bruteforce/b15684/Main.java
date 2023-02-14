package bruteforce.b15684;

import java.io.*;

// 백준 15684 사다리 조작
public class Main {
	static int N;
	static int M;
	static int H;
	static int ladder[][];
	
	private static void dfs( int cnt, int r) {
		if(cnt == r) {
			if(checkLadder()) {
				System.out.println(r);
				System.exit(0);
			}
			return;
		}
		for(int i = 1;i <= H;i++) {
			for(int j = 1;j < N;j++) {
				if(ladder[i][j] == 0 && ladder[i][j + 1] == 0) { 
					ladder[i][j] = 1;
					ladder[i][j + 1] = 2;
					dfs(cnt + 1, r);
					ladder[i][j] = 0;
					ladder[i][j + 1] = 0;
				}
			}
		}
	}
	
	private static boolean checkLadder() {
		for(int i = 1;i <= N;i++) {
			int nx = 1;
			int ny = i;
			
			while(nx <= H) {
				if(ladder[nx][ny] == 1) ny += 1;
				else if(ladder[nx][ny] == 2) ny -=  1;
				nx += 1;
			}
			
			if(ny != i) return false; 
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		H = Integer.parseInt(str[2]);
		
		ladder = new int[H + 1][N + 1];
		
		for(int i = 0;i < M;i++) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			
			// 사다리 왼쪽
			ladder[a][b] = 1;
			// 사다리 오른쪽
			ladder[a][b + 1] = 2;
		}
		
		// 사다리 놓기 전 확인
		if(checkLadder()) {
			System.out.println(0);
			System.exit(0);
		}
		// 사다리 놓기
		for(int i = 1;i <= 3; i++) {
			dfs(0, i);
		}
		
		System.out.println(-1);
	}
}
