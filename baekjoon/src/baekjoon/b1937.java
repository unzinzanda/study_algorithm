package baekjoon;

import java.io.*;

//백준 1937 욕심쟁이 판다
public class b1937 {
	static int N;
	static int map[][];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		String str[];
		for(int i = 0;i < N;i++) {
			str = br.readLine().split(" ");
			for(int j = 0;j < str.length;j++) map[i][j] = Integer.parseInt(str[j]);
		}
		
		
	}
}
