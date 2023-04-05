import java.io.*;

//백준 15961 회전 초밥
public class Main {
	static int N, d, k, c;
	static int visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		d = Integer.parseInt(str[1]);
		k = Integer.parseInt(str[2]);
		c = Integer.parseInt(str[3]);
		
		//먹은 초밥인지 아닌지 표시할 배열
		visited = new int[d + 1];
		
		int table[] = new int[N + k - 1];
		for(int i = 0;i < N;i++) {
			table[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0;i < k - 1;i++) {
			table[i + N] = table[i];
		}
		
		int start = 0, end = 0;
		int res = 0, cnt = 0, eat = 0;
		
		while(end < N + k - 1) {
			if(visited[table[end]] == 0) {
				eat++;
			} 
			visited[table[end]]++;
			cnt++;
			end++;
			
			if(cnt == k) {
				if(visited[c] == 0) res = Math.max(res, eat + 1);
				else res = Math.max(res, eat);
				
				visited[table[start]]--;
				if(visited[table[start]] == 0) eat--;
				start++;
				cnt--;
			}
		}
		
		System.out.println(res);
	}
}