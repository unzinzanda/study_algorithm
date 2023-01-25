package gumi_5;

import java.util.Scanner;

public class b2839 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sugar = sc.nextInt();
		int res = 0;
		int min = Integer.MAX_VALUE;
		if(sugar % 3 == 0) {
			if(min > (sugar / 3)) min = sugar / 3;
		}
		while(sugar >= 5) {
			res += 1;
			sugar -= 5;
			if(sugar == 0) {
				if(min > res) min = res;
			}
			if(sugar % 3 == 0) {
				int tmp =  sugar / 3;
				if(min > res + tmp) min = res + tmp;
			}
		}
		
		if(min == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(min);
		sc.close();
	}
}
