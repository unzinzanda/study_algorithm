import java.util.*;
import java.io.*;
/*
* 불이 없을 수도 있음
* 플레이어가 이동하며 범위 밖으로 나가는 경우 -> 출구에 위치할 경우
* */
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
    static int w, h;
    static char map[][];
    static Point sanggeun;
    static boolean visited[][], fire[][];
    static Deque<Point> firePoint;
    static int dx[] = {1, -1, 0, 0}, dy[] = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for(int t  = 0;t < tc;t++) {
            String str[] = br.readLine().split(" ");
            w = Integer.parseInt(str[0]);
            h = Integer.parseInt(str[1]);
            
            map = new char[h][w];
            visited = new boolean[h][w];
            fire = new boolean[h][w];
            firePoint = new ArrayDeque<>();
            int ans = 0;

            // 지도 입력 받기
            // 상근이의 위치 저장하고 불 초기 위치 큐에 담기
            for(int i = 0;i < h;i++) {
                map[i] = br.readLine().toCharArray();
                for(int j = 0;j < w;j++) {
                    if(map[i][j] == '@') {
                        sanggeun = new Point(i, j, 1);
                        break;
                    }
                    else if(map[i][j] == '*') {
                        firePoint.add(new Point(i, j, 0));
                        fire[i][j] = true;
                    }
                }
            }

            if(w == 1 && h == 1) {
                System.out.println(1);
                continue;
            }

            // depth가 달라지기 전, 불이 번져야 함
            Deque<Point> q = new ArrayDeque<>();
            q.add(sanggeun);

            int curDepth = 0;
            bfs:
            while(!q.isEmpty()) {
                Point temp = q.poll();

                if(temp.depth != curDepth) {
                    spread();
                    curDepth = temp.depth;
                }

                for(int i = 0;i < 4;i++) {
                    int nx = temp.x + dx[i];
                    int ny = temp.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= h || ny >= w) {
                        ans = temp.depth;
                        break bfs;
                    }
                    else if(visited[nx][ny] || fire[nx][ny] || map[nx][ny] == '#') continue;

                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny, temp.depth + 1));
                }
            }

            if(ans == 0) System.out.println("IMPOSSIBLE");
            else System.out.println(ans);
        }
    }

    static void spread() {
        int size = firePoint.size();
        while(size-- > 0) {
            Point temp = firePoint.poll();

            for(int i = 0;i < 4;i++) {
                int nx = temp.x + dx[i];
                int ny = temp.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= h || ny >= w || fire[nx][ny] || map[nx][ny] == '#') continue;

                fire[nx][ny] = true;
                firePoint.add(new Point(nx, ny, 0));
            }
        }
    }
}