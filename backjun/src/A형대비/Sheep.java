package A형대비;

import java.io.*;

// 백준 3184 양
// 필드를 만나면 dfs, 양과 늑대의 수 세기
public class Sheep {
	static int R, C;
	static String map[][];
	static boolean visited[][];
	static int sheep, wolf;
	static int sheepRes, wolfRes;
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	
	private static void dfs(int x, int y) {
		for(int i = 0;i < 4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >= 0 && nx < R && ny >= 0 && ny < C) {
				if(!visited[nx][ny] && !map[nx][ny].equals("#")) {
					visited[nx][ny] = true;
					if(map[nx][ny].equals("v")) wolf++;
					if(map[nx][ny].equals("o")) sheep++;
					dfs(nx, ny);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		
		map = new String[R][C];
		visited = new boolean[R][C];
		for(int i = 0;i < R;i++) map[i] = br.readLine().split("");
		
		for(int i = 0;i < R;i++) {
			for(int j = 0;j < C;j++) {
				if(!visited[i][j] && !map[i][j].equals("#")) {
					sheep = wolf = 0;
					visited[i][j] = true;
					if(map[i][j].equals("v")) wolf++;
					if(map[i][j].equals("o")) sheep++;
					dfs(i, j);
					
					if(sheep > wolf) sheepRes += sheep;
					else wolfRes += wolf;
				}
			}
		}
		
		System.out.println(sheepRes + " " + wolfRes);
	}
}
