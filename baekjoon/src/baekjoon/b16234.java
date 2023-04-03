package baekjoon;

import java.io.*;
import java.util.*;

// 백준 16234 인구 이동
public class b16234 {
	static int N;
	static int L;
	static int R;
	static int country[][];
	static boolean visited[][];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	static int day;
	static int sum;
	static int div;
	static Queue<Point> q;
	static List<Point> list;
	static boolean flag;
	
	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		L = Integer.parseInt(str[1]);
		R = Integer.parseInt(str[2]);
		
		country = new int[N][N];
		
		for(int i = 0;i < N;i++) {
			str = br.readLine().split(" ");
			for(int j = 0;j < str.length;j++) country[i][j] = Integer.parseInt(str[j]);
		}
		
		
		while(true) {
			visited = new boolean[N][N];
			flag = true;
			for(int i = 0;i < N;i++) {
				for(int j = 0;j < N;j++) {
					if(!visited[i][j]) {
						visited[i][j] = true;
						sum = country[i][j];
						div = 1;
						list = new ArrayList<>();
						q = new LinkedList<>();
						q.add(new Point(i, j));
						list.add(new Point(i, j));
						search();
						
						int temp = sum / div;
						
						for(Point p : list) country[p.x][p.y] = temp;
					}
				}
			}
			if(flag) break;
			else day++;
		}
		
		System.out.println(day);
	}
	
	private static void search() {
		while(!q.isEmpty()) {
			Point temp = q.poll();
			for(int i = 0;i < 4;i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
					int diff = Math.abs(country[temp.x][temp.y] - country[nx][ny]);
					if(diff >= L && diff <= R && !visited[nx][ny]) {
						flag = false;
						visited[nx][ny] = true;
						sum += country[nx][ny];
						div++;
						list.add(new Point(nx, ny));
						q.add(new Point(nx, ny));
					}
				}
			}
		}
	}
}
