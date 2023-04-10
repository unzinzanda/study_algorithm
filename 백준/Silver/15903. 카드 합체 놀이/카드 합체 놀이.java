import java.io.*;
import java.util.*;

//백준 15903 카드 합체 놀이
/*
 * 제일 작은 값을 합치고 그 결과를 두 번 우선순위 큐에 추가
 * */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		
		PriorityQueue<Long> q = new PriorityQueue<>();
		
		str = br.readLine().split(" ");
		for(int i = 0;i < n;i++) q.add(Long.parseLong(str[i]));
		
		long a, b;
		for(int i = 0;i < m;i++) {
			a = q.poll();
			b = q.poll();
			q.add(a + b);
			q.add(a + b);
		}
		
		long sum = 0;
		while(!q.isEmpty()) sum += q.poll();
		
		System.out.println(sum);
	}
}