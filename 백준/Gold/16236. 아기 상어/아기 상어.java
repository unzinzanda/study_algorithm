import java.io.*;
import java.util.*;

// 백준 16236 아기 상어
public class Main {
	static class Point implements Comparable<Point>{
		public int x;
		public int y;
		public int cnt;
		
		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		// y로 오름차순, y가 같다면 x로 오름차순 정렬
		@Override
		public int compareTo(Point o) {
			if(o.cnt == this.cnt) {
				if(o.x == this.x) {
					return Integer.compare(this.y, o.y);
				}
				return Integer.compare(this.x, o.x);
			}
			return Integer.compare(this.cnt, o.cnt);
		}
	}
	
	static int N;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[][] map;
	static boolean[][] visited;
	static Point shark;
	static int size = 2;				// 아기 상어  크기
	static int eat = 0;					// 먹은 물고기 수
	static int time = 0;				// 먹은 시간
	static List<Point> fishList = new ArrayList<>();
	static Queue<Point> q = new LinkedList<>();
	
	public static void bfs() {
		q.add(shark);
		visited[shark.x][shark.y] = true;
		while(!q.isEmpty()) {
			Point temp = q.poll();
			for(int i = 0; i < 4;i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
					if(!visited[nx][ny]) {
						visited[nx][ny] = true;
						if(map[nx][ny] == 9) continue;
						if(map[nx][ny] == 0 || map[nx][ny] == size) q.add(new Point(nx, ny, temp.cnt + 1));
						else if(map[nx][ny] < size) {
							visited[nx][ny] = true;
							fishList.add(new Point(nx, ny, temp.cnt + 1));
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i = 0;i < N;i++) {
			String str[] = br.readLine().split(" ");
			for(int j = 0;j < str.length;j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if(map[i][j] == 9) shark = new Point(i, j, 0);
			}
		}
		
		while(true) {
			bfs();
			if(fishList.isEmpty()) break;
			else {
				// 정렬, 먹은거에 대한 처리들
				Collections.sort(fishList);
				Point pick = fishList.get(0);
				eat += 1;
				map[shark.x][shark.y] = 0;
				shark.x = pick.x;
				shark.y = pick.y;
				map[shark.x][shark.y] = 9;
				time += pick.cnt;
				
				if(eat >= size) {
					eat -= size;
					size += 1;
				}
				
				fishList.clear();
				q.clear();
				for(int i = 0; i < N;i++) Arrays.fill(visited[i], false);
			}
		}
		
		System.out.println(time);
	}
}
