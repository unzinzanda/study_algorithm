package baekjoon;

import java.io.*;

// 백준 2931 가스관
public class GasPipe {
	static int R, C;
	static String map[][];
	static Point posM, posZ, start, hole;
	static String pipe[] = {"|", "-", "+", "1", "2", "3", "4"};
	static boolean flag = false;
	static int dx[] = {0, 0, 1, -1};
	static int dy[] = {1, -1, 0, 0};
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
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		
		for(int i = 0; i < R;i++) {
			map[i] = br.readLine().split(" ");
			for(int j = 0;j < C;j++) {
				if(map[i][j].equals("M")) posM = new Point(i, j);
				else if(map[i][j].equals("Z")) posZ = new Point(i, j);
			}
		}
		
		// M에서 관이 있는 위치 찾기
		for(int i = 0;i < 4;i++) {
			int nx = posM.x + dx[i];
			int ny = posM.y + dy[i];
			if(nx >= 0 && nx < R && ny >= 0 && ny < C) {
				if("|-+1234".contains(map[nx][ny])) start = new Point(nx, ny);
			}
		}
		
		visited = new boolean[R][C];
		visited[posM.x][posM.y] = true;
		visited[start.x][start.y] = true;
		findEmpty(start.x, start.y);
	}

	private static void findEmpty(int x, int y) {
		
	}
}
