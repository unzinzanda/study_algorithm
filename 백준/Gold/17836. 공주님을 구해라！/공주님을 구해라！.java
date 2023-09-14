import java.util.*;
import java.io.*;

public class Main {
    static class Point {
        int x;
        int y;
        int depth;
        boolean hasGram;

        public Point(int x, int y, int depth, boolean hasGram) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.hasGram = hasGram;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int T = Integer.parseInt(str[2]);

        int map[][] = new int[N][M];
        boolean visited[][][] = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < str.length; j++) {
                map[i][j] = Integer.parseInt(str[j]);
            }
        }

        Queue<Point> q = new ArrayDeque<>();
        int dx[] = {0, 0, 1, -1};
        int dy[] = {1, -1, 0, 0};
        q.add(new Point(0, 0, 0, false));
        visited[0][0][0] = true;
        int answer = Integer.MAX_VALUE;

        bfs:
        while (!q.isEmpty()) {
            Point temp = q.poll();
            if(temp.depth > T) continue;

            for (int i = 0; i < 4; i++) {
                int nx = temp.x + dx[i];
                int ny = temp.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny][temp.hasGram ? 1 : 0]) continue;
                if (!temp.hasGram && map[nx][ny] == 1) continue;
                visited[nx][ny][temp.hasGram ? 1 : 0] = true;
                if (map[nx][ny] == 2) {
                    q.add(new Point(nx, ny, temp.depth + 1, true));
                    continue;
                }

                if (nx == N - 1 && ny == M - 1) {
                    answer = Math.min(answer, temp.depth + 1);
                    continue;
                }

                q.add(new Point(nx, ny, temp.depth + 1, temp.hasGram));
            }
        }


        if (answer <= T) System.out.println(answer);
        else System.out.println("Fail");
    }
}