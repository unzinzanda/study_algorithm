import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int order[];
    static StringBuilder sb = new StringBuilder();
    static void perm(int cnt) {
        if(cnt == M) {
            for(int i = 0;i < M;i++) sb.append(order[i]).append(" ");
            sb.append('\n');
            return;
        }

        for(int i = 1;i <= N;i++) {
            order[cnt] = i;
            perm(cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        order = new int[M];

        perm(0);

        System.out.println(sb.toString());
    }
}