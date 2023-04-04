import java.io.*;
import java.util.Arrays;

//백준 11404 플로이드
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int arr[][] = new int[N + 1][N + 1];
		
		for(int i = 0;i <= N;i++) {
			Arrays.fill(arr[i], 30000000);
			arr[i][i] = 0;
		}
		
		int M = Integer.parseInt(br.readLine());
		
		int u, v, w;
		String str[];
		for(int i = 0;i < M;i++) {
			str = br.readLine().split(" ");
			u = Integer.parseInt(str[0]);
			v = Integer.parseInt(str[1]);
			w = Integer.parseInt(str[2]);
			
			arr[u][v] = Math.min(arr[u][v], w);
		}
		
		for(int k = 1;k <= N;k++) {
			for(int i = 1;i <= N;i++) {
				for(int j = 1;j <= N;j++) {
					if(i != k && j != k && i != j) {
						arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
					}
				}
			}
		}
		
		for(int i = 1;i <= N;i++) {
			for(int j = 1;j <= N;j++) {
				if(arr[i][j] != 30000000) System.out.print(arr[i][j] + " ");
				else System.out.print(0 + " ");
			}
			System.out.println();
		}
	}
}