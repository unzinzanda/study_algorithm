import java.io.*;
import java.util.*;

//백준 6091 핑크 플로이드
/*
 * 주어진 인접행렬을 통해 MST를 만들면 그것이 원본 인접리스트
 * */
public class Main {
	static class Node implements Comparable<Node> {
		int from;
		int to;
		int cost;
		public Node(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	static int parent[];
	
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			if(a < b) parent[b] = a;
			else parent[a] = b;
			return true;
		}
		return false;
	}
	
	private static int find(int a) {
		if(parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		
		for(int i = 0;i <= N;i++) parent[i] = i;
		
		String str[];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int i = 1; i < N;i++) {
			str = br.readLine().split(" ");
			for(int j = 0;j < str.length;j++) pq.add(new Node(i, j + i + 1, Integer.parseInt(str[j]))); 
		}
		
		ArrayList<Node> list = new ArrayList<>();
		
		//크루스칼 알고리즘을 통한 MST 구하기
		int cnt = 0;
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			
			if(union(temp.from, temp.to)) {
				cnt++;
				list.add(temp);
				if(cnt == N - 1) break;
			}
		}
		
		//원본 인접 리스트 만들기
		ArrayList<Integer> adjList[] = new ArrayList[N + 1];
		for(int i = 0;i <= N;i++) adjList[i] = new ArrayList<>();
		
		for(Node temp : list) {
			adjList[temp.from].add(temp.to);
			adjList[temp.to].add(temp.from);
		}
		
		for(int i = 1;i <= N;i++) {
			sb.append(adjList[i].size()).append(" ");
			Collections.sort(adjList[i]);
			for(int a : adjList[i]) sb.append(a).append(" ");
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}