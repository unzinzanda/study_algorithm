package bruteforce.b14225;

import java.io.*;

// 백준 14225 부분수열의 합
public class Main {
	static int N;
	static int num[];
	static boolean visited[];
	static boolean exist[] = new boolean[10000000];
	
	public static void calc(int idx, int cnt, int r) {
		if(cnt == r) {
			int res = 0;
			for(int i = 0;i < N;i++) {
				if(visited[i]) res += num[i];
			}
			exist[res] = true;
			return;
		}
		for(int i = idx;i < N;i++) {
			if(!visited[i]) {
				visited[i] = true;
				calc(i + 1, cnt + 1, r);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		num = new int[N];
		visited = new boolean[N];
		String str[] = br.readLine().split(" ");
		for(int i = 0; i < str.length;i++) num[i] = Integer.parseInt(str[i]);
		
		for(int i = 1; i <= N;i++) {
			calc(0, 0, i);
		}
		
		for(int i = 1; i < 10000000;i++) {
			if(!exist[i]) {
				System.out.println(i);
				break;
			}
		}
	}
}
