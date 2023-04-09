import java.io.*;

//백준 11501 주식
/*
 * maxStock[i] : i ~ N일 중 최댓값 저장
 * 파는 건 언제나 하나의 주식을 팜
 * stock[i] < maxStock[i + 1] : 팜
 * */
public class Main {
	static int N;
	static int stock[];
	static int maxStock[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0;t < T;t++) {
			N = Integer.parseInt(br.readLine());
			
			stock = new int[N];
			maxStock = new int[N];
			String str[] = br.readLine().split(" ");
			for(int i = 0;i < N;i++) stock[i] = Integer.parseInt(str[i]);
			
			//앞이 아니라 뒤에서 부터 max값 찾으면 O(N)에 가능
			maxStock[N - 1] = stock[N - 1];
			for(int i = N - 2;i >= 0;i--) {
				maxStock[i] = Math.max(stock[i], maxStock[i + 1]);
			}
			
			long res = 0;
			
			for(int i = 0;i < N - 1;i++) {
				if(stock[i] < maxStock[i + 1]) res += maxStock[i + 1] - stock[i];
			}
			System.out.println(res);
		}
	}
}