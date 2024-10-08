import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int order[];
    static void comb(int idx, int cnt) {
        if(cnt == M) {
            for(int i = 0;i < M;i++) System.out.print(order[i] + " ");
            System.out.println();
            return;
        }

        for(int i = idx;i <= N;i++) {
            order[cnt] = i;
            comb(i + 1,cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        order = new int[M];

        comb(1,0);
    }
}