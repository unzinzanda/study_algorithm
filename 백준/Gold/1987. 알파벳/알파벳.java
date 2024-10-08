import java.io.*;

class Main {
    static int result = 0, R, C;
    static char[][] map;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static boolean[] alpha;

    static void dfs(int x, int y, int path) {
        result = Math.max(result, path);
        for(int i = 0;i < 4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= R || ny >= C || alpha[(int)map[nx][ny] - 65]) continue;

            alpha[(int)map[nx][ny] - 65] = true;
            dfs(nx, ny, path + 1);
            alpha[(int)map[nx][ny] - 65] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        R = Integer.parseInt(str[0]);
        C = Integer.parseInt(str[1]);

        map = new char[R][C];
        for(int i = 0;i < R;i++) {
            map[i] = br.readLine().toCharArray();
        }

        alpha = new boolean[26];
        alpha[(int)map[0][0] - 65] = true;
        dfs(0, 0, 1);

        System.out.println(result);
    }
}