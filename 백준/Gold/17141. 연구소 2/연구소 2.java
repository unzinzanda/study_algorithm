import java.io.*;
import java.util.*;

// 백준 17141 연구소2
// visited 를 int 배열로 하여 현재 초가 기록된 초보다 크다면 탐색을 종료
// 바이러스를 둘 수 있는 위치를 저장해두고 조합으로 뽑기
public class Main {
	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M;
	static ArrayList<Point> virusPlace = new ArrayList<Point>();
	static boolean isSelected[];
	static int board[][];
	static int visited[][];
	static int dx[] = {0, 0, -1, 1}, dy[] = {1, -1, 0, 0};
	static Queue<Point> q = new ArrayDeque<Point>();
	static int result = Integer.MAX_VALUE;
	static int blank = 0;
	
	static void comb(int r, int idx) {
		if(r == M) {
			for(int i = 0;i < N;i++) Arrays.fill(visited[i], Integer.MAX_VALUE);
			int tempBlank = blank;
			for(int i = 0;i < isSelected.length;i++) {
				if(isSelected[i]) {
					q.add(virusPlace.get(i));
					visited[virusPlace.get(i).x][virusPlace.get(i).y] = 0;
				}
			}
			int curMax = 0;
			while(!q.isEmpty()) {
				Point temp = q.poll();
					for(int i = 0;i < 4;i++) {
						int nx = temp.x + dx[i];
						int ny = temp.y + dy[i];
						
						if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[temp.x][temp.y] + 1 > visited[nx][ny] || board[nx][ny] == 1 ) continue;
						
						if(visited[nx][ny] == Integer.MAX_VALUE) {
							tempBlank--;
							visited[nx][ny] = visited[temp.x][temp.y] + 1;
							q.add(new Point(nx, ny));
							curMax = Math.max(curMax, visited[nx][ny]);
						}
					}
			}
			
			if(tempBlank == 0) result = Math.min(curMax, result);
		}
		
		for(int i = idx; i < virusPlace.size();i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				comb(r + 1, i + 1);
				isSelected[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		board = new int[N][N];
		visited = new int[N][N];
		
		for(int i = 0;i < N;i++) {
			str = br.readLine().split(" ");
			for(int j = 0;j < N;j++) {
				board[i][j] = Integer.parseInt(str[j]);
				if(board[i][j] == 2) {
					virusPlace.add(new Point(i, j));
				}
				if(board[i][j] == 0 || board[i][j] == 2) blank++;
			}
		}
		blank -= M;
		isSelected = new boolean[virusPlace.size()];
		comb(0, 0);
		
		if(result == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
	}
}