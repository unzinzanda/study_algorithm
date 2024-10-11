import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ban[][] = new int[N][5];

        for(int i = 0;i < N;i++) {
            String str[] = br.readLine().split(" ");
            for(int j = 0;j < 5;j++) ban[i][j] = Integer.parseInt(str[j]);
        }

        // isSame[i][j] = true -> i와 j가 같은 반인 적이 있음
        boolean isSame[][] = new boolean[N][N];
        for(int i = 0;i < 5;i++) {
            for(int j = 0;j < N;j++) {
                for(int k = 0;k < N;k++) {
                    if(j == k) continue;
                    if(!isSame[j][k] && ban[j][i] == ban[k][i]) {
                        isSame[j][k] = true;
                    }
                }
            }
        }
        int result = 1, maxCnt = 0;
        for(int i = 0;i < N;i++) {
            int cnt = 0;
            for(int j = 0;j < N;j++) {
                if(isSame[i][j]) cnt++;
            }
            if(maxCnt < cnt) {
                result = i + 1;
                maxCnt = cnt;
            }
        }

        System.out.println(result);
    }
}