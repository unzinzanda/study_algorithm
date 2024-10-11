import java.io.*;
import java.util.*;

class Main {
    static int rev(String str) {
        String result = "";
        String arr[] = str.split("");

        for(int i = arr.length - 1;i >= 0;i--) result += arr[i];

        return Integer.parseInt(result);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int sum = rev(str[0]) + rev(str[1]);
        System.out.println(rev(Integer.toString(sum)));
    }
}