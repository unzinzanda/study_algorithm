import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lightGreen = br.readLine();
        int N = Integer.parseInt(br.readLine());

        String team[] = new String[N];

        for(int i = 0;i < N;i++) team[i] = br.readLine();

        Arrays.sort(team);
        int maxPercent = 0;
        String result = team[0];
        for(int i = 0;i < N;i++) {
            String temp = lightGreen + team[i];

            int love[] = new int[4];
            love[0] = temp.length() - temp.replace("L", "").length();
            love[1] = temp.length() - temp.replace("O", "").length();
            love[2] = temp.length() - temp.replace("V", "").length();
            love[3] = temp.length() - temp.replace("E", "").length();

            int per = 1;
            for(int j = 0;j < 3;j++) {
                for(int k = j + 1;k < 4;k++) per *= (love[j] + love[k]);
            }

            per %= 100;

            if(maxPercent < per) {
                maxPercent = per;
                result = team[i];
            }
        }

        System.out.println(result);
    }
}