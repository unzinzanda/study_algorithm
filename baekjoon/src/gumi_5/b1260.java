package gumi_5;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.IOException;

//백준 1260 DFS와 BFS
public class b1260 {
	public static int N;
	public static int M;
	public static int[][] graph;
	public static boolean[] visited;
	
	public static void dfs(int v) {
		if(visited[v] == false) {
			System.out.print(v + " ");
			visited[v] = true;
		}
		for(int i = 1; i < graph[v].length;i++) {
			if(visited[i] == false && graph[v][i] == 1) {
				dfs(i);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		int K = Integer.parseInt(str[2]);
		graph = new int[N+1][N+1];
		visited = new boolean[N+1];
		for(int i = 0; i < M;i++) {
			str = br.readLine().split(" ");
			graph[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = 1;
			graph[Integer.parseInt(str[1])][Integer.parseInt(str[0])] = 1;
		}
		dfs(K);
		System.out.println();
		for(int i = 0; i < N + 1; i++) visited[i] = false;
		
		Queue<Integer> q = new LinkedList<>();
		q.add(K);
		while(!q.isEmpty()) {
			int a = q.poll();
			if(visited[a] == false) {
				visited[a] = true;
				System.out.print(a + " ");
			}
			for(int i = 1; i < graph[a].length;i++) {
				if(visited[i] == false && graph[a][i] == 1) 
					q.add(i);
			}
		}
	}
}
