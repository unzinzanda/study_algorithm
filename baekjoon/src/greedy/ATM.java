package greedy;

import java.io.*;
import java.util.*;

// 백준 11399 ATM
public class ATM {
	static int N;
	static List<Integer> list = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String str[] = br.readLine().split(" ");
		for(int i = 0; i < str.length;i++) list.add(Integer.parseInt(str[i]));
		
		Collections.sort(list);
		
		int res = 0;
		for(int i = 0;i < N;i++) {
			res += list.get(i) * (N - i);
		}
		System.out.println(res);
	}
}
