package backjun;

import java.io.*;
import java.util.*;

// 백준 21609 상어 중학교
public class SharkMiddleSchool {
	static int N, M;
	static int map[][];
	static boolean visited[][];
	static int dx[] = {0, 0, 1, -1};
	static int dy[] = {1, -1, 0, 0};
	static Queue<Point> q = new ArrayDeque<>();
	static List<Block> blockGroup = new ArrayList<>();	// 지울 수 있는 블록 그룹을 담은 리스트
	static List<Point> rainbow = new ArrayList<>();		// 무지개 블록의 위치를 담은 리스트
	static int score;
	
	static class Point {
		int x;
		int y;
		int color;
		
		public Point(int x, int y, int color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}
	
	static class Block implements Comparable<Block>{
		int x;	// 기준 블록의 X 좌표
		int y;	// 기준 블록의 Y 좌표
		int size;	// 블록 그룹의 사이즈
		int rainbowBlock;	// 그룹 내 무지개 블록의 개수
		Point startPoint;
		
		public Block(int x, int y, int size, int rainbowBlock, Point startPoint) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.rainbowBlock = rainbowBlock;
			this.startPoint = startPoint;
		}

		// size -> rainbowBlock -> x -> y 순으로 내림차순
		@Override
		public int compareTo(Block o) {
			if(o.size == this.size) {
				if(o.rainbowBlock == this.rainbowBlock) {
					if(o.x == this.x) {
						return o.y - this.y;
					}
					return o.x - this.x;
				}
				return o.rainbowBlock - this.rainbowBlock;
			}
			return o.size - this.size;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		map = new int[N][N];
		
		for(int i = 0; i < N;i++) {
			str = br.readLine().split(" ");
			for(int j = 0;j < str.length;j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if(map[i][j] == 0) rainbow.add(new Point(i, j, 0));
			}
		}
		
		while(true) {
			visited = new boolean[N][N];
			for(int i = 0;i < N;i++) {
				for(int j = 0;j < N;j++) {
					// 탐색의 시작은 일반 블록
					// 0 : 무지개 블록, -1 : 검은 블록, -2 : 빈 공간
					if(!visited[i][j] && map[i][j] != 0 && map[i][j] != -1 && map[i][j] != -2) {
						visited[i][j] = true;
						int cnt = 1;
						int minX = i;
						int minY = j;
						int rbCnt = 0;
						q.add(new Point(i, j, map[i][j]));
						
						while(!q.isEmpty()) {
							Point temp = q.poll();
							for(int k = 0;k < 4;k++) {
								int nx = temp.x + dx[k];
								int ny = temp.y + dy[k];
								if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
									// 같은 색이거나 무지개 블록이며 방문한 적이 없다면 같은 그룹
									if(!visited[nx][ny] && (map[nx][ny] == temp.color || map[nx][ny] == 0)) {
										if (map[nx][ny] == 0) rbCnt++;
										cnt++;
										visited[nx][ny] = true;
										// 무지개 블록이 아닐 때만 기준 블록인지 확인
										if(map[nx][ny] != 0) {
											if(minX > nx) {
												minX = nx;
												minY = ny;
											}
											else if(minX == nx) {
												minY = Math.min(minY, ny);
											}
										}
										q.add(new Point(nx, ny, temp.color));
									}
								}
							}
						}
						
						// 사이즈가 2 이상인 것만 후보 블록 그룹에 담기
						if(cnt >= 2) {
							blockGroup.add(new Block(minX, minY, cnt, rbCnt, new Point(i, j, map[i][j])));
						}
						// 무지개 블럭만 다시 false로 처리(다른 블록 그룹도 사용할 수 있도록)
						for(Point r : rainbow) visited[r.x][r.y] = false;
					}
				}
			}
			// 유효한 블럭 그룹이 없다면 종료
			if(blockGroup.isEmpty()) break;
			
			// 가장 큰 그룹 제거
			Collections.sort(blockGroup);
			
			score += (blockGroup.get(0).size * blockGroup.get(0).size);
			removeBlock(blockGroup.get(0).startPoint);
			
			
			gravity();
			Rotation();
			gravity();
			blockGroup.clear();
			
			// 2번의 중력 작용과 회전으로 인해 무지개 블럭의 위치도 바뀌었을 것
			// 따라서 새로 무지개 블록의 위치 업데이트
			rainbow.clear();
			for(int i = 0;i < N;i++) {
				for(int j = 0;j < N;j++) {
					if(map[i][j] == 0) rainbow.add(new Point(i, j, 0));
				}
			}
		}
		
		System.out.println(score);
	}

	private static void Rotation() {
		int copy[][] = new int[N][N];
		
		for(int i = 0;i < N;i++) {
			for(int j = 0;j < N;j++) {
				// 외워둔 90도 회전 희희
				copy[N - 1 - j][i] = map[i][j];
			}
		}
		
		for(int i = 0;i < N;i++) {
			map[i] = Arrays.copyOf(copy[i], N);
		}
	}

	private static void gravity() {
		// 밑에서부터 block 내리기
		// 이 때 N - 1행을 볼 필요 X (어차피 맨 밑이니까)
		for(int i = N - 2; i >= 0;i--) {
			for(int j = 0;j < N;j++) {
				if(map[i][j] != -1 && map[i][j] != -2) {
					int tempX = i;
					while(true) {
						if(tempX + 1 >= N || map[tempX + 1][j] != -2) {
							map[tempX][j] = map[i][j];
							// 제자리일 때는 map[i][j]를 빈공간으로 만들어주면 안됨
							if(tempX != i) map[i][j] = -2;
							break;
						}
						tempX += 1;
					}
				}
			}
		}
	}

	// 다시 bfs를 돌며 map 업데이트
	private static void removeBlock(Point startPoint) {
		visited = new boolean[N][N];
		q.add(startPoint);
		visited[startPoint.x][startPoint.y] = true;
		map[startPoint.x][startPoint.y] = -2;
		
		while(!q.isEmpty()) {
			Point temp = q.poll();
			for(int i = 0;i < 4;i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if(!visited[nx][ny] && (map[nx][ny] == temp.color || map[nx][ny] == 0)) {
						visited[nx][ny] = true;
						map[nx][ny] = -2;
						q.add(new Point(nx, ny, temp.color));
					}
				}
			}
		}
		
	}
}
