import java.io.*;
import java.util.*;

//백준 2533 사회망 서비스(SNS)
/*
 * 부모 노드가 얼리어답터 O : 자식은 얼리어답터일 필요 X
 * 부모 노드가 얼리어답터 X : 자식은 얼리어답터여야 함.
 * 
 * [node][0] : 해당 노드가 얼리어답터가 아닐 경우, 모든 자식 노드의 [node][1]의 합??
 * [node][1] : 해당 노드가 얼리어답터일 경우, 모든 자식노드의 [node][1]와 [node][0] 중 최소값의 합
 * 
 * */
public class Main {
	static int N;
	static int dp[][];
	static boolean visited[];
	static ArrayList<Integer> tree[];
	
	private static void startDp(int node) {
		visited[node] = true;
		dp[node][1] = 1;
		for (int i = 0; i < tree[node].size(); i++) {
			if(!visited[tree[node].get(i)]) {
				startDp(tree[node].get(i));
				dp[node][0] += dp[tree[node].get(i)][1];
				dp[node][1] += Math.min(dp[tree[node].get(i)][1], dp[tree[node].get(i)][0]);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][2];
		tree = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		
		for(int i = 0 ;i <= N;i++) tree[i] = new ArrayList<>();
		
		String str[];
		int a, b;
		for(int i = 0;i < N - 1;i++) {
			str = br.readLine().split(" ");
			a = Integer.parseInt(str[0]);
			b = Integer.parseInt(str[1]);
			tree[a].add(b);
			tree[b].add(a);
		}
		
		startDp(1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
}