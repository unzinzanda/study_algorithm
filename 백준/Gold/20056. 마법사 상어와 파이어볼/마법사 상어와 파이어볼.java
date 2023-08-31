import java.io.*;
import java.util.*;

// 백준 20056 마법사 상어와 파이어볼
/*
 * d 방향으로 s 칸 만큼 이동
 * 
 * 이동 후, 한 칸에 여러 파이어볼이 모인다면
 * 1. 하나로 합치기
 * 2. 4개로 나누기 (질량 : 질량합 / 5, 속력 : 속력 합 / 파이어볼 개수)
 * 3. 합쳐지는 파이어볼 방향이 모두 홀수거나 짝수면 4개의 방향은 0 2 4 6이고 아니면 1 3 5 7 이 됨
 * 4. 질량이 0이면 소멸
 * 
 * 할 일
 * 1. 파이어볼 이동
 * 2. 파이어볼이 모인 경우에 대한 처리
 * */
public class Main {
	static class Fireball {
		int x;
		int y;
		int m;
		int s;
		int d;
		public Fireball(int x, int y, int m, int s, int d) {
			super();
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
	}
	static int N, M, K;
	static int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int dy[] = {0, 1, 1, 1, 0, -1, -1, -1};
	static List<Fireball> fireballs = new ArrayList<>();
	static Queue<Fireball> map[][];
	
	static void move() {
		for(Fireball fire: fireballs) {
			int nx = (fire.x + ((fire.s % N) * dx[fire.d]) + N) % N;
			int ny = (fire.y + ((fire.s % N) * dy[fire.d]) + N) % N;
			
			fire.setX(nx);
			fire.setY(ny);
			
			map[nx][ny].add(fire);
		}
	}
	
	static void division() {
		for(int i = 0;i < N;i++) {
			for(int j = 0;j < N;j++) {
				if(map[i][j].size() >= 2) {
					int mSum = 0;
					int sSum = 0;
					int cnt = map[i][j].size();
					
					boolean odd = true;
					boolean even = true;
					
					while(!map[i][j].isEmpty()) {
						Fireball fire = map[i][j].poll();
						mSum += fire.m;
						sSum += fire.s;
						
						if(fire.d % 2 == 0) {
							odd = false;
						} else even = false;
						
						fireballs.remove(fire);
					}
					
					int newM = mSum / 5;
					int newS = sSum / cnt;
					
					if(newM == 0) continue;
					
					if(odd || even) {
						for(int k = 0;k < 8;k += 2) {
							fireballs.add(new Fireball(i, j, newM, newS, k));
						}
					} else {
						for(int k = 1;k < 8;k += 2) {
							fireballs.add(new Fireball(i, j, newM, newS, k));
						}
					}
				} else map[i][j].clear();
			}
		} 
	}
	
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		
		map = new LinkedList[N][N];
		
		for(int i = 0;i < N;i++) {
			for(int j = 0;j < N;j++) map[i][j] = new LinkedList<>();
		}
		
		for(int i = 0;i < M;i++) {
			str = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			int m = Integer.parseInt(str[2]);
			int s = Integer.parseInt(str[3]);
			int d = Integer.parseInt(str[4]);
			fireballs.add(new Fireball(x - 1, y - 1, m, s, d));
		}
		
		for(int i = 0;i < K;i++) {
			move();
			division();
		}
		
		int ans = 0;
		
		for(Fireball fire : fireballs) ans += fire.m;
		
		System.out.println(ans);
	}
}