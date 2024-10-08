import java.io.*;

public class Main {
	static int N, M;
	static int order[];
	static boolean visited[];
	static StringBuilder sb = new StringBuilder();
	
	private static void perm(int cnt) {
		if(cnt == M) {
			for(int i = 0;i < M;i++) sb.append(order[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i = 1;i <= N;i++) {
			if(!visited[i]) {
				visited[i] = true;
				order[cnt] = i;
				perm(cnt + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		order = new int[M];
		visited = new boolean[N + 1];
		
		perm(0);
		System.out.println(sb.toString());
	}
}