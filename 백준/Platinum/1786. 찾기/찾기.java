import java.io.*;
import java.util.*;

//백준 1786 찾기
/*
 * KMP 알고리즘을 사용하여 문자열 매칭
 * */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char T[] = br.readLine().toCharArray();
		char P[] = br.readLine().toCharArray();
		
		int pi[] = new int[P.length];
			
		//부분 문자열 배열 만들기
		int j = 0;
		for(int i = 1;i < P.length;i++) {
			while(j > 0 && P[i] != P[j]) j = pi[j - 1];
			
			if(P[i] == P[j]) pi[i] = ++j;
			else pi[i] = 0;
		}
		
		j = 0;
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0;i < T.length;i++) {
			while(j > 0 && T[i] != P[j]) j = pi[j - 1];
			if(T[i] == P[j]){
				if(j == P.length - 1) {
					cnt++;
					list.add(i - j + 1);
					j = pi[j];
				}
				else j++;
			}
		}
		
		System.out.println(cnt);
		for(int a : list) sb.append(a).append(" ");
		System.out.println(sb.toString());
	}
}