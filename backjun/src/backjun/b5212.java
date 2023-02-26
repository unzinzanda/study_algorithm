package backjun;

import java.io.*;
import java.util.*;

// 백준 5212 지구 온난화
public class b5212 {
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
		int R = Integer.parseInt(str[0]);
		int C = Integer.parseInt(str[1]);
		
		String map[][] = new String[R][C];
		List<Point> list = new ArrayList<>();
		List<Point> removePoint = new ArrayList<>();
		
		int dx[] = {0, 0, 1, -1};
		int dy[] = {1, -1, 0, 0};
		
		for(int i = 0;i < R;i++) {
			map[i] = br.readLine().split("");
			for(int j = 0;j < C;j++) {
				if(map[i][j].equals("X")) list.add(new Point(i, j));
			}
		}
		
		for(Point a : list) {
			int cnt = 0;
			for(int i = 0;i < 4;i++) {
				int nx = a.x + dx[i];
				int ny = a.y + dy[i];
				if(nx >= 0 && nx < R && ny >= 0 && ny < C) {
					if(map[nx][ny].equals(".")) cnt++;
				}
				else cnt++;
			}
			
			if(cnt >= 3) removePoint.add(a);
		}
		for(Point a : removePoint) {
			map[a.x][a.y] = ".";
		}
		
		int minX = R, minY = C, maxX = 0, maxY = 0;
		for(int i = 0;i < R;i++) {
			for(int j = 0;j < C;j++) {
				if(map[i][j].equals("X")) {
					minX = Math.min(minX, i);
					minY = Math.min(minY, j);
					maxX = Math.max(maxX, i);
					maxY = Math.max(maxY, j);
				}
			}
		}
		
		
		for(int i = minX;i <= maxX;i++) {
			for(int j = minY;j <= maxY;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
