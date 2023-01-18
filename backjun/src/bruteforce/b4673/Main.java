package bruteforce.b4673;

public class Main {
	
	public static boolean f[] = new boolean[10001];
	public static void d(int n) {
		int one = n % 10;
		int two = (n % 100) / 10;
		int three = (n % 1000) / 100;
		int four = n / 1000;
		
		int result = n + one + two + three + four;
		if(result <= 10000) {
			if(f[result] == false) f[result] = true;
		}
	}

	public static void main(String[] args) {
		for(int i = 1; i <= 10000;i++) {
			d(i);
		}
		for(int i = 1; i <= 10000;i++) {
			if(f[i] == false) System.out.println(i);
		}
	}
	
}
