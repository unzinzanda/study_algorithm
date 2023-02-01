package gumi_5;

import java.io.*;

// 백준 15652 N과 M (4)
public class b15652 {
	static int N;
	static int M;
	static int visited[];
	
	public static void backtrack(int idx, int cnt) {
		if(cnt == M) {
			for(int i = 1; i <= N;i++) {
				if(visited[i] != 0) {
					for(int j = 0; j < visited[i];j++) System.out.print(i + " ");
				}
			}
			System.out.println();
			return;
		}
		
		for(int i = idx;i <= N;i++) {
			visited[i] += 1;
			backtrack(i, cnt + 1);
			visited[i] -= 1;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		visited = new int[N + 1];
		
		backtrack(1, 0);
	}
}
