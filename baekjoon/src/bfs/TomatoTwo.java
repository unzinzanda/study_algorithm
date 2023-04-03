package bfs;

import java.io.*;
import java.util.*;

// 백준 7576 토마토
public class TomatoTwo {
	static int N;
	static int M;
	static int box[][];
	static boolean visited[][];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	static Queue<Point> q = new LinkedList<>();
	
	static class Point {
		int x;
		int y;
		int day;
		
		public Point(int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		box = new int[M][N];
		visited= new boolean[M][N];
		
		int zero = 0;
		int one = 0;
		
		for(int i = 0;i < M;i++) {
			str = br.readLine().split(" ");
			for(int j = 0; j < N;j++) {
				box[i][j] = Integer.parseInt(str[j]);
				if(box[i][j] == 1) {
					one++;
					q.add(new Point(i, j, 0));
				}
				else if(box[i][j] == 0) zero++;
			}
		}
		
		if(zero == 0) {
			System.out.println(0);
			System.exit(0);
		}
		else if(one == 0) {
			System.out.println(-1);
			System.exit(0);
		}
		
		visited[q.peek().x][q.peek().y] = true;
		int day = 0;
		while(!q.isEmpty()) {
			Point temp = q.poll();
			day = temp.day;
			for(int i = 0;i < 4;i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				if(nx >= 0 && ny >= 0 && nx < M && ny < N) {
					if(!visited[nx][ny] && box[nx][ny] == 0) {
						visited[nx][ny] = true;
						box[nx][ny] = 1;
						q.add(new Point(nx, ny, temp.day + 1));
					}
				}
			}
		}
		
		for(int i = 0;i < M;i++) {
			for(int j = 0;j < N;j++) {
				if(box[i][j] == 0) {
					System.out.println(-1);
					System.exit(0);
				}
			}
		}
		System.out.println(day);
	}
}

