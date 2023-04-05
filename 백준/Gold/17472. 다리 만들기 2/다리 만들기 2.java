import java.io.*;
import java.util.*;

//백준 17472 다리 만들기 2
/*
 * 1. 섬 구하고 번호 붙이기
 * 2. 섬 사이의 최소 거리 구하기
 * 3. 최소 신장 트리 구하기
 * */
public class Main {
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
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
	
	static int N, M, numbering;
	static int map[][], adj[][], parent[];
	static boolean visited[][];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	
	private static void dfs(int x, int y, int numbering) {
		for(int i = 0;i < 4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] == 0) continue;
			
			visited[nx][ny] = true;
			map[nx][ny] = numbering;
			dfs(nx, ny, numbering);
		}
	}
	
	//섬과 섬 사이의 최소 다리 길이를 구하는 함수
	private static void makeBridge(int x, int y, int num) {
		visited[x][y] = true;
		for(int i = 0;i < 4;i++) {
			Queue<Point> q = new ArrayDeque<>();
			q.add(new Point(x, y));
			
			int bridgeLen = 0;
			while(!q.isEmpty()) {
				Point cur = q.poll();
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				//다른 섬을 마주하지 못하고 지형 밖으로 나간 경우, 동일 번호의 땅을 마주한 경우
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == num) break;
				
				//다른 섬과 마주한 경우, 다리의 길이가 2 이상이라면 업데이트
				if(map[nx][ny] != 0 && map[nx][ny] != num) {
					if(bridgeLen > 1) {
						adj[num][map[nx][ny]] = Math.min(adj[num][map[nx][ny]], bridgeLen);
					}
					break;
				}
				
				//바다인 경우, 다리를 건설해야 함.
				if(map[nx][ny] == 0) {
					bridgeLen++;
					q.add(new Point(nx, ny));
				}
			}
		}
	}
	
	private static int kruskal() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i = 1;i < numbering;i++) {
			for(int j = 1;j < numbering;j++) pq.add(new Node(i, j, adj[i][j]));
		}
		
		int res = 0, count = 0;
		
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			
			if(union(temp.from, temp.to)) {
				if(temp.cost == Integer.MAX_VALUE) return -1;
				res += temp.cost;
				if(++count == numbering - 1) return res;
			}
		}
		
		return res;
	}
	
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
		String str[] = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0;i < N;i++) {
			str = br.readLine().split(" ");
			for(int j = 0;j < str.length;j++) map[i][j] = Integer.parseInt(str[j]);
		}
		
		//dfs를 이용하여 섬에 번호를 붙임.
		numbering = 1;
		for(int i = 0;i < N;i++) {
			for(int j = 0;j < M;j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					visited[i][j] = true;
					map[i][j] = numbering;
					dfs(i, j, numbering);
					numbering++;
				}
			}
		}
		
		
		//각 섬 사이의 최소 거리를 저장하기 위한 배열 초기화
		adj = new int[numbering][numbering];
		for(int i = 0;i < numbering;i++) Arrays.fill(adj[i], Integer.MAX_VALUE);
		
		visited = new boolean[N][M];
		for(int i = 0;i < N;i++) {
			for(int j = 0;j < M;j++) {
				if(!visited[i][j] && map[i][j] != 0) {
					makeBridge(i, j, map[i][j]);
				}
			}
		}
		
		//최소 신장 트리를 구하고 간선의 합을 리턴 받아 출력
		parent = new int[numbering];
		for(int i = 0;i < numbering;i++) parent[i] = i;
		
		int res = kruskal();
		
		System.out.println(res);
	}
}