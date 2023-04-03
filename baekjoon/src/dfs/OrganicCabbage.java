package dfs;

import java.io.*;

// 백준 1012 유기농 배추
public class OrganicCabbage {
	static int T;
	static int M;
	static int N;
	static int K;
	static int map[][];
	static boolean visited[][];
	static int cnt;
	static int dx[] = {0, 0, 1, -1};
	static int dy[] = {1, -1, 0, 0};
	
	public static void dfs(int x, int y) {
		for(int i = 0;i < 4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >= 0 && ny >= 0 && nx < M && ny < N) {
				if(!visited[nx][ny] && map[nx][ny] == 1) {
					visited[nx][ny] = true;
					dfs(nx, ny);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int t = 0;t < T;t++) {
			String str[] = br.readLine().split(" ");
			M =Integer.parseInt(str[0]);
			N =Integer.parseInt(str[1]);
			K =Integer.parseInt(str[2]);
			
			map = new int[M][N];
			visited = new boolean[M][N];
			
			for(int i = 0; i < K;i++) {
				str = br.readLine().split(" ");
				map[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = 1;
			}
			
			cnt = 0;
			for(int i = 0;i < M;i++) {
				for(int j = 0;j < N;j++) {
					if(!visited[i][j] && map[i][j] == 1) {
						visited[i][j] = true;
						dfs(i, j);
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
		}
	}
}
