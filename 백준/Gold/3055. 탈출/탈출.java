import java.io.*;
import java.util.*;

//백준 3055 탈출
/*
 * . : 빈 칸
 * * : 물
 * X : 돌
 * D : 굴
 * S : 고슴도치
 * 
 * 1. 각 칸에 물이 언제 차오르는지 계산할  BFS
 * 2. 고슴도치 이동시키는 BFS(이 때, 날짜마다 물이 차는지 안차는지 확인하면서 이동)
 * 
 * */
public class Main {
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int R, C, res;
	static char map[][];
	static boolean visited[][];
	static int water[][], path[][];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	static Queue<Point> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		
		map = new char[R][C];
		visited = new boolean[R][C];
		water = new int[R][C];
		path = new int[R][C];
		int x = 0, y = 0;
		q = new ArrayDeque<>();
		
		//물과 맞닿아 있지 않은 곳은 언제든 이동할 수 있도록 
		//water[nx][ny] <= path[cur.x][cur.y] + 1이 조건에 항상 true이게 MAX로 초기화
		for(int i = 0;i < R;i++) Arrays.fill(water[i], Integer.MAX_VALUE);
		
		for(int i = 0;i < R;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0;j < C;j++) {
				if(map[i][j] == 'S') {
					x = i;
					y = j;
				}
				if(map[i][j] == '*') {
					water[i][j] = 0;
					visited[i][j] = true;
					q.add(new Point(i, j));
				}
			}
		}
		
		fillWater();
		
		visited = new boolean[R][C];
		if(!findPath(x, y)) {
			System.out.println("KAKTUS");
		}
	}
	
	//고슴도치가 굴로 가는 최단 경로를 찾는 BFS
	private static boolean findPath(int x, int y) {
		visited[x][y] = true;
		q.add(new Point(x, y));
		
		while(!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny] || map[nx][ny] == 'X'
						|| water[nx][ny] <= path[cur.x][cur.y] + 1)
					continue;
				
				visited[nx][ny] = true;
				path[nx][ny] = path[cur.x][cur.y] + 1;
				if(map[nx][ny] == 'D') {
					System.out.println(path[nx][ny]);
					return true;
				}

				q.add(new Point(nx, ny));
			}
		}
		
		return false;
	}

	// 각 칸에 물이 며칠에 차는지 구하는 BFS
	private static void fillWater() {
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int i = 0;i < 4;i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]
						|| map[nx][ny] == 'X' || map[nx][ny] == 'D') continue;
				
				visited[nx][ny] = true;
				water[nx][ny] = water[cur.x][cur.y] + 1;
				q.add(new Point(nx, ny));
			}
		}
	}
}