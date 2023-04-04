import java.io.*;
import java.util.Arrays;

//SWEA 5643 키 순서
/*
 * 플로이드 워샬을 돌림
 * 모든 정점에 대해 경로가 있는 정점은 키 순서 표현 가능
 * 해당 정점에서 하나라도 경로가 없는 정점이 있으면 순서 표현X
 * 따라서 특정 정점 i에 대해 arr[i][j]와 arr[j][i]에 초기값이 있으면 표현 X
 * */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");

		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int arr[][] = new int[N + 1][N + 1];

		for (int i = 0; i <= N; i++) {
			Arrays.fill(arr[i], -1);
			arr[i][i] = 0;
		}

		int u, v;
		for (int i = 0; i < M; i++) {
			str = br.readLine().split(" ");
			u = Integer.parseInt(str[0]);
			v = Integer.parseInt(str[1]);
			arr[u][v] = 1;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == k || j == k || i == j || arr[i][k] == -1 || arr[k][j] == -1)
						continue;
					arr[i][j] = arr[i][k] + arr[k][j];
				}
			}
		}

		int res = 0;
		for (int i = 1; i <= N; i++) {
			boolean flag = false;

			for (int j = 1; j <= N; j++) {
				if (arr[i][j] == -1 && arr[j][i] == -1)
					flag = true;
			}

			if (!flag)
				res++;
		}

		System.out.println(res);
	}
}