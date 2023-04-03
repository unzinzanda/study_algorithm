package bruteforce.b14502;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair {
	public int x;
	public int y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static int N;
	public static int M;
	public static int map[][];
	public static int dx[] = {0, 0, 1, -1};
	public static int dy[] = {1, -1, 0, 0};
	public static int max = 0;
	
	public static void dfs(int cnt) {
		if(cnt == 3) {
			bfs();
			return;
		}
		else {
			for(int i = 0; i < N;i++) {
				for(int j = 0; j < M;j++) {
					if(map[i][j] == 0) {
						map[i][j] = 1;
						dfs(cnt + 1);
						map[i][j] = 0;
					}
				}
			}
		}
	}
	
	public static void bfs() {
		int virus[][] = new int[N][M];
		
		for(int i = 0; i < N;i++) {
			for(int j = 0;j < M;j++) {
				virus[i][j] = map[i][j];
			}
		}
		
		Queue<Pair> q = new LinkedList<>();
		for(int i = 0; i < N;i++) {
			for(int j = 0;j < M;j++) {
				if(virus[i][j] == 2) q.add(new Pair(i, j));
			}
		}
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			for(int i = 0; i < 4;i++) {
				int nx = dx[i] + p.x;
				int ny = dy[i] + p.y;
				if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if(virus[nx][ny] == 0) {
						virus[nx][ny] = 2;
						q.add(new Pair(nx, ny));
					}
				}
			}
		}
		
		int res = 0;
		for(int i = 0; i < N;i++) {
			for(int j = 0; j < M;j++) {
				if(virus[i][j] == 0) res++;
			}
		}
		if(max < res) max = res;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		
		// 지도 입력
		for(int i = 0;i < N;i++) {
			for(int j = 0; j < M;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < N;i++) {
			for(int j = 0; j < M;j++) {
				if(map[i][j] == 0) {
					// 벽 세우기
					map[i][j] = 1;
					dfs(1);
					// bfs를 돌고 다른 벽을 세우기 위해 되돌리기
					map[i][j] = 0;
				}
			}
		}
		
		System.out.println(max);
		
		sc.close();
	}
}
