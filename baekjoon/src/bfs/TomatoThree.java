package bfs;

import java.io.*;
import java.util.*;

// 백준 7569 토마토(3차원)
public class TomatoThree {
	static int N;
	static int M;
	static int H;
	static int dx[] = {1, -1, 0, 0, 0, 0};
	static int dy[] = {0, 0, 1, -1, 0, 0};
	static int dz[] = {0, 0, 0, 0, 1, -1};
	static int box[][][];
	static boolean visited[][][];
	static Queue<Point> q = new LinkedList<>();
	
	static class Point {
		int x;
		int y;
		int z;
		int day;
		
		public Point(int z, int y, int x, int day) {
			this.z = z;
			this.y = y;
			this.x = x;
			this.day = day;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		H = Integer.parseInt(str[2]);
		
		box = new int[H][M][N];
		visited = new boolean[H][M][N];
		int one = 0;
		int zero = 0;
		
		for(int i = 0;i < H;i++) { // Z
			for(int j = 0;j < N;j++) { // X
				str = br.readLine().split(" ");
				for(int k = 0;k < M;k++) { // Y
					box[i][k][j] = Integer.parseInt(str[k]);
					if(box[i][k][j] == 1) {
						one++;
						q.add(new Point(i, j, k, 0));
					}
					else if(box[i][k][j] == 0) zero++;
				}
			}
		}
		
		if(zero == 0) {
			System.out.println(0);
			System.exit(0);
		}
		if(one == 0) {
			System.out.println(-1);
			System.exit(0);
		}
		
		int day = 0;
		visited[q.peek().z][q.peek().x][q.peek().y] = true;
		while(!q.isEmpty()) {
			Point temp = q.poll();
			day = temp.day;
			for(int i = 0;i < 6;i++) {
				int nz = temp.z + dz[i];
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];
				if(nx >= 0 && ny >= 0 && nz >= 0 && nz < H && ny < N && nx < M) {
					if(!visited[nz][nx][ny] && box[nz][nx][ny] == 0) {
						visited[nz][nx][ny] = true;
						zero--;
						q.add(new Point(nz, ny, nx, temp.day + 1));
					}
				}
			}
		}
		
		if(zero == 0) System.out.println(day);
		else System.out.println(-1);
	}
}
