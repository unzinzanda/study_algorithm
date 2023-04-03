package bruteforce.b1065;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		
		if(num >= 1 && num < 100) System.out.println(num);
		else {
			int count = 99;
			int cur = 100;
			for(;cur <= num;cur++) {
				if(cur != 1000) {
					int one = cur % 10;
					int two = (cur % 100) / 10;
					int three = (cur % 1000) / 100;
					
					if((three - two) == (two - one)) count += 1;
				}
			}
			System.out.println(count);
		}
		sc.close();
	}
}
