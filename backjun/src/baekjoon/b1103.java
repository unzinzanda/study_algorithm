package baekjoon;

import java.io.*;
import java.util.Arrays;

//백준 1103 게임
/*
* visited에 dp처리를 함께, 리턴할 때 현재 자리까지 횟수를 업데이트
* */
public class b1103 {
	static int N, M;
	static int board[][];
	static int visited[][];
	static boolean cycle;
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	
	public static int dp(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < M) {
			if(visited[x][y] == -1) {
				int cal = 0;	// 사방 탐색을 하고 사방 중 최대로 많이 이동한 횟수
				if(board[x][y] == -1) {
					return 0;
				}
				else {
					visited[x][y] = 0;	// 일단 방문 처리
					
					for(int i = 0;i < 4;i++) {
						int nx = x + dx[i] * board[x][y];
						int ny = y + dy[i] * board[x][y];
						
						cal = Math.max(cal, dp(nx, ny) + 1);
					}
					return visited[x][y] = cal;
				}
			} else if(visited[x][y] == 0) {
				// visited == 0인 경우만 사이클이라고 여기는 이유
				// visited == 0인 자리들 : 현재 탐색 중인 경로
				// visited != 0인 자리들 : 탐색이 종료, 사방 중 더 이상 진행할 수 있는 부분 X
				// a -> b로 갔는데 b가 0이 아닌 다른 수인 경우, b -> a로 올 수 있다고 확신할 수 없음.
				// 그게 가능했다면 a -> b로 갔을 때, b == 0 이었을 것.
				cycle = true;
			} else {
				return visited[x][y];
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		board = new int[N][M];
		visited = new int[N][M];
		for(int i = 0;i < N;i++) Arrays.fill(visited[i], -1);
		
		// H : -1로 표시
		for(int i = 0;i < N;i++) {
			str = br.readLine().split("");
			for(int j = 0;j < str.length;j++) {
				if(str[j].equals("H")) board[i][j] = -1;
				else board[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		dp(0, 0);
		
		if(cycle) System.out.println(-1);
		else System.out.println(visited[0][0]);
	}
}
