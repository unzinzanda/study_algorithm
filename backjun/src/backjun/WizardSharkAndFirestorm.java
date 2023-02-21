package backjun;

import java.io.*;

// 백준 20058 마법사 상어와 파이어스톰
public class WizardSharkAndFirestorm {
	static int N;
	static int Q;
	static int L;
	static int size; // 한 변의 길이
	static int ice[][];
	static int copy[][];
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	static boolean visited[][];
	static int total;
	static int iceArea;
	static int max = 0;
	
	private static void rotation(int len) {
		
		for(int i = 0;i < size;i+=len) {
			for(int j = 0;j < size; j += len) {
				for(int a = 0; a < len;a++) {
					for(int b = 0; b < len;b++) {
						copy[a + i][b + j] = ice[i + len - 1 - b][j + a];
					}
				}
			}
		}
		
		// 원본으로 복사
		for(int i = 0;i < size;i++) {
			for(int j = 0 ;j < size;j++) {
				ice[i][j] = copy[i][j];
			}
		}
	}
	
	private static void fire() {
		for(int i = 0;i < size;i++) {
			for(int j = 0;j < size;j++) {
				int cnt = 0;
				for(int k = 0;k < 4;k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(nx >= 0 && nx < size && ny >= 0 && ny < size) {
						if(ice[nx][ny] > 0) cnt++;
					}
				}
				if(cnt < 3) copy[i][j] -= 1;
			}
		}
		
		// 원본으로 복사
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				ice[i][j] = copy[i][j];
			}
		}
	}
	
	private static void search(int x, int y) {
		for(int i = 0;i < 4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >= 0 && nx < size && ny >= 0 && ny < size) {
				if(!visited[nx][ny] && ice[nx][ny] > 0) {
					visited[nx][ny] = true;
					total += ice[nx][ny];
					iceArea++;
					search(nx, ny);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		Q = Integer.parseInt(str[1]);
		
		size = (int)Math.pow(2, N);
		ice = new int[size][size];
		copy = new int[size][size];
		visited = new boolean[size][size];
		
		for(int i = 0;i < size;i++) {
			str = br.readLine().split(" ");
			for(int j = 0 ;j < size;j++) {
				ice[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		str = br.readLine().split(" ");
		for(int i = 0;i < Q;i++) {
			L = Integer.parseInt(str[i]);
			
			rotation((int)Math.pow(2, L));
			fire();
		}
		
		for(int i = 0;i < size;i++) {
			for(int j = 0;j < size;j++) {
				if(ice[i][j] > 0 && !visited[i][j]) {
					iceArea = 1;
					total += ice[i][j];
					visited[i][j] = true;
					search(i, j);
					max = Math.max(max, iceArea);
				}
			}
		}
		
		System.out.println(total);
		System.out.println(max);
	}
}
