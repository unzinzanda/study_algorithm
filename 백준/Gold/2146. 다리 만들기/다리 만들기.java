import java.util.*;
import java.io.*;

public class Main {
    static class Point {
        int x;
        int y;
        int depth;

        public Point(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    static int N;
    static int map[][];
    static int land[][];
    static int dx[] = {1, -1, 0, 0}, dy[] = {0, 0, 1, -1};

    // 땅에 번호 매기기
    static void dfs(int x, int y, int cnt) {
        land[x][y] = cnt;

        for(int i = 0;i < 4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 0 || land[nx][ny] != 0) continue;

            dfs(nx, ny, cnt);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        land = new int[N][N];

        for(int i = 0;i < N;i++) {
            String str[] = br.readLine().split(" ");
            for(int j = 0;j < N;j++) map[i][j] = Integer.parseInt(str[j]);
        }

        int landCnt = 1;
        for(int i = 0;i < N;i++) {
            for(int j = 0;j < N;j++) {
                if(map[i][j] == 1 && land[i][j] == 0) {
                    dfs(i, j, landCnt++);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 0;i < N;i++) {
            for(int j = 0;j < N;j++) {
                if(land[i][j] == 0) continue;

                boolean visited[][] = new boolean[N][N];
                Deque<Point> q = new ArrayDeque<>();
                q.add(new Point(i, j, 0));
                visited[i][j] = true;

                bfs:
                while(!q.isEmpty()) {
                    Point temp = q.poll();

                    for(int k = 0;k < 4;k++) {
                        int nx = temp.x + dx[k];
                        int ny = temp.y + dy[k];

                        if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || land[nx][ny] == land[i][j]) continue;
                        if(land[nx][ny] != 0 && land[nx][ny] != land[i][j]) {
                            ans = Math.min(ans, temp.depth);
                            break bfs;
                        }

                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny, temp.depth + 1));
                    }
                }
            }
        }

        System.out.println(ans);
    }
}