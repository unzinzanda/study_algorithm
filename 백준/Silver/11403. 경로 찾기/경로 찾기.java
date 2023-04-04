import java.io.*;

//백준 11403 경로 찾기
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int arr[][] = new int[N + 1][N + 1];
		
		String str[];
		
		for(int i = 1;i <= N;i++) {
			str = br.readLine().split(" ");
			for(int j = 1;j <= N;j++) {
				arr[i][j] = Integer.parseInt(str[j - 1]);
				if (arr[i][j] == 0 && i != j) {
					arr[i][j] = -1;
				}
			}
		}
		
		for(int k = 1;k <= N;k++) {
			for(int i = 1;i <= N;i++) {
				for(int j = 1;j <= N;j++) {
					if(i == k || j == k || arr[i][k] == -1 || arr[k][j] == -1) continue;
					arr[i][j] = 1;
				}
			}
		}
		
		for(int i = 1;i <= N;i++) {
			for(int j = 1;j <= N;j++) {
				if(arr[i][j] == -1) System.out.print(0 + " ");
				else System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}