package backjun;

import java.io.*;
import java.util.*;

// 백준 10775 공항
public class b10775 {
	static int parent[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		
		for(int i = 0;i <= N;i++) parent[i] = i;
		
		int gate;
		int res = 0;
		for(int i = 0;i < M;i++) {
			gate = Integer.parseInt(br.readLine());
			
			if(parent[gate] != 0) {
				res++;
				union(gate, parent[parent[gate] - 1]);
			} else break;
		}
		System.out.println(res);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			if(a < b) parent[b] = a;
			else parent[a] = b;
		}
	}
	
	private static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		else return parent[a] = a;
	}
}
