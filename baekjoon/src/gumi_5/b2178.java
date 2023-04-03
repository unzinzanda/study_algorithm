package gumi_5;

import java.io.*;
import java.util.*;

// 백준 2178 미로 탐색
public class b2178 {
	
	static int map[][];
	static boolean visited[][];
	static int N;
	static int M;
	static int dx[] = {1, 0, -1, 0};
	static int dy[] = {0, 1, 0, -1};
	static int min = Integer.MAX_VALUE;
	
	static class Point {
		public int x;
		public int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0;i < N;i++) {
			str = br.readLine().split("");
			for(int j = 0;j < str.length;j++) map[i][j] = Integer.parseInt(str[j]);
		}
		
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		visited[0][0] = true;
		while(!q.isEmpty()) {
			Point tmp = q.poll();
			for(int i = 0; i < 4;i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if(!visited[nx][ny] && map[nx][ny] > 0) {
						visited[nx][ny] = true;
						q.add(new Point(nx, ny));
						map[nx][ny] = map[tmp.x][tmp.y] + 1;
					}
				}
 			}
		}
		
		System.out.println(map[N - 1][M - 1]);
	}

}
