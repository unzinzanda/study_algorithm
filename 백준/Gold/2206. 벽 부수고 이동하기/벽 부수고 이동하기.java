import java.util.*;
import java.io.*;
public class Main {
    static class Point {
        public Point(int x, int y, int depth, boolean breakWall) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.breakWall = breakWall;
        }

        int x;
        int y;
        int depth;
        boolean breakWall;


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str[] = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        int map[][] = new int[N][M];

        for(int i = 0;i < N;i++) {
            str = br.readLine().split("");
            for(int j = 0;j < str.length;j++) map[i][j] = Integer.parseInt(str[j]);
        }

        if(N == 1 && M == 1 && map[0][0] == 0) {
            System.out.println(1);
            System.exit(0);
        }

        boolean visited[][][] = new boolean[N][M][2]; // 0 : 벽 안 부숨, 1 : 벽 부숨
        Queue<Point> q = new ArrayDeque<>();

        q.add(new Point(0,0,1, false));
        visited[0][0][0] = true;
        int dx[] = {0, 0, -1, 1};
        int dy[] = {1, -1, 0, 0};
        int answer = Integer.MAX_VALUE;

        while(!q.isEmpty()) {
            Point temp = q.poll();

            for(int i = 0;i < 4;i++) {
                int nx = temp.x + dx[i];
                int ny = temp.y + dy[i];

                // 범위 밖
                if(nx < 0 || ny < 0 || nx >= N || ny >= M ) continue;

                else if(nx == N - 1 && ny == M - 1) {
                    answer = Math.min(answer, temp.depth + 1);
                }

                // 이미 벽을 부셨는데 또 벽을 만난 경우
                else if(temp.breakWall && map[nx][ny] == 1) continue;

                // 이미 방문을 한 경우
                else if(visited[nx][ny][temp.breakWall ? 1 : 0]) continue;

                // 벽을 부술 수 있는데 벽을 만난 경우
                else if(!temp.breakWall && map[nx][ny] == 1) {
                    visited[nx][ny][1] = true;
                    q.add(new Point(nx, ny, temp.depth + 1, true));
                }
                else {
                    visited[nx][ny][temp.breakWall ? 1 : 0] = true;
                    q.add(new Point(nx, ny, temp.depth + 1, temp.breakWall));
                }
            }
        }

        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }
}