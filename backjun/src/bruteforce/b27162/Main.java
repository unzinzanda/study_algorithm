package bruteforce.b27162;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		
		int max = 0;
		int dice[] = new int[3];
		int num[] = new  int[7];
		
		for(int i = 0; i < 3;i++) dice[i] = sc.nextInt();
		for(int i = 0; i < 3; i++) num[dice[i]] += 1;
		
		for(int i = 0; i < 6;i++) {
			int res = 0;
			if(str.charAt(i) == 'Y') {
				for(int j = 0; j < 3;j++) {
					if(dice[j] == (i + 1)) res += (i + 1);
				}
				res += 2 * (i + 1);
			}
			max = Math.max(max, res);
		}
		
		// Four of a Kind
		if(str.charAt(6) == 'Y') {
			int two = 0;
			for(int i = 1; i < 7; i++) {
				if(num[i] >= 2) {
					two = i;
					break;
				}
			}
			
			if(two != 0) max = Math.max(max, two * 4);
		}
		
		// Full House
		if(str.charAt(7) == 'Y') {
			boolean oneFlag = false, twoFlag = false;
			boolean threeFlag = false;
			int one = 0, two = 0, three = 0;
			for(int i = 1; i < 7;i++) {
				if(num[i] == 1) {
					oneFlag = true;
					one = i;
				}
				if(num[i] == 2) {
					twoFlag = true;
					two = i;
				}
				if(num[i] == 3) {
					threeFlag = true;
					three = i;
					break;
				}
			}
			
			if(oneFlag && twoFlag) {
				max = Math.max(max, (one * 2) + (two * 3));
			}
			if(threeFlag) {
				if(three == 6) max = Math.max(max, three * 3 + 10);
				else max = Math.max(max, three * 3 + 12);
			}
		}
		
		//Little Straight
		if(str.charAt(8) == 'Y') {
			int cnt = 0;
			for(int i = 1; i < 6;i++) {
				if(num[i] == 1) cnt += 1;
			}
			if(cnt == 3) max = Math.max(max, 30);
		}
		
		//Big Straight
		if(str.charAt(9) == 'Y') {
			int cnt = 0;
			for(int i = 2; i < 7;i++) {
				if(num[i] == 1) cnt++;
			}
			if(cnt == 3) max = Math.max(max, 30);
		}
		
		//Yacht
		if(str.charAt(10) == 'Y') {
			int ya = 0;
			for(int i = 1; i < 7;i++) {
				if(num[i] == 3) {
					ya = i;
					break;
				}
			}
			if(ya != 0) max = Math.max(max, 50);
		}
		
		//Choice
		if(str.charAt(11) == 'Y') {
			int sum = 0;
			for(int i = 0; i < 3;i++) sum += dice[i];
			max = Math.max(max, sum + 12);
		}
		
		System.out.println(max);
		
		sc.close();
	}
}
