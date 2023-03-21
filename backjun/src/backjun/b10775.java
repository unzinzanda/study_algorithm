package backjun;

import java.io.*;
import java.util.*;

// 백준 10775 공항
public class b10775 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		Set<Integer> set = new HashSet<>();
		
		for(int i = 0;i < M;i++) {
			int num = Integer.parseInt(br.readLine());
			set.add(num);
		}
		
		System.out.println(set.size());
	}
}
