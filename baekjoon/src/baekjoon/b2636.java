package baekjoon;

import java.io.*;
import java.util.*;

//백준 2636 치즈
/*
 * 구멍을 그냥 치즈라고 여기고 0과 맞닿은 부분이 녹음.
 * */
public class b2636 {
	static int N, M;
	static int cheeze[][];
	static int res, count, time;
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	static boolean visited[][];
	
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		cheeze = new int[N][M];
		
		// 치즈 : 1, 빈공간 : 0
		for(int i = 0;i < N;i++) {
			str = br.readLine().split(" ");
			for(int j = 0;j < M;j++) {
				cheeze[i][j] = Integer.parseInt(str[j]);
				
				if(cheeze[i][j] == 1) count++;
			}
		}
		
		while(count != 0) {
			res = 0;
			time++;
			Queue<Point> q = new ArrayDeque<>();
			visited = new boolean[N][M];
			
			q.add(new Point(0, 0));
			visited[0][0] = true;
			
			
			while(!q.isEmpty()) {
				Point temp = q.poll();
				
				for(int i = 0;i < 4;i++) {
					int nx = temp.x + dx[i];
					int ny = temp.y + dy[i];
					if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
					
					visited[nx][ny] = true;
					if(cheeze[nx][ny] == 1) {
						res++;
						cheeze[nx][ny] = 0;
					} else {
						q.add(new Point(nx, ny));
					}
				}
			}
			
			count -= res;
		}
		
		System.out.println(time + "\n" + res);
	}
}
