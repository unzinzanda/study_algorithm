package bruteforce.b18111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 18111 마인크래프트
public class Main {
	static int N;
	static int M;
	static int block;
	static int map[][];
	static int height;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int minTime = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		block = Integer.parseInt(str[2]);
		
		map = new int[N][M];
		
		for(int i = 0; i < N;i++) {
			str = br.readLine().split(" ");
			for(int j = 0; j < str.length;j++) {
				map[i][j] = Integer.parseInt(str[j]);
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);
			}
		}
		
		for(int i = min;i <= max;i++) {
			int temp = block;
			int time = 0;
			for(int n = 0; n < N;n++) {
				for(int m = 0; m < M;m++) {
					int diff = map[n][m] - i;
					if(diff == 0) continue;
					else if(diff < 0) {
						time += Math.abs(diff);
						temp -= Math.abs(diff);
					}
					else if(diff > 0) {
						time += 2 * diff;
						temp += diff;
					}
				}
			}
			
			if(temp >= 0) {
				if(minTime >= time) {
					minTime = time;
					height = i;
				}
			}
		}
		
		System.out.println(minTime + " " + height);
		
	}
}
