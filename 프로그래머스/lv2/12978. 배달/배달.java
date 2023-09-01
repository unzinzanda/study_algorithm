import java.util.*;
class Solution {
    
    static class Node implements Comparable<Node> {
        int to;
        int cost;
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    
    static ArrayList<Node> adjList[];
    static boolean visited[];
    static int D[];
    static PriorityQueue<Node> pq = new PriorityQueue<>(); 
    
    static void dijkstra(int vertex) {
        pq.add(new Node(vertex, 0));
        D[vertex] = 0;
        while(!pq.isEmpty()) {
            Node next = pq.poll();
            if(!visited[next.to]) {
                visited[next.to] = true;
                
                for(Node node : adjList[next.to]) {
                    if(!visited[node.to] && D[node.to] > D[next.to] + node.cost) {
                        D[node.to] = D[next.to] + node.cost;
                        pq.add(new Node(node.to, D[node.to]));
                    }
                }
            }
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        adjList = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        D = new int[N + 1];
        
        for(int i = 0;i <= N;i++) adjList[i] = new ArrayList<>();
        
        for(int i = 0;i < road.length;i++) {
            adjList[road[i][0]].add(new Node(road[i][1], road[i][2]));
            adjList[road[i][1]].add(new Node(road[i][0], road[i][2]));
        }
        
        Arrays.fill(D, Integer.MAX_VALUE);
        
        dijkstra(1);
        
        for(int i = 0;i <= N;i++) {
            if(D[i] <= K) answer++;
        }

        return answer;
    }
}