package backjun.b16236;

import java.io.*;
import java.util.*;

// 백준 16236 아기 상어
public class Main {
	// 물고기의 x, y 좌표와 상어와의 거리를 저장
	static class Point implements Comparable<Point>{
		public int x;
		public int y;
		public int cnt;
		
		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		// cnt로 오름차순, cnt가 같다면 x로 오름차순, x가 같다면 y로 오름차순
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
	static List<Point> fishList = new ArrayList<>();	// 먹을 수 있는 물고기를 담는 리스트
	static Queue<Point> q = new LinkedList<>();			// bfs를 위한 큐
	
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
						// 칸이 0이거나 상어와 크기가 같으면 지나갈 수 있음
						if(map[nx][ny] == 0 || map[nx][ny] == size) q.add(new Point(nx, ny, temp.cnt + 1));
						// 먹을 수 있는 물고기 (더이상 이동 X)
						else if(map[nx][ny] < size) {
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
			// bfs를 돌며 먹을 수 있는 물고기를 list에 담음
			// 만약 list가 비어있다면 먹을 수 있는게 없는 것 -> break;
			if(fishList.isEmpty()) break;
			else {
				// 정렬, 먹은거에 대한 처리들
				Collections.sort(fishList);
				Point pick = fishList.get(0);
				eat += 1;
				// 기존의 상어 위치는 0으로
				map[shark.x][shark.y] = 0;
				shark.x = pick.x;
				shark.y = pick.y;
				// 새로 이동한 위치는 상어임을 표시하기 위해 9로 (굳이 필요없을지도)
				map[shark.x][shark.y] = 9;
				time += pick.cnt;
				
				if(eat == size) {
					eat -= size;
					size += 1;
				}
				
				// 다시 먹을 수 있는 물고기를 찾아야하므로 리스트 비우기
				fishList.clear();
				q.clear();
				for(int i = 0; i < N;i++) Arrays.fill(visited[i], false);
			}
		}
		
		System.out.println(time);
	}
}
