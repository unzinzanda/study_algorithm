import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int order[];
    static StringBuilder sb = new StringBuilder();
    static void comb(int idx, int cnt) {
        if(cnt == M) {
            for(int i = 0;i < M;i++) sb.append(order[i]).append(" ");
            sb.append('\n');
            return;
        }

        for(int i = idx;i <= N;i++) {
            order[cnt] = i;
            comb(i,cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        order = new int[M];

        comb(1,0);

        System.out.println(sb.toString());
    }
}