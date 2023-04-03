package bruteforce.b18428;

import java.io.*;
import java.util.*;

// 백준 18428 감시 피하기

class Point {
	public int x;
	public int y;
	
	public Point(int x, int y) {;
		this.x = x;
		this.y = y;
	}
	
}

public class Main {
	static int N;
	static String map[][];
	static List<Point> stud = new ArrayList<>();
	static boolean flag;
	static boolean meetT;
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	
	public static void dfs(int cnt) {
		if(cnt == 3) {
			for(Point s : stud) {
				check(s, -1, 0);
				if(meetT) break;
			}
			if(!meetT) {
				System.out.println("YES");
				System.exit(0);
			}
			return;
		}
		for(int i = 0;i < N;i++) {
			for(int j = 0;j < N;j++) {
				if(map[i][j].equals("X")) {
					map[i][j] = "O";
					dfs(cnt + 1);
					map[i][j] = "X";
					meetT = false;
				}
			}
		}
	}
	
	public static void check(Point s, int n, int cnt) {
		int x = s.x;
		int y = s.y;
		if(n == -1) {
			for(int i = 0; i < 4;i++) {
				if(meetT) break;
				check(s, i, 1);
				flag = false;
			}
		}
		
		else {
			int nx = x + dx[n] * cnt;
			int ny = y + dy[n] * cnt;
			if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if(map[nx][ny].equals("O")) flag = true;
				if(!flag && map[nx][ny].equals("T")) {
					meetT = true;
					return;
				}
				check(s, n, cnt + 1);
			}
			return;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new String[N][N];
		
		for(int i = 0; i < N;i++) {
			String str[] = br.readLine().split(" ");
			for(int j = 0;j < str.length;j++) {
				map[i][j] = str[j];
				if(str[j].equals("S")) stud.add(new Point(i, j));
			}
		}
		
		for(int i = 0; i < stud.size();i++) {
			for(int j = 0; j < 4;j++) {
				int nx = stud.get(i).x + dx[j];
				int ny = stud.get(i).y + dy[j];
				if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
					if(map[nx][ny].equals("T")) {
						System.out.println("NO");
						System.exit(0);
					}
				}
			}
		}
		
		for(int i = 0; i < N;i++) {
			for(int j = 0; j < N;j++) {
				if(map[i][j].equals("X")) {
					map[i][j] = "O";
					dfs(1);
					map[i][j] = "X";
					meetT = false;
				}
			}
		}
		
		System.out.println("NO");
		
	}
}
