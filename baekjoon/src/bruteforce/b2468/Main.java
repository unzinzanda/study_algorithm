package bruteforce.b2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 2468 안전 영역
public class Main {
	static int N;
	static int map[][];
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int maxHeight = 0;
	static int max = 0;
	static int res = 0;
	static int copy[][];
	static boolean visited[][];
	
	public static void safeArea(int x, int y) {
		if(!visited[x][y] && copy[x][y] > 0) {
			visited[x][y] = true;
			for(int i = 0; i < 4 ; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
					safeArea(nx, ny);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		copy = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N;i++) {
			String str[] = br.readLine().split(" ");
			for(int j = 0; j < str.length;j++) {
				map[i][j] = Integer.parseInt(str[j]);
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}
		
		for(int i = 0; i < maxHeight;i++) {
			res = 0;
			for(int a = 0; a< N;a++) {
				Arrays.fill(visited[a], false);
			}
			for(int n = 0;n < N;n++) {
				for(int m = 0; m < N;m++) {
					copy[n][m] = map[n][m] - i;
				}
			}
			
			for(int n = 0;n < N;n++) {
				for(int m = 0; m < N;m++) {
					if(copy[n][m] > 0 && !visited[n][m]) {
						safeArea(n, m);
						res++;
					}
				}
			}
			max = Math.max(max, res);
		}
		System.out.println(max);
	}
}
