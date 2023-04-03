package gumi_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b10828 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int size = 0;
		int stack[] = new int[10000];
		for(int i = 0; i < N;i++) {
			String str[] = br.readLine().split(" ");
			if(str[0].equals("push")) {
				if(size < 10000) stack[size++] = Integer.parseInt(str[1]);
			}
			else if(str[0].equals("pop")) {
				if(size == 0) {
					System.out.println(-1);
				}
				else {
					System.out.println(stack[size - 1]);
					stack[size - 1] = 0;
					size--;
				}
			}
			else if(str[0].equals("size")) System.out.println(size);
			else if(str[0].equals("empty")) {
				if(size > 0) System.out.println(0);
				else System.out.println(1);
			}
			else if(str[0].equals("top")) {
				if(size > 0) System.out.println(stack[size - 1]);
				else System.out.println(-1);
			}
		}
	}
}
