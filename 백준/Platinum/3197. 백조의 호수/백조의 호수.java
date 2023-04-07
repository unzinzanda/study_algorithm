import java.io.*;
import java.util.*;

//백준 3197 백조의 호수
/*
 * 1. 두 백조가 속한 곳을 각각의 집합으로 표현
 * 2. 얼음을 녹여가며 유니온
 * 3. 두 백조의 루트가 같아지면 결과값 출력
 * 
 * 힌트 : 새로 물이 된 영역에서만 사방탐색을 진행
 * 
 * 고칠 부분 : 얼음은 모든 곳에서 녹는다.(현재 백조가 있는 곳에서만 얼음이 녹음)
 * 
 * --> 구현 실패 (시간 초과)
 * 
 * 변경한 코드
 * 1. 새로 물이 된 영역으로만 얼음 녹이기
 * 2. 백조 만나는지 확인
 * 
 * 힌트 : 한 백조가 다른 백조가 속한 영역에 들어서면 두 백조가 만난 것이다.
 * */
public class Main {
	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int R, C;
	static char lake[][];
	static boolean visited[][];
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static Queue<Point> search;
	static Queue<Point> next;
	static int res = 0;
	static Point dest;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");

		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		lake = new char[R][C];
		search = new ArrayDeque<>();
		next = new ArrayDeque<>();
		
		// 입력 받기, 백조의 좌표를 저장하고 각 백조의 루트를 1과 2로 설정.
		for (int i = 0; i < R; i++) {
			lake[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (lake[i][j] == 'L') {
					if(next.isEmpty()) {
						next.add(new Point(i, j));
					} else dest = new Point(i, j);
					search.add(new Point(i, j));
				}
				if (lake[i][j] == '.')
					search.add(new Point(i, j));
			}
		}
		
		visited = new boolean[R][C];
		iceToWater();
		System.out.println(res);
	}

	private static boolean meet() {
		Queue<Point> q = new ArrayDeque<>(next);
		next.clear();
		visited[q.peek().x][q.peek().y] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny])
					continue;
				
				if (nx == dest.x && ny == dest.y)
					return true;

				visited[nx][ny] = true;
				if(lake[nx][ny] == 'X') 
					next.add(new Point(nx, ny));
				else 
					q.add(new Point(nx, ny));
			}
		}

		return false;
	}

	private static void iceToWater() {
		boolean visited[][] = new boolean[R][C];

		while (!search.isEmpty()) {
			int size = search.size();
			
			if(meet()) return;
			res++;
			for(int k = 0;k < size;k++) {
				Point cur = search.poll();

				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny])
						continue;

					if (lake[nx][ny] == 'X') {
						visited[nx][ny] = true;
						lake[nx][ny] = '.';
						search.add(new Point(nx, ny));
					}
				}
			}
		}
	}

}