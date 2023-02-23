package backjun;

import java.io.*;

// 백준 2644 촌수 계산
public class b2644 {
	static int N;
	static int a;
	static int b;
	static int family[][];
	static boolean visited[];
	static int depth;
	
	private static void dfs(int num) {
		if(num == b) {
			System.out.println(depth);
			System.exit(0);
		}
		for(int i = 1;i <= N;i++) {
			if(family[num][i] == 1 && !visited[i]) {
				visited[i] = true;
				depth++;
				dfs(i);
				depth--;
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		family = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		
		String str[] = br.readLine().split(" ");
		a = Integer.parseInt(str[0]);
		b = Integer.parseInt(str[1]);
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i = 0;i < M;i++) {
			str = br.readLine().split(" ");
			int u = Integer.parseInt(str[0]);
			int v = Integer.parseInt(str[1]);
			family[u][v] = 1;
			family[v][u] = 1;
		}
		
		visited[a] = true;
		dfs(a);
		
		System.out.println(-1);
	}
}
