package baekjoon;

import java.io.*;
import java.util.*;

// 백준 1021 회전하는 큐
public class RotationQueue {
	static int N;
	static int M;
	static int num[];
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		str = br.readLine().split(" ");
		Deque<Integer> deq = new ArrayDeque<>();
		for(int i = 1; i <= N;i++) deq.add(i);
		res = 0;
		
		for(int i = 0;i < str.length;i++) {
			int target = Integer.parseInt(str[i]);
			Iterator<Integer> iter = deq.iterator();
			int tar_idx = 0;
			while(iter.hasNext()) {
				if(iter.next() != Integer.valueOf(target)) tar_idx++;
				else break;
			}
			int half = deq.size() / 2;
			
			int cnt = 0;
			if(tar_idx <= half) {
				for(int j = 0;j < tar_idx;j++) {
					deq.addLast(deq.pollFirst());
					cnt++;
				}
			}
			else {
				for(int j = 0; j < deq.size() - tar_idx;j++) {
					deq.addFirst(deq.pollLast());
					cnt++;
				}
			}
			
			deq.pollFirst();
			res += cnt;
		}
		System.out.println(res);
	}
}
