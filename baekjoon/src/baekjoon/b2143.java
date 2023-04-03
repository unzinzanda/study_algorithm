package baekjoon;

import java.io.*;
import java.util.*;

//백준 2143 두 배열의 합
/*
 * 두 집합의 부집합을 각각 구해두기
 * 이 때, 자료구조를 Map으로 하여 중복하여 나오는 부집합의 합의 갯수를 value로 저장
 * 
 * B의 key 중 T - A인 것이 있는지 찾고 있다면 그 값의 cnt 값을 res에 저장
 * */
public class b2143 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		long A[] = new long[N + 1];
		
		String str[] = br.readLine().split(" ");
		
		for(int i = 0;i < str.length;i++) A[i + 1] = Integer.parseInt(str[i]) + A[i];
		
		int M = Integer.parseInt(br.readLine());
		long B[] = new long[M + 1];
		
		str = br.readLine().split(" ");
		
		for(int i = 0;i < str.length;i++) B[i + 1] = Integer.parseInt(str[i]) + B[i];
		
		long res = 0;
		ArrayList<Long> listA = new ArrayList<>();
		Map<Long, Long> listB = new HashMap<>();
		
		for(int i = 1;i <= N;i++) {
			listA.add(A[i]);
			for(int j = i + 1;j <= N;j++) {
				listA.add(A[j] - A[i]);
			}
		}
		
		for(int i = 1;i <= M;i++) {
			if(!listB.containsKey(B[i])) {
				listB.put(B[i], 1L);
			} else {
				listB.put(B[i], listB.get(B[i]) + 1);
			}
			for(int j = i + 1;j <= M;j++) {
				if(!listB.containsKey(B[j] - B[i])) {
					listB.put(B[j] - B[i], 1L);
				} else {
					listB.put(B[j] - B[i], listB.get(B[j] - B[i]) + 1);
				}
			}
		}

		for(int i = 0;i < listA.size();i++) {
			if(listB.containsKey(T - listA.get(i))) {
				res += listB.get(T - listA.get(i));
			}
		}
		
		System.out.println(res);
	}
}
