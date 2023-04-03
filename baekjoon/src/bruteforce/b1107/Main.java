package bruteforce.b1107;

import java.util.Scanner;

//���� 1107 ������
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String want = sc.next();
		int M = sc.nextInt();
		int fail[] = new int[M];
		int canUse[] = new int[10 - M]; // boolean type으로 사용가능숫자 표기
		
		for(int i = 0;i < M;i++) fail[i] = sc.nextInt();
		int idx = 0;
		for(int i = 0;i < 10;i++) {
			boolean flag = false;
			for(int j = 0; j < M;j++) {
				if(i == fail[j]) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				canUse[idx] = i;
				idx++;
			}
		}
		
		// want == 100
		if(want.equals("100")) System.out.println(0);
		
		else {
			int min = Integer.MAX_VALUE;
			int w = Integer.parseInt(want);
			min = Math.min(min, Math.abs(w - 100));
			
			for(int a : canUse) {
				if(a == 0) min = Math.min(min, w + 1);
			}
			
			for(int  i = 1;i < 1000000;i++) {
				int temp = i;
				int cnt = 0;
				boolean flag = false;
				while(temp > 0) {
					flag = false;
					for(int a : canUse) {
						if(a == (temp % 10)) {
							flag = true;
							break;
						}
					}
					if(!flag) break;
					else {
						temp = temp / 10;
						cnt++;
					}
				}
				if(!flag) continue;
				else {
					int res = Math.abs(w - i);
					min = Math.min(min, res + cnt);
				}
			}
			
			System.out.println(min);
			
			sc.close();
		}
	}
}
