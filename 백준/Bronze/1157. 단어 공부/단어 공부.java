import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        str = str.toUpperCase();
        int alpha[] = new int[26];
        for(int i = 0;i < 26;i++) {
            int pre = str.length();
            int next = str.replace(Character.toString(65 + i), "").length();
            alpha[i] = pre - next;
        }

        int maxCnt = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0;i < 26;i++) {
            if(maxCnt < alpha[i]) {
                maxCnt = alpha[i];
                list.clear();
                list.add(i);
            } else if(maxCnt == alpha[i]) list.add(i);
        }

        if(list.size() > 1) System.out.println("?");
        else System.out.println(Character.toString(65 + list.get(0)));
    }
}