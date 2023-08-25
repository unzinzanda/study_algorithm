import java.io.*;
import java.util.*;

// 백준 23352 방탈출

/*
 0인 방은 지나갈 수 없음
 최단 경로 => BFS
 최단 경로 중 가장 긴 경로의 방 번호의 합 -> 가장 긴 경로 여러 개면 합의 최댓값
 비밀번호 없다면 0
 
 풀이
 1. 이중 for문을 돌면서 현재 위치에서 갈 수 있는 꼭짓점으 모두 들른다.
 2. 방문하면서 현재까지 탐색했던 지점 중 가장 긴 경로를 저장한다.
 3. 가장 긴 경로가 업데이트 될 때, 탐색을 시작한 칸과 현재 칸의 합을 result에 저장한다.
    만약, 가장 긴 경로와 같은 길이라면 max값을 저장한다.
*/
public class Main {
	static int N, M;
	static int board[][];
	static boolean visited[][];
	static int dx[] = {0, 0, 1, -1};
	static int dy[] = {1, -1, 0, 0};
	static int maxDepth = 0;
	static int result = 0;
	
	static class Point {
		int x;
		int y;
		int depth;
		public Point(int x, int y, int depth) {
			super();
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str[] = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		board = new int[N][M];
		
		for(int i = 0;i < N;i++) {
			str = br.readLine().split(" ");
			for(int j = 0;j < str.length;j++) board[i][j] = Integer.parseInt(str[j]);
		}
		
		for(int i = 0;i < N;i++) {
			for(int j = 0;j < M;j++) {
				if(board[i][j] != 0) {
					visited = new boolean[N][M];
					visited[i][j] = true;
					
					Queue<Point> q = new LinkedList<>();
					q.add(new Point(i, j, 0));
					
					while(!q.isEmpty()) {
						Point temp = q.poll();
						
						for(int k = 0; k < 4;k++) {
							int nx = temp.x + dx[k];
							int ny = temp.y + dy[k];
							
							if(nx < 0 || ny < 0 || nx >= N || ny >= M || board[nx][ny] == 0) continue;
							
							if(!visited[nx][ny]) {
								visited[nx][ny] = true;
								if(maxDepth <= temp.depth + 1) {
									if(maxDepth == temp.depth + 1) result = Math.max(result, board[i][j] + board[nx][ny]);
									else result = board[i][j] + board[nx][ny];
									maxDepth = temp.depth + 1;
									
								}
								
								q.add(new Point(nx, ny, temp.depth + 1));
							}
						}
					}
				}
			}
		}
		
		System.out.println(result);
	}
}