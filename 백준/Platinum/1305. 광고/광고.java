import java.io.*;

//백준 1305 광고
public class Main {
	private static int[] getPi(char[] pattern) {
		int pi[] = new int[pattern.length];
		
		int j = 0;
		for(int i = 1;i < pattern.length;i++) {
			while(j > 0 && pattern[i] != pattern[j]) j = pi[j - 1];
			 
			if(pattern[i] == pattern[j]) pi[i] = ++j;
			else j = 0;
		}
		
		return pi;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int L = Integer.parseInt(br.readLine());
		char pattern[] = br.readLine().toCharArray();
		
		int pi[] = getPi(pattern);
		
		System.out.println(L - pi[L - 1]);
	}
}