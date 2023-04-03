package gumi_5;

import java.io.*;

// 백준 1987 알파벳
public class b1987 {
	static int R;
	static int C;
	static String alpha[][];
	static boolean visited[] = new boolean[26];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	static int max = 0;
	
	public static void search(int x, int y, int cnt) {
		max = Math.max(max, cnt);
		for(int i = 0; i < 4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >= 0 && ny >= 0 && nx < R && ny < C) {
				if(!visited[alpha[nx][ny].charAt(0) - 'A']) {
					visited[alpha[nx][ny].charAt(0) - 'A'] = true;
					search(nx, ny, cnt+1);
					visited[alpha[nx][ny].charAt(0) - 'A'] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		alpha = new String[R][C];
		
		for(int i = 0;i < R;i++) {
			str = br.readLine().split("");
			for(int j = 0; j < str.length;j++) {
				alpha[i][j] = str[j];
				if(i == 0 && j == 0) visited[str[j].charAt(0) - 'A'] = true;
			}
		}
		
		search(0, 0, 1);
		
		System.out.println(max);
	}
}
