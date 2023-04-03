package bruteforce.b14501;

import java.util.Scanner;

//백준 14501 퇴사
public class Main {
	static int T[];
	static int P[];
	static int N;
	static int max = 0;
	
	public static void dp(int cost, int idx) {
		if(idx >= N) {
			if(max < cost) max = cost;
			return;
		}
		else {
			// N일째에 마무리 되는 일도 수행가능
			if(idx + T[idx] <= N) {
				cost += P[idx];
				dp(cost, idx + T[idx]);
				cost -= P[idx];
			}
			else {
				if(max < cost) max = cost;
			}
			dp(cost, idx + 1);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		T = new int[N];
		P = new int[N];
		for(int i = 0; i < N;i++) {
			T[i] = sc.nextInt();
			P[i] = sc.nextInt();
		}
		
		dp(0, 0);
		
		System.out.println(max);
		sc.close();
	}
}
