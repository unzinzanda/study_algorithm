package gumi_5;

import java.io.*;
import java.util.*;

// 백준 1753 최단 경로
public class b1753 {
	
	static class edge {
		int v;
		int weight;
		
		public edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
	}
	
	static int V;
	static int E;
	static int K;
	static List<ArrayList<edge>> graph = new ArrayList<ArrayList<edge>>();
	static int route[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		V = Integer.parseInt(str[0]);
		E = Integer.parseInt(str[1]);
		K = Integer.parseInt(br.readLine());
		
		for(int i = 0;i < V + 1;i++) {
			graph.add(new ArrayList<>());
		}
		
		route = new int[V + 1];
		Arrays.fill(route, Integer.MAX_VALUE);
		
		for(int i = 0; i < E;i++) {
			str = br.readLine().split(" ");
			int u = Integer.parseInt(str[0]);
			int v = Integer.parseInt(str[1]);
			int w = Integer.parseInt(str[2]);
			
			graph.get(u).add(new edge(v, w));
		}
		
		Queue<Integer> q = new LinkedList<>();
		q.add(K);
		route[K] = 0;
		while(!q.isEmpty()) {
			int tmp = q.poll();
			for(int i = 0; i < graph.get(tmp).size();i++) {
				int idx = graph.get(tmp).get(i).v;
				if(route[idx] == Integer.MAX_VALUE || route[idx] > route[tmp] + graph.get(tmp).get(i).weight) {
					route[idx] = route[tmp] + graph.get(tmp).get(i).weight;
					q.add(idx);
				}
			}
		}
		
		for(int i = 1;i <= V;i++) {
			if(route[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(route[i]);
		}
	}
}