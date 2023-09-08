import java.io.*;

public class Main {
    static int N, M;
    static String maze[][];
    static boolean visited[][];
    static int canEscape[][];
    // 0 : R, 1 : L, 2 : D, 3 : U
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};

    static int answer = 0;

    static boolean escape(int x, int y) {
        if(x < 0 || y < 0 || x >= N || y >= M) {
            return true;
        }
        if(visited[x][y]) return false;
        if(canEscape[x][y] != -1) return canEscape[x][y] == 1;
            visited[x][y] = true;
            int dir;
            switch (maze[x][y]) {
                case "D":
                    dir = 2;
                    break;
                case "U":
                    dir = 3;
                    break;
                case "R":
                    dir = 0;
                    break;
                case "L":
                    dir = 1;
                    break;
                default:
                    dir = -1;
            }
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            canEscape[x][y] = escape(nx, ny) ? 1 : 0;
            visited[x][y] = false;

        return canEscape[x][y] == 1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        maze = new String[N][M];
        canEscape = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0;i < N;i++){
            str = br.readLine().split("");
            for(int j = 0;j < M;j++) {
                maze[i][j] = str[j];
                canEscape[i][j] = -1;
            }
        }
        for(int i = 0;i < N;i++) {
            for(int j = 0;j < M;j++) {
                if(escape(i, j)) answer++;
            }
        }

        System.out.println(answer);
    }
}