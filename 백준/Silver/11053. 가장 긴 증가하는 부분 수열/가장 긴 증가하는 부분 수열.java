import java.io.*;

// 백준 11053 가장 긴 증가하는 부분 수열
public class Main {
	static int N;
	static int num[];
	static int len[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		String str[] = br.readLine().split(" ");
		num = new int[N];
		len = new int[N];
		for(int i = 0;i < str.length;i++) num[i] = Integer.parseInt(str[i]);
		
		len[0] = 1;
		for(int i = 1;i < N;i++) {
			len[i] = 1;
			for(int j = 0;j < i;j++) {
				if(num[j] < num[i]) len[i] = Math.max(len[j] + 1, len[i]);
			}
		}
		
		int max = 0;
		for(int i = 0; i < N;i++) {
			max = Math.max(max, len[i]);
		}
		
		System.out.println(max);
	}
}