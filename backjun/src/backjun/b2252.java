package backjun;

import java.io.*;
import java.util.*;

// 백준 2252 줄 세우기
/*
 * 위상정렬!
 * 배웠던 틀에서 벗어나 orderList를 만들어 반환하지 않고 StringBuilder에 결과값을 바로 저장하니 시간이 거의 반으로 감소
 */
public class b2252 {
	static int N, M;
	static ArrayList<Integer> adjList[];
	static int inDegree[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		// 변수 할당 및 초기화
		adjList = new ArrayList[N + 1];
		inDegree = new int[N + 1];
		
		for(int i = 0;i <= N;i++) adjList[i] = new ArrayList<>();
		
		int front, back;
		for(int i = 0;i < M;i++) {
			str = br.readLine().split(" ");
			front = Integer.parseInt(str[0]);
			back = Integer.parseInt(str[1]);
			
			adjList[front].add(back);
			inDegree[back]++;
		}
		topologySort();
		System.out.println(sb.toString());
	}
	
	private static void topologySort() {
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i = 1;i <= N;i++) {
			if(inDegree[i] == 0) q.add(i);
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" ");
			
			for(int i = 0;i < adjList[cur].size();i++) {
				if(--inDegree[adjList[cur].get(i)] == 0) 
					q.add(adjList[cur].get(i));
			}
		}
		
		sb.append("\n");
	}
}
