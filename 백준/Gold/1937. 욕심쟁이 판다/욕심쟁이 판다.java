import java.io.*;

//백준 1937 욕심쟁이 판다
/*
 * dfs를 돌다가 더이상 갈 수 없음 -> return 0
 * 리턴 받은 값 + 1 이 dp[nx][ny]의 값
 * */
public class Main {
	static int N;
	static int map[][], dp[][];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	static int res;
	
	private static int dfs(int x, int y) {
		if(dp[x][y] == 0) {
			int cal = 1;
			for(int i = 0; i < 4;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[x][y] >= map[nx][ny]) continue;
				
				int cnt = dfs(nx, ny) + 1;
				cal = Math.max(cal, cnt);
				res = Math.max(res, cal);
			}
			
			return dp[x][y] = Math.max(dp[x][y], cal);
		} else return dp[x][y];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		
		String str[];
		for(int i = 0;i < N;i++) {
			str = br.readLine().split(" ");
			for(int j = 0;j < str.length;j++) map[i][j] = Integer.parseInt(str[j]);
		}
		
		for(int i = 0;i < N;i++) {
			for(int j = 0; j < N;j++) {
				res = Math.max(res, dfs(i, j));
			}
		}
		
		System.out.println(res);
	}
}