package gumi_5;

import java.io.*;
import java.util.*;

// 백준 2606 바이러스
public class b2606 {
	static int graph[][];
	static int N;
	static int M;
	static int cnt = 0;
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new int[N+1][N+1];
		visited = new boolean[N+1];
		for(int i = 0; i < M;i++) {
			String str[] = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		q.add(1);
		int virus = 0;
		while(!q.isEmpty()) {
			int tmp = q.poll();
			if(!visited[tmp]) {
				visited[tmp] = true;
				virus++;
				for(int i = 0; i < graph[tmp].length;i++) {
					if(!visited[i] && graph[tmp][i] == 1) {
						q.add(i);
					}
				}
			}
		}
		
		// 1번 컴퓨터를 제외하고 바이러스에 감염된 컴퓨터의 수 출력
		System.out.println(virus - 1);
		
	}
}
 