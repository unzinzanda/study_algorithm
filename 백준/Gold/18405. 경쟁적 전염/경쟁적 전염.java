import java.util.*;
import java.io.*;
public class Main {
    static class Virus implements Comparable<Virus> {
        int x;
        int y;
        int num;

        public Virus(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public int compareTo(Virus o) {
            return this.num - o.num;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str[] = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);

        int map[][] = new int[N][K];
        PriorityQueue<Virus> pq = new PriorityQueue<>();

        for(int i = 0;i < N;i++) {
            str = br.readLine().split(" ");
            for(int j = 0;j < N;j++) {
                map[i][j] = Integer.parseInt(str[j]);
                if(map[i][j] != 0) pq.add(new Virus(i, j, map[i][j]));
            }
        }

        str = br.readLine().split(" ");
        int S = Integer.parseInt(str[0]);
        int X = Integer.parseInt(str[1]);
        int Y = Integer.parseInt(str[2]);

        int dx[] = {1, -1, 0, 0};
        int dy[] = {0, 0, 1, -1};

        for(int i = 0;i < S;i++) {
            int size = pq.size();
            PriorityQueue<Virus> nextPq = new PriorityQueue<>();

            for(int j = 0;j < size;j++) {
                Virus temp = pq.poll();

                for(int k = 0;k < 4;k++) {
                    int nx = temp.x + dx[k];
                    int ny = temp.y + dy[k];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] != 0) continue;

                    map[nx][ny] = temp.num;
                    nextPq.add(new Virus(nx, ny, temp.num));
                }
            }

            pq = nextPq;
        }

        System.out.println(map[X - 1][Y - 1]);
    }
}