import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 1197 최소 스패닝 트리 - 크루스칼
public class Main {
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int cost;
		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		// 가중치가 가장 작은 간선부터 선택해야 함, 가중치로 오름차순 정렬
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	
	static int V;
	static int E;
	static Edge[] pq;	// 간선 정보를 담고 정렬할 배열
	static int[] root;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		V = Integer.parseInt(str[0]);
		E = Integer.parseInt(str[1]);
		
		root = new int[V + 1];
		pq = new Edge[E];
		
		for(int i = 0;i <= V; i++) root[i] = i;
		for(int i = 0; i < E;i++) {
			str = br.readLine().split(" ");
			int from = Integer.parseInt(str[0]);
			int to = Integer.parseInt(str[1]);
			int cost = Integer.parseInt(str[2]);
			pq[i] = new Edge(from, to, cost);
		}
		
		System.out.println(kruskal());
	}
	private static int kruskal() {
		int sum = 0;	// 스패닝 트리의 가중치 합, 최솟값을 출력할 예정
		int cnt = 0;	// 선택된 간선 개수
		Arrays.sort(pq);
		for(Edge edge : pq) {
			if(union(edge.from, edge.to)) {
				sum += edge.cost;
				// 선택할 수 있는 간선의 최댓값 == V - 1
				if(++cnt == V - 1) return sum;
			}
		}
		
		return sum;
	}
	// 두 정점이 이미 같은 트리에 속하는지 확인
	// 같은 트리에 속한다면 연결할 수 없음 -> 사이클을 이루기 때문
	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false;
		root[a] = b;
		return true;
	}
	
	public static int find(int a) {
		if(root[a] == a) return a;
		else {
			return root[a] = find(root[a]);
		}
	}
}