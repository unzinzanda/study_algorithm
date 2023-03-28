package backjun;

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
				int cal = 0;	// ��� Ž���� �ϰ� ��� �� �ִ�� ���� �̵��� Ƚ��
				if(board[x][y] == -1) {
					return 0;
				}
				else {
					visited[x][y] = 0;	// �ϴ� �湮 ó��
					
					for(int i = 0;i < 4;i++) {
						int nx = x + dx[i] * board[x][y];
						int ny = y + dy[i] * board[x][y];
						
						cal = Math.max(cal, dp(nx, ny) + 1);
					}
					return visited[x][y] = cal;
				}
			} else if(visited[x][y] == 0) {
				// visited == 0�� ��츸 ����Ŭ�̶�� ����� ����
				// visited == 0�� �ڸ��� : ���� Ž�� ���� ���
				// visited != 0�� �ڸ��� : Ž���� ����, ��� �� �� �̻� ������ �� �ִ� �κ� X
				// a -> b�� ���µ� b�� 0�� �ƴ� �ٸ� ���� ���, b -> a�� �� �� �ִٰ� Ȯ���� �� ����.
				// �װ� �����ߴٸ� a -> b�� ���� ��, b == 0 �̾��� ��.
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
		
		// H : -1�� ǥ��
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
