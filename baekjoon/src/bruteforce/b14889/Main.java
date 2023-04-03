package bruteforce.b14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14889 스타트와 링크
public class Main {
	public static int N;
	public static int stats[][];
	public static int min = Integer.MAX_VALUE;
	public static boolean visited[];
	
	// 조합
	public static void select(int idx, int cnt) {
		if(cnt == N / 2) {
			calcStats();
			return;
		}
		for(int i = idx; i < N;i++) {
			if(!visited[i]) {
				visited[i] = true;
				select(i + 1, cnt + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void calcStats() {
		int start = 0;
		int link = 0;
		
		// 내부 for문을 i + 1부터 돌아서 1 2인 경우와 2 1 인 경우를 1 2에서 처리하여 반복을 줄일 수 있게 함
		for(int i = 0; i < N - 1;i++) {
			for(int j = i + 1;j < N;j++) {
				if(visited[i] && visited[j]) {
					start += stats[i][j];
					start += stats[j][i];
				}
				else if(!visited[i] && !visited[j]) {
					link += stats[i][j];
					link += stats[j][i];
				}
			}
		}
		min = Math.min(min, Math.abs(start - link));
		if(min == 0) {
			System.out.println(min);
			System.exit(0);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stats = new int[N][N];
		visited = new boolean[N];
		
		for(int i = 0; i < N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N;j++) {
				stats[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		select(0, 0);
		
		System.out.println(min);
	}
}
