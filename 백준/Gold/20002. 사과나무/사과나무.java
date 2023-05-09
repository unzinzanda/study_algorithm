import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int max = Integer.MIN_VALUE, sum = 0;
		int arr[][] = new int[N + 1][N + 1];
		String str[];
		
		for(int i = 1;i <= N;i++) {
			str = br.readLine().split(" ");
			max = Math.max(max, Integer.parseInt(str[0]));
			arr[i][1] = Integer.parseInt(str[0]);
			for(int j = 1;j < str.length;j++) {
				int num = Integer.parseInt(str[j]);
				arr[i][j + 1] = num + arr[i][j];
				max = Math.max(num, max);
				sum += num;
			}
		}
		max = Math.max(sum, max);
		
		for(int k = 2;k < N;k++) {
			for(int y = 1;y <= N - k + 1;y++) {
				for(int x = 1;x <= N - k + 1;x++) {
					sum = 0;
					for(int i = x; i < x + k;i++) {
						sum += arr[i][y + k - 1] - arr[i][y - 1];
					}
					max = Math.max(max, sum);
				}
			}
		}
		
		System.out.println(max);
	}
}