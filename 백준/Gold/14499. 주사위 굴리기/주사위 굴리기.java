import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int x = Integer.parseInt(str[2]);
		int y = Integer.parseInt(str[3]);
		int K = Integer.parseInt(str[4]);
		
		int board[][] = new int[N][M];
		
		for(int i = 0;i < N;i++) {
			str = br.readLine().split(" ");
			for(int j = 0;j < M;j++) {
				board[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		int dx[] = {0, 0, 0, -1 , 1}, dy[] = {0, 1, -1, 0, 0};
		int dice[][] = new int[4][3];
		str = br.readLine().split(" ");
		for(int k = 0;k < K;k++) {
			int dir = Integer.parseInt(str[k]);
			
			// 주사위 굴리기
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
			x = nx;
			y = ny;

			// 바닥면 제외하고 칸 이동
			int bottom = 0;
			switch(dir) {
			case 1:
				bottom = dice[1][2];
				dice[1][2] = dice[1][1];
				dice[1][1] = dice[1][0];
				dice[1][0] = dice[3][1];
				break;
			case 2:
				bottom = dice[1][0];
				dice[1][0] = dice[1][1];
				dice[1][1] = dice[1][2];
				dice[1][2] = dice[3][1];
				break;
			case 3:
				bottom = dice[0][1];
				dice[0][1] = dice[1][1];
				dice[1][1]= dice[2][1];
				dice[2][1] = dice[3][1];
				break;
			case 4:
				bottom = dice[2][1];
				dice[2][1] = dice[1][1];
				dice[1][1] = dice[0][1];
				dice[0][1] = dice[3][1];
				break;
			}
			
			// 바닥면 값 저장
			if(board[x][y] == 0) {
				board[x][y] = bottom;
				dice[3][1] = bottom;
			} else {
				dice[3][1] = board[x][y];
				board[x][y] = 0;
			}
			
			// 윗면 값 출력
			System.out.println(dice[1][1]);
		}
	}

}