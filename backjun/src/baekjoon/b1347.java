package baekjoon;

import java.io.*;

// 백준 1347 미로 만들기
public class b1347 {
	static int N;
	static String route[];
	static int dx[] = {1, 0, -1, 0};
	static int dy[] = {0, -1, 0, 1};
	static int dir = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		route = br.readLine().split("");
		
		int map[][] = new int[101][101];
		
		int x = 50, y = 50;
		int minX = 50, minY = 50;
		int maxX = 50, maxY = 50;
		map[x][y] = 1;
		for(int i = 0; i < route.length;i++) {
			if(route[i].equals("F")) {
				x += dx[dir];
				y += dy[dir];
				map[x][y] = 1;
				minX = Math.min(minX, x);
				minY = Math.min(minY, y);
				maxX = Math.max(maxX, x);
				maxY = Math.max(maxY, y);
			}
			else if(route[i].equals("L")) {
				dir -= 1;
				if(dir < 0) dir += 4;
				dir %= 4;
			}
			else if(route[i].equals("R")) {
				dir += 1;
				dir %= 4;
			}
		}
		
		for(int i = minX;i <= maxX;i++) {
			for(int j = minY; j <= maxY;j++) {
				if(map[i][j] == 1) System.out.print(".");
				else System.out.print("#");
			}
			System.out.println();
		}
	}
}
