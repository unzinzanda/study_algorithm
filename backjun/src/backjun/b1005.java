package backjun;

import java.io.*;
import java.util.*;

//백준 1005 ACM Craft
/*
 * 해당 정점까지 오는 최대 값 구하기
 * -> 목표 정점과 연결된 정점 중 최댓값 + 목표 정점하여 출력
 * 
 */
public class b1005 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0;t < T;t++) {
			String str[] = br.readLine().split(" ");
			int N = Integer.parseInt(str[0]);
			int M = Integer.parseInt(str[1]);
			
			ArrayList<Integer> graph[] = new ArrayList[N + 1];	// 위상 정렬을 위한 그래프
			for(int i = 0; i <= N;i++) {
				graph[i] = new ArrayList<>();
			}
			int inDegree[] = new int[N + 1];
			str = br.readLine().split(" ");
			
			int weight[] = new int[N + 1];
			int dp[] = new int[1001];
			
			for(int i = 0;i < str.length;i++) weight[i + 1] = Integer.parseInt(str[i]);
			
			int a, b;
			for(int i = 0;i < M;i++) {
				str = br.readLine().split(" ");
				a = Integer.parseInt(str[0]);
				b = Integer.parseInt(str[1]);
				
				graph[a].add(b);
				inDegree[b]++;
			}
			
			int goal = Integer.parseInt(br.readLine());
			Queue<Integer> q = new ArrayDeque<>();
			
			for(int i = 1;i <= N;i++) {
				if(inDegree[i] == 0) {
					q.add(i);
					dp[i] = weight[i];	// 가장 처음 큐에 들어오는 정점은 최댓값이 자기 자신
				}
			}
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				
				if(cur == goal) break;
				
				for(int i = 0;i < graph[cur].size();i++) {
					int node = graph[cur].get(i);
					dp[node] = Math.max(dp[node], dp[cur] + weight[node]);	// node라는 정점까지의 최댓값 구하기
					// 이 때, 진입차수가 0이 되지 않더라도 최댓값은 구해줘야 한다는 점에 유의
					
					if(--inDegree[node] == 0) {
						q.add(node);
					}
				}
			}
			
			
			System.out.println(dp[goal]);
		}
	}
}
