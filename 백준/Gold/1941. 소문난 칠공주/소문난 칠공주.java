import java.io.*;
import java.util.*;

//백준 1941 소문난 칠공주
/*
 * 1. 조합으로 7명의 학생을 뽑음.
 * 2. 인접해 있는지 확인
 * 3. 인접해 있으면 S가 몇 개인지 확인.
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
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}
	
	static char desk[][] = new char[5][5];
	static boolean visited[][];
	static boolean isSelected[] = new boolean[25];
	static Point stud[] = new Point[25];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	static int res;
	
	private static void comb(int idx, int cnt) {
		if(cnt == 7) {
			int scnt = 0;
			ArrayList<Point> list = new ArrayList<>();
			for(int i = 0;i < 25;i++) {
				if(isSelected[i]) {
					list.add(stud[i]);
					if(desk[stud[i].x][stud[i].y] == 'S') scnt++;
				}
			}
			
			if(scnt >= 4) {
				visited = new boolean[5][5];
				Queue<Point> q = new ArrayDeque<>();
				q.add(list.get(0));
				visited[list.get(0).x][list.get(0).y] = true;
				int correct = 1;
				
				while(!q.isEmpty()) {
					Point cur = q.poll();
					
					for(int i = 0;i < 4;i++) {
						int nx = cur.x + dx[i];
						int ny = cur.y + dy[i];
						
						if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visited[nx][ny]) continue;
						
						Point temp = new Point(nx, ny);
						visited[nx][ny] = true;
						for(Point l : list) {
							if(temp.equals(l)) {
								q.add(temp);
								correct++;
								break;
							}
						}
					}
				}
				
				if(correct == 7) res++;
			}

			return;
		}
		
		for(int i = idx;i < 25;i++) {
			isSelected[i] = true;
			comb(i + 1, cnt + 1);
			isSelected[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//입력 받기
		int numbering = 0;
		for(int i = 0; i < 5;i++) {
			desk[i] = br.readLine().toCharArray();
			for(int j = 0;j < 5;j++) stud[numbering++] = new Point(i, j);
		}
		
		comb(0, 0);
		
		System.out.println(res);
	}
}