import java.io.*;
import java.util.*;

// 백준 20040 사이클 게임
public class Main {
	static int n, m;
	static int parent[];
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		parent = new int[n];
		for(int i = 0;i < n;i++) parent[i] = i;
		
		for(int i = 1;i <= m;i++) {
			str = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			if(!union(x, y)) {
				res = i;
				break;
			}
		}
		
		System.out.println(res);
	}
	
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a != b) {
			if(a < b) parent[b] = a;
			else parent[a] = b;
			return true;
		}
		return false;
	}
	
	private static int find(int a) {
		if(parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}
}